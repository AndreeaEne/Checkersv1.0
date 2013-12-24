
/**
 * Created with IntelliJ IDEA.
 * User: Andreea-Daniela Ene
 * Date: 12/2/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */

package Checkers;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// jframe == fereastra
// mouselistener == ca sa primim evenimente legate de mouse
public class CheckersProgram extends javax.swing.JFrame implements MouseListener
{
    // Celulele albe si negre
    public javax.swing.JLabel[][] jLabelSquare;
    // Piesele
    public javax.swing.JLabel[][] jLabelDots;
    // Starile celulei (0 == goala, 1 == are piesa jucator 1, 2 == are piesa jucator 2)
    public int[][] cellState;

    // Cate celule pe rand si coloana
    public int dotsPerRow = 8;
    public int dotsPerCol = 8;
    public int size;

    public CheckersProgram()
    {
        int width = 800;
        this.setSize(width, width + 20);
        this.setResizable(false);
        this.setTitle("Checkers");
        size = width / dotsPerRow;

        // instantam arrayurile
        jLabelSquare = new javax.swing.JLabel[dotsPerRow][dotsPerCol];
        jLabelDots = new javax.swing.JLabel[dotsPerRow][dotsPerCol];
        cellState = new int[dotsPerRow][dotsPerCol];

        // luam fiecare celula
        for (int i = 0; i < dotsPerRow; i++) {
            for (int j = 0; j < dotsPerCol; j++) {
                boolean isPlayer1 = j <= 1 && ((i + j) % 2 == 0);
                boolean isPlayer2 = j >= 6 && ((i + j) % 2 == 0);

                // Starea celulei
                if (isPlayer1) {
                    cellState[i][j] = 1;
                }
                else if (isPlayer2) {
                    cellState[i][j] = 2;
                }
                else {
                    cellState[i][j] = 0;
                }

                // Piesa
                if (isPlayer1 || isPlayer2) {
                    Color c = isPlayer1 ? Color.RED : Color.BLUE;

                    // cream labelul
                    jLabelDots[i][j] = new javax.swing.JLabel();
                    jLabelDots[i][j].setOpaque(false);
                    jLabelDots[i][j].setLocation(i * size + size / 4, j * size + size / 4);
                    jLabelDots[i][j].setSize(size / 2, size / 2);
                    jLabelDots[i][j].setVisible(true);

                    // captureaza clickuri de mouse
                    jLabelDots[i][j].addMouseListener(
                            new MouseAdapter() {
                                @Override
                                public void mouseReleased(MouseEvent evt) {
                                    piesaDropped(evt);
                                }
                            }
                    );

                    // Folosim lineborder ca sa dam colturi rotunjite labelui. astfel dam efectul de cerc.
                    LineBorder line = new LineBorder(c, 100, true);
                    jLabelDots[i][j].setBorder(line);

                    // adaugam labelul in frame
                    this.add(jLabelDots[i][j]);
                }
            }
        }

        // luam fiecare celula
        for (int i = 0; i < dotsPerRow; i++) {
            for (int j = 0; j < dotsPerCol; j++) {
                // Celula patrat
                jLabelSquare[i][j] = new javax.swing.JLabel();
                jLabelSquare[i][j].setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.BLACK);
                jLabelSquare[i][j].setOpaque(true);
                jLabelSquare[i][j].setLocation(i * size, j * size);
                jLabelSquare[i][j].setSize(size, size);
                jLabelSquare[i][j].setVisible(true);
                this.add(jLabelSquare[i][j]);
            }
        }

        this.setLayout(null);
    }

    public void piesaDropped(MouseEvent evt) {
        Component src = evt.getComponent();

        // aflam x si y relativ la frame si aflam celula de la pozitia respectiva
        int i = (evt.getX() + src.getX()) / size;
        int j = (evt.getY() + src.getY()) / size;
//        jLabelSquare[i][j].setBackground(Color.PINK);

        boolean potsamut = true;
        if (potsamut)           {
            int e = src.getX() / size;
            int f = src.getY() / size;
            jLabelDots[e][f].move(jLabelSquare[i][j].getX() + size / 4,jLabelSquare[i][j].getY() + size / 4);

            jLabelDots[i][j] = jLabelDots[e][f];
            jLabelDots[e][f] = null;
            /*
             celula pe care am dat drumul piesa este la i,j
             celula curenta pe care este piesa este la pozitia e,f
             */
        }



        repaint();
        System.out.println("Here");
    }

    @Override
    public void mouseClicked(MouseEvent me) {
//        me.getComponent().setBackground(getColorForState(getStateForColor(me.getComponent().getBackground()) == 0 ? 1 : 0));
    }

    @Override
    public void mouseEntered (MouseEvent e) {}

    @Override
    public void mousePressed (MouseEvent e) {}

    @Override
    public void mouseReleased (MouseEvent e) {}

    @Override
    public void mouseExited (MouseEvent e) {}

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
            java.util.logging.Logger.getLogger(CheckersProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckersProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckersProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckersProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckersProgram().setVisible(true);
            }
        });
    }



}
