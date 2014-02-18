/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Logic;
import java.io.File;

/**
 *
 * @author Vojta
 */
public class GUI extends javax.swing.JFrame {

    private Logic logic;
    private Picture graphics;
    private final File[] fileList;
    private boolean end;

    /**
     * Creates new form GUI
     */
    //konstruktor kde načítám seznam map
    public GUI() {
        initComponents();
        end = false;
        File f = new File("Map");
        fileList = f.listFiles();
        for (File file : fileList) {
            LevelList.add(file.getName().substring(0, file.getName().length() - 4));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NewGame = new javax.swing.JButton();
        DeskView = new java.awt.Canvas();
        StepCount = new java.awt.Label();
        LevelList = new java.awt.List();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(640, 480));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        NewGame.setText("Nová hra");
        NewGame.setEnabled(false);
        NewGame.setFocusable(false);
        NewGame.setName(""); // NOI18N
        NewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameActionPerformed(evt);
            }
        });

        DeskView.setName(""); // NOI18N
        DeskView.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                DeskViewFocusGained(evt);
            }
        });

        StepCount.setName(""); // NOI18N

        LevelList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                LevelListItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(DeskView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LevelList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StepCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(168, 168, 168))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(NewGame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LevelList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(DeskView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StepCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        NewGame.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
//velikost gui
    private void NewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGameActionPerformed
        logic = new Logic(fileList[LevelList.getSelectedIndex()]);
        graphics = new Picture(logic.getWidth() * 32, logic.getHeight() * 32);
        setSize(logic.getWidth() * 32 + 230, logic.getHeight() * 32 + 80);
        DeskView.setSize(logic.getWidth() * 32, logic.getHeight() * 32);
        end = false;
        updateView(end);
    }//GEN-LAST:event_NewGameActionPerformed
//reagovani na tl. zavola se provedení pohybu a kontrola end
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        if (!end) {
            if (evt.getKeyCode() == 37) {
                updateView(end = logic.MoveLeft());
            }
            if (evt.getKeyCode() == 38) {
                updateView(end = logic.MoveUp());
            }
            if (evt.getKeyCode() == 39) {
                updateView(end = logic.MoveRight());
            }
            if (evt.getKeyCode() == 40) {
                updateView(end = logic.MoveDown());
            }
        }
    }//GEN-LAST:event_formKeyPressed
//udržení focus na formulaři
    private void DeskViewFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DeskViewFocusGained
        // TODO add your handling code here:
        requestFocus();
    }//GEN-LAST:event_DeskViewFocusGained
//oznacení v listu levlů a zpřístupní setl. nová hra
    private void LevelListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_LevelListItemStateChanged
        // TODO add your handling code here:
        NewGame.setEnabled(true);
    }//GEN-LAST:event_LevelListItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas DeskView;
    private java.awt.List LevelList;
    private javax.swing.JButton NewGame;
    private java.awt.Label StepCount;
    // End of variables declaration//GEN-END:variables
//překreslení
    private void updateView(boolean end) {
        graphics.Draw(logic.toArray(), DeskView.getGraphics(), end);
        StepCount.setText(String.valueOf("kroky: " + logic.getSteps()));
        requestFocus();
    }
}
