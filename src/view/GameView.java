package view;

import ViewModel.GameVM;
import models.GameModel;
import models.GameState;

import javax.swing.*;
import java.awt.*;

public class GameView {

    GameState gameState;
    GameModel gameModel;
    GameVM gameVM;
    JFrame mainFrame;
    JPanel mainPanel;
    JPanel gamePanel;
    JPanel scorePannel;

    JLabel scorelabel;
    JLabel rows[][];

    public GameView(GameModel gameModel, GameVM gameVM) {
        this.gameModel = gameModel;
        this.gameState = gameModel.gamestate;
        this.gameVM = gameVM;
    }

    public void startGame(){
        mainFrame = new JFrame();
        mainFrame.setFocusable(true);
        mainFrame.addKeyListener(this.gameVM);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        gamePanel= new JPanel(new GridLayout(gameState.level,gameState.level, 5,5));
        rows = new JLabel[gameState.level][gameState.level];  //change ui content by managing these now

        scorelabel = new JLabel("Score is 34");
        scorePannel = new JPanel();
        scorePannel.add(scorelabel);

        drawGame(gameState.level);
        mainPanel.add(gamePanel);
        mainPanel.add(scorePannel);

        mainFrame.add(mainPanel);
        //frame.add(score);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void drawGame(int size){

        int iconSize = 500 / size;
        gamePanel.removeAll();
        scorelabel.setText("Score is "+gameState.score);
        for(int i=0;i<gameState.level;i++)
            for(int j=0;j<gameState.level;j++){
            ImageIcon rabbit = new ImageIcon("images/mice.png");
            ImageIcon trap = new ImageIcon("images/trap.png");
            ImageIcon empty = new ImageIcon("images/empty.png");
            ImageIcon food = new ImageIcon("images/food.png");
            Image imgRabbit = rabbit.getImage();
            Image imgTrap = trap.getImage();
            Image imgEmpty = empty.getImage();
            Image imgFood = food.getImage();
            ImageIcon rabbitFinal =  new ImageIcon(imgRabbit.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            ImageIcon trapFinal =  new ImageIcon(imgTrap.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            ImageIcon emptyFinal =  new ImageIcon(imgEmpty.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            ImageIcon foodFinal =  new ImageIcon(imgFood.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
            if (gameState.rabbitY == j && gameState.rabbitX == i)
                rows[i][j] = new JLabel(rabbitFinal);
            else if (gameState.gameMatrix[i][j] == 1)
                rows[i][j] = new JLabel(trapFinal);
            else if (gameState.isWin(i, j))
                rows[i][j] = new JLabel(foodFinal);
            else rows[i][j] = new JLabel(emptyFinal);
            gamePanel.add(rows[i][j]);
        }
        gamePanel.revalidate();

    }

    public void showPath(int size){
        mainFrame.setVisible(true);
        int iconSize = 500 / size;
        gamePanel.removeAll();
        scorelabel.setText("TOO LONG! This is the correct path");
        for(int i=0;i<gameState.level;i++)
            for(int j=0;j<gameState.level;j++){
                ImageIcon rabbit = new ImageIcon("images/mice.png");
                ImageIcon trap = new ImageIcon("images/trap.png");
                ImageIcon empty = new ImageIcon("images/empty.png");
                ImageIcon food = new ImageIcon("images/food.png");
                ImageIcon star = new ImageIcon("images/star.png");
                Image imgStar = star.getImage();
                Image imgRabbit = rabbit.getImage();
                Image imgTrap = trap.getImage();
                Image imgEmpty = empty.getImage();
                Image imgFood = food.getImage();
                ImageIcon rabbitFinal =  new ImageIcon(imgRabbit.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
                ImageIcon trapFinal =  new ImageIcon(imgTrap.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
                ImageIcon emptyFinal =  new ImageIcon(imgEmpty.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
                ImageIcon foodFinal =  new ImageIcon(imgFood.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
                ImageIcon starFinal = new ImageIcon(imgStar.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH));
                if (gameModel.vizMatrix[i][j] > 0)
                    rows[i][j] = new JLabel(starFinal);
                else if (gameState.rabbitY == j && gameState.rabbitX == i)
                    rows[i][j] = new JLabel(rabbitFinal);
                else if (gameState.gameMatrix[i][j] == 1)
                    rows[i][j] = new JLabel(trapFinal);
                else if (gameState.isWin(i, j))
                    rows[i][j] = new JLabel(foodFinal);
                else rows[i][j] = new JLabel(emptyFinal);
                gamePanel.add(rows[i][j]);
            }
        gamePanel.revalidate();
    }
    public void closeGame(){
        mainFrame.setVisible(false);
    }
    public void setVWinLabel(){
        scorelabel.setText("BRAVO! You found the shorthest path");
    }

}
