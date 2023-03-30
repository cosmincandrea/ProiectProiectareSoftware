package models;

public class GameState {

    public int[][] gameMatrix = new int[100][100];
    public int level;
    public int rabbitX, rabbitY;
    public int foodX, foodY;
    public GameState(int level) {
        this.level = level;
        this.rabbitX = 0;
        this.rabbitY = this.level - 1;
        this.foodX = this.level - 1;
        this.foodY = this.level - 1;
        for (int i = 0; i < this.level; i++)
            for (int j = 0; j < this.level; j++)
            {
                    gameMatrix[i][j] = 0;
            }
        gameMatrix[1][1] = 2;
    }

    public boolean isWin(int newX, int newY){
        return newX == foodX && newY == foodY;
    }
    public boolean isTrap(int newX, int newY){
        return gameMatrix[newX][newY] == 1;
    }

}
