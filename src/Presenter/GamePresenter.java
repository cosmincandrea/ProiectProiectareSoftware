package Presenter;

import models.GameState;
import view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePresenter implements KeyListener {

    GameState gamestate;
    GameView gameView;

    public GamePresenter(GameState gamestate, GameView gameView) {
        this.gamestate = gamestate;
        this.gameView = gameView;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
