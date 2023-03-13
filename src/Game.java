import java.util.List;

public class Game {

    private int N;
    private int entityes;
    List<String> stringList;

    public Game(int n, int entityes, List<String> stringList) {
        N = n;
        this.entityes = entityes;
        this.stringList = stringList;
    }

    public int getN() {
        return N;
    }

    public int getEntityes() {
        return entityes;
    }

    public List<String> getStringList() {
        return stringList;
    }
}
