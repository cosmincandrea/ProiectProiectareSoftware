package view;

import Presenter.KeyPresenter;
import models.GameState;

import javax.swing.*;
import java.awt.*;

public class GameView {

    GameState gameState;

    public GameView(GameState gameState) {
        this.gameState = gameState;
    }

    public void drawGame(){
        JFrame frame = new JFrame();
        frame.setFocusable(true);
        frame.addKeyListener(new KeyPresenter(gameState));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel panel= new JPanel(new GridLayout(gameState.level,gameState.level, 5,5));
        JPanel score = new JPanel();
        JLabel rows[][] = new JLabel[gameState.level][gameState.level];  //change ui content by managing these now

        for(int i=0;i<gameState.level;i++)
            for(int j=0;j<gameState.level;j++){
            ImageIcon rabbit = new ImageIcon("images/mice.png");
            ImageIcon trap = new ImageIcon("images/trap.png");
            ImageIcon empty = new ImageIcon("images/empty.png");
            if (gameState.rabbitY == j && gameState.rabbitX == i)
                rows[i][j] = new JLabel(rabbit);
            else if (gameState.gameMatrix[i][j] == 2)
                rows[i][j] = new JLabel(trap);
            else
                rows[i][j] = new JLabel(empty);
            panel.add(rows[i][j]);
        }

//Example: To modify row 3 (i.e. rows[2])

        JLabel label = new JLabel("Score is 34");
        label.setText("NEw text");
        score.add(label);
        mainPanel.add(panel);
        mainPanel.add(score);
        frame.add(mainPanel);
        //frame.add(score);
        frame.pack();
        frame.setVisible(true);
    }



}
