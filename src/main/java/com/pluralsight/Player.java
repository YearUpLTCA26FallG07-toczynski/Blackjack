
public class Player {

    private String name;
    private Hand hand;
    private boolean stayed;
    private boolean busted;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.stayed = false;
        this.busted = false;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean hasStayed() {
        return stayed;
    }

    public void setStayed(boolean stayed) {
        this.stayed = stayed;
    }

    public boolean isBusted() {
        return busted;
    }

    public void setBusted(boolean busted) {
        this.busted = busted;
    }
}
