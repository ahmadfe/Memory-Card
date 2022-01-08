public class Player {
    
    private String name;
    private int score;
    private boolean myTurn;

    public Player(String name, boolean myTurn) {
        this.name = name;
        this.myTurn = myTurn;
        this.score = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int setScore(int score) {
        this.score = score;
        return this.score;
    }

    public int addScore() {
        return ++this.score;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
    
    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public boolean isMyTurn() {
        return this.myTurn;
    }
}