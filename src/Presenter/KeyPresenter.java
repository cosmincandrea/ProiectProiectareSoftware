package Presenter;

import models.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPresenter implements KeyListener {

    GameState gameState;
    public KeyPresenter(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key T" + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key P" + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
