package view;

import models.GameState;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private final int startX = 20;
    private final int startY = 20;
    private final int edgeSize = 10;
    private int level = 3;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public void drawGame(GameState gamestate){
        JFrame frame = new JFrame();
        JPanel panel= new JPanel(new GridLayout(gamestate.level,gamestate.level, 5,5));
        JLabel rows[][] = new JLabel[4][4];  //change ui content by managing these now

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
            ImageIcon icon = new ImageIcon("images/mice.png");
            rows[i][j] = new JLabel(icon);
            panel.add(rows[i][j]);
        }

//Example: To modify row 3 (i.e. rows[2])

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
