package Controller;

import models.GameModel;
import models.GameState;
import models.repo.UserDao;
import view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {

    GameModel gamemodel;
    GameView gameView;
    public String lang;
    public MainController mainVM;
    UserDao userDao;
    boolean fin = false;

    public GameController(MainController mainVM) {
        this.mainVM = mainVM;
        this.userDao = new UserDao();
    }

    public void init(int lvl, String lang){
        fin = false;
        this.lang = lang;
        this.gamemodel = new GameModel();
        gamemodel.newGame(lvl);
        this.gameView = new GameView(this.gamemodel, this);
        gameView.startGame();
    }


    @Override
    public void keyTyped(KeyEvent e) {

        GameState gamestate = gamemodel.gamestate;
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
        if (e.getKeyChar() == 'x'){
            gameView.closeGame();
            return;
        }

        int event = checkEvent(newX, newY);
        if (event == 1){
            fin = true;
            gameView.closeGame();
        }
        if (event == 2){
            fin = true;
            int len =  gamemodel.getShortestPath();
            if (gamestate.score <= len) {
                gameView.drawGame(gamestate.level);
                gameView.setVWinLabel(true, mainVM.lang);
                gamestate.score = len;
            }
            else
                gameView.showPath(gamestate.level);

            int finalScore = (len * 100) / gamestate.score;
            mainVM.currentUser.setScore(finalScore);
            mainVM.updateGreeting();
            userDao.updateUser(mainVM.currentUser);
        }

        if (!gamestate.isValidPosition(newX, newY))
            return;

        gamestate.rabbitX = newX;
        gamestate.rabbitY = newY;
        gamestate.score++;
//        if (gamestate.score > 5)
//            gameView.closeGame();
        if (!fin)
            gameView.drawGame(gamestate.level);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private int checkEvent(int x, int y){
        if (!gamemodel.isValidPosition(x, y))
            return 0;
        if (gamemodel.isTrap(x, y))
            return 1;
        if (gamemodel.isWin(x, y))
            return 2;
       return 0;
    }

}
