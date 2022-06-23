package client;

public class Player {

    public String name;
    private final boolean bot;

    public Player(boolean bot) {
        this.bot = bot;
        if (bot){
            name = "Bob";
        }
    }

    public Player(boolean bot, String name) {
        this.bot = bot;
    }

    public Move getNextMove(){
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
