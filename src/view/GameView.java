package view;

import Presenter.GamePresenter;
import models.GameState;

import javax.swing.*;
import java.awt.*;

public class GameView {

    GameState gameState;
    GamePresenter gamePresenter;
    JFrame mainFrame;
    JPanel mainPanel;
    JPanel gamePanel;
    JPanel scorePannel;
    JLabel scorelabel;
    JLabel rows[][];

    public GameView(GameState gameState, GamePresenter gamePresenter) {
        this.gameState = gameState;
        this.gamePresenter = gamePresenter;
    }

    public void startGame(){
        mainFrame = new JFrame();
        mainFrame.setFocusable(true);
        mainFrame.addKeyListener(this.gamePresenter);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        gamePanel= new JPanel(new GridLayout(gameState.level,gameState.level, 5,5));
        rows = new JLabel[gameState.level][gameState.level];  //change ui content by managing these now

        scorelabel = new JLabel("Score is 34");
        scorePannel = new JPanel();
        scorePannel.add(scorelabel);

        drawGame();
        mainPanel.add(gamePanel);
        mainPanel.add(scorePannel);

        mainFrame.add(mainPanel);
        //frame.add(score);
        mainFrame.pack();
        mainFrame.setVisible(true);


    }

    public void drawGame(){

        gamePanel.removeAll();
        scorelabel.setText("Score is "+gameState.score);
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
            gamePanel.add(rows[i][j]);
        }
        gamePanel.revalidate();

    }
    public void closeGame(){
        mainFrame.setVisible(false);
    }

}
