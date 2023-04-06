package Presenter;

import models.GameState;
import models.User;
import models.repo.UserDao;
import view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePresenter implements KeyListener {

    GameState gamestate;
    GameView gameView;
    MainPresenter mainPresenter;
    UserDao userDao;

    public GamePresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        this.userDao = new UserDao();
    }

    public void init(int lvl){
        this.gamestate = new GameState(lvl);
        this.gameView = new GameView(this.gamestate, this);
        gameView.startGame();
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

        int event = checkEvent(newX, newY);
        if (event == 1){
            gameView.closeGame();
        }
        if (event == 2){
            gameView.closeGame();
            mainPresenter.currentUser.setScore(gamestate.score);
            System.out.println(mainPresenter.currentUser);
            mainPresenter.updateGreeting();
            userDao.updateUser(mainPresenter.currentUser);
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

    private int checkEvent(int x, int y){
        if (!gamestate.isValidPosition(x, y))
            return 0;
        if (gamestate.isTrap(x, y))
            return 1;
        if (gamestate.isWin(x, y))
            return 2;
       return 0;
    }

}
