package Presenter;

import models.GameState;
import view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePresenter implements KeyListener {

    GameState gamestate;
    GameView gameView;

    public GamePresenter() {
        this.gamestate = new GameState(3);
        this.gameView = new GameView(this.gamestate, this);
    }

    public void init(){
        gameView.startGame();
        ///gameView.drawGame();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 's'){
            gamestate.rabbitY = gamestate.rabbitY - 1;
            System.out.println(gamestate.rabbitY);
             gameView.drawGame();
            System.out.println("Test");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
