/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Vojta
 */
public class Picture {

    private BufferedImage bufferImage, biGrass, biWall, bifloor, biBox, biPlayer, biFinish;

    Picture(int width, int height) {
        bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        try {
            biGrass = ImageIO.read(getClass().getResource("Pictures/grass.png"));
            biWall = ImageIO.read(getClass().getResource("Pictures/wall.png"));
            bifloor = ImageIO.read(getClass().getResource("Pictures/floor.png"));
            biBox = ImageIO.read(getClass().getResource("Pictures/box.png"));
            biPlayer = ImageIO.read(getClass().getResource("Pictures/player.png"));
            biFinish = ImageIO.read(getClass().getResource("Pictures/finish.png"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
//metoda na vykreslení
    void Draw(byte[][] desk, Graphics g, boolean end) {
        Graphics bufferGraphics = bufferImage.getGraphics();
        for (int y = 0; y < desk.length; y++) {
            for (int x = 0; x < desk[0].length; x++) {
                switch (desk[y][x]) {
                    case 0:
                        bufferGraphics.drawImage(biGrass, x * 32, y * 32, null);
                        break;
                    case 1:
                        bufferGraphics.drawImage(biWall, x * 32, y * 32, null);
                        break;
                    case 2:
                        bufferGraphics.drawImage(bifloor, x * 32, y * 32, null);
                        break;
                    case 3:
                        bufferGraphics.drawImage(biBox, x * 32, y * 32, null);
                        break;
                    case 4:
                        bufferGraphics.drawImage(biPlayer, x * 32, y * 32, null);
                        break;
                    case 5:
                        bufferGraphics.drawImage(biFinish, x * 32, y * 32, null);
                        break;
                }
            }
        }
        if (end) {
            bufferGraphics.setColor(Color.red);
            bufferGraphics.setFont(new Font("TimesRoman", Font.BOLD, 24));
            bufferGraphics.drawString("Konec kola", bufferImage.getWidth() / 2 - 64, bufferImage.getHeight() / 2);
        }
        g.drawImage(bufferImage, 0, 0, null);
    }
}
