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

        int newX = gamestate.rabbitX;
        int newY = gamestate.rabbitY;

        if (e.getKeyChar() == 's'){
            newX = newX + 1;
            newY = newY - 1;
        }
        if (e.getKeyChar() == 'w') {
            newX = newX - 1;
            newY = newY - 1;
        }
        if (e.getKeyChar() == 'e'){
            newX = newX - 1;
            newY = newY + 1;
        }
        if (e.getKeyChar() == 'd') {
            newX = newX + 1;
            newY = newY + 1;
        }

        if (!gamestate.isValidPosition(newX, newY))
            return;

        gamestate.rabbitX = newX;
        gamestate.rabbitY = newY;
        gamestate.score++;
//        if (gamestate.score > 5)
//            gameView.closeGame();
        gameView.drawGame();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
