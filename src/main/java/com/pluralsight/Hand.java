
import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        if (card != null) {
            cards.add(card);
        }
    }

    public int calculateScore() {
        int score = 0;
        int aceCount = 0;

        for (Card card : cards) {
            score += card.getValue();

            if (card.getRank().equals("Ace")) {
                aceCount++;
            }
        }

        // Change Aces from 11 to 1 when necessary
        while (score > 21 && aceCount > 0) {
            score -= 10;
            aceCount--;
        }

        return score;
    }

    public boolean isBust() {
        return calculateScore() > 21;
    }

    public void displayHand() {
        for (Card card : cards) {
            System.out.println("  " + card);
        }

        System.out.println("  Total: " + calculateScore());
    }
}
