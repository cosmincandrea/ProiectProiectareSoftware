package models;

import java.util.PriorityQueue;

public class GameModel {

    public GameState gamestate;
    public int[][] vizMatrix = new int[100][100];
    public GameModel() {
        gamestate = new GameState(5);
    }

    public void newGame(int level){
        gamestate = new GameState(level);
    }
    public int[][] getMatrix(){
        return gamestate.gameMatrix;
    }
    public boolean isWin(int x, int y){
        return gamestate.isWin(x, y);
    }
    public boolean isTrap(int x, int y){
        return gamestate.isTrap(x, y);
    }
    public boolean isValidPosition(int x, int y){
        return gamestate.isValidPosition(x, y);
    }

    class Cell implements Comparable<Cell>{
        public Integer x, y, key, level;
        @Override
        public int compareTo(Cell o) {
            return this.key.compareTo(o.key);
        }
    }


    public int getShortestPath(){

        int dirX[] = {-1, -1, 1, 1};
        int dirY[] = {1, -1, -1, 1};
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                vizMatrix[i][j] = 0;
        int index = 0;
        PriorityQueue<Cell> queue = new PriorityQueue<Cell>();
        Cell current = new Cell();
        current.x = 0;
        current.level = 0;
        current.y = gamestate.level - 1;
        current.key = Math.abs(gamestate.foodX - current.x) + Math.abs(gamestate.foodY - current.y);
        queue.add(current);

        while(!queue.isEmpty()){
            Cell X = queue.poll();
            if (gamestate.isWin(X.x, X.y)) {
                vizMatrix[X.x][X.y] = index+1;
                break;
            }
            index++;
            for (int i = 0; i < 4; i++){
                int newX = X.x + dirX[i];
                int newY = X.y + dirY[i];
                vizMatrix[X.x][X.y] = index;
                if (gamestate.isValidPosition(newX, newY) && vizMatrix[newX][newY] == 0 && !isTrap(newX, newY))
                {
                    Cell neighbor = new Cell();
                    neighbor.x = newX;
                    neighbor.y = newY;
                    neighbor.key = Math.abs(gamestate.foodX - neighbor.x) + Math.abs(gamestate.foodY - neighbor.y);
                    queue.add(neighbor);
                }
            }
        }

        return index;
    }


}
