
/**
 * Created with IntelliJ IDEA.
 * User: Andreea-Daniela Ene
 * Date: 12/2/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */

import java.awt.*;
import javax.swing.*;

public class CheckersProgram
{
    public static int rows = 8;
    public static int columns = 8;
    public static Color col1 = Color.BLACK;
    public static Color col2 = Color.RED;
    public static void main(String[] args)
    {
        JFrame checkersBoard = new JFrame();
        checkersBoard.setSize(800, 800);
        checkersBoard.setTitle("CheckersBoard");
        Container pane = checkersBoard.getContentPane();
        pane.setLayout(new GridLayout(rows, columns));
        Color temp;
        for (int i = 0; i < rows; i++)
        {
            temp = (i%2 == 0) ? col1 : col2;
            for (int j = 0; j < columns; j++)
            {
                JPanel panel = new JPanel();
                panel.setBackground(temp);
                temp = (temp.equals(col1)) ? col2 : col1 ;
                pane.add(panel);
            }
        }
        checkersBoard.setVisible(true);
    }

}
