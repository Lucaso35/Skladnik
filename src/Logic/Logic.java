/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vojta
 */
public class Logic {

    private byte[][] desk, movers;
    private int steps;
    private Point player;
    private final ArrayList<Point> finishPoints;
//konstruktor kde načítám mapu ze souboru přes parametr fileName a nastavuji písmena na čísla
    public Logic(File fileName) {
        steps = 0;
        finishPoints = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            ArrayList<Byte[]> gameMap = new ArrayList<>();
            while (line != null) {
                char[] gameCharLine = line.toCharArray();
                Byte[] gameLine = new Byte[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    switch (gameCharLine[i]) {
                        case '-':
                            gameLine[i] = 0;
                            break;
                        case '#':
                            gameLine[i] = 1;
                            break;
                        case ' ':
                            gameLine[i] = 2;
                            break;
                        case 'O':
                            gameLine[i] = 3;
                            break;
                        case '@':
                            gameLine[i] = 4;
                            break;
                        case '+':
                            gameLine[i] = 5;
                            break;
                    }
                }
                gameMap.add(gameLine);
                line = br.readLine();
            }
            //přepsání z jednoho pole na dva mapa a pohybijící se objekty
            Byte[][] b = gameMap.toArray(new Byte[0][0]);
            desk = new byte[b.length][b[0].length];
            movers = new byte[b.length][b[0].length];
            for (int y = 0; y < b.length; y++) {
                for (int x = 0; x < b[0].length; x++) {
                    if (b[y][x] == 3) {
                        desk[y][x] = 2;
                        movers[y][x] = 3;
                    } else if (b[y][x] == 4) {
                        player = new Point(x, y);
                        desk[y][x] = 2;
                        movers[y][x] = 4;
                    } else {
                        if (b[y][x] == 5) {
                            finishPoints.add(new Point(x, y));
                        }
                        desk[y][x] = b[y][x];
                        movers[y][x] = 0;
                    }
                }
            }
            //vyjimka, zda li načetl soubor
        } catch (IOException ex) {
            Logger.getLogger(Logic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//metody na pohyb
    public boolean MoveUp() {
        move(player, "UP");
        return CheckFinish();
    }

    public boolean MoveDown() {
        move(player, "DOWN");
        return CheckFinish();
    }

    public boolean MoveLeft() {
        move(player, "LEFT");
        return CheckFinish();
    }

    public boolean MoveRight() {
        move(player, "RIGHT");
        return CheckFinish();
    }
//poze debug
    @Override
    public String toString() {
        String result = new String();
        for (byte y = 0; y < getHeight(); y++) {
            for (byte x = 0; x < getWidth(); x++) {
                if (movers[y][x] != 0) {
                    switch (movers[y][x]) {
                        case 3:
                            result += 'O';
                            break;
                        case 4:
                            result += '@';
                            break;
                    }
                } else {
                    switch (desk[y][x]) {
                        case 0:
                            result += ' ';
                            break;
                        case 1:
                            result += '#';
                            break;
                        case 2:
                            result += ' ';
                            break;
                        case 5:
                            result += '+';
                            break;
                    }
                }
            }
            result += "\n";
        }
        result += "\n";
        return result;
    }
//převod dvou poli na jedno pro vykreslovani
    public byte[][] toArray() {
        byte[][] result = new byte[desk.length][desk[0].length];
        for (byte y = 0; y < getHeight(); y++) {
            for (byte x = 0; x < getWidth(); x++) {

                if (movers[y][x] != 0) {
                    result[y][x] = movers[y][x];
                } else {
                    result[y][x] = desk[y][x];
                }
            }
        }
        return result;
    }
//kontrola zda li jsou všechny bedny v cíli
    private boolean CheckFinish() {
        int p = 0;
        for (Point f : finishPoints) {
            if (movers[f.y][f.x] == 3) {
                p++;
            }
        }
        return p == finishPoints.size();
    }
//provedení samotného pohybu
    private boolean move(Point start, String direction) {
        Point dest = new Point(start);
        if (direction.equals("UP")) {
            dest.y--;
        }
        if (direction.equals("DOWN")) {
            dest.y++;
        }
        if (direction.equals("LEFT")) {
            dest.x--;
        }
        if (direction.equals("RIGHT")) {
            dest.x++;
        }

        byte who = movers[start.y][start.x];
        byte moverAt = movers[dest.y][dest.x];
        byte deskAt = desk[dest.y][dest.x];

        if ((moverAt == 3 && who == 4 && move(dest, direction))
                || (deskAt == 2 && who == 3 && moverAt == 0)
                || (deskAt == 2 && who == 4 && moverAt == 0)
                || (deskAt == 5 && who == 3 && moverAt == 0)
                || (deskAt == 5 && who == 4 && moverAt == 0)) {
            movers[dest.y][dest.x] = who;
            movers[start.y][start.x] = 0;
            if (who == 4) {
                player = dest;
                steps++;
            }
            return true;
        }
        return false;
    }

    public int getWidth() {
        return desk[0].length;
    }

    public int getHeight() {
        return desk.length;
    }

    public int getSteps() {
        return steps;
    }
}
