
import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Deck deck = new Deck();
        ArrayList<Player> players = new ArrayList<>();

        System.out.println("================================");
        System.out.println("       WELCOME TO BLACKJACK");
        System.out.println("================================");

        // Ask how many players will participate
        int numberOfPlayers;

        while (true) {
            System.out.print("Enter the number of players (1-10): ");

            try {
                numberOfPlayers = Integer.parseInt(scanner.nextLine());

                if (numberOfPlayers >= 1 && numberOfPlayers <= 10) {
                    break;
                }

                System.out.println("Please enter a number between 1 and 10.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Get player names
        for (int i = 0; i < numberOfPlayers; i++) {

            System.out.print("Enter player " + (i + 1) + "'s name: ");

            String name = scanner.nextLine().trim();

            while (name.isEmpty()) {
                System.out.print("Name cannot be empty. Enter a name: ");
                name = scanner.nextLine().trim();
            }

            players.add(new Player(name));
        }

        // Create and shuffle the deck
        deck.shuffle();

        System.out.println("\nShuffling deck...");
        System.out.println("Dealing cards...\n");

        // Deal two cards to each player
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.getHand().addCard(deck.dealCard());
            }
        }

        // Display starting hands
        for (Player player : players) {

            System.out.println("\n" + player.getName() + "'s hand:");

            player.getHand().displayHand();
        }

        // Each player gets a turn
        for (Player player : players) {

            if (player.hasStayed() || player.isBusted()) {
                continue;
            }

            System.out.println("\n================================");
            System.out.println(player.getName() + "'s Turn");
            System.out.println("================================");

            boolean turnOver = false;

            while (!turnOver) {

                int score = player.getHand().calculateScore();

                if (score > 21) {
                    player.setBusted(true);
                    System.out.println(player.getName() + " busted!");
                    break;
                }

                System.out.println("\nYour current hand:");
                player.getHand().displayHand();

                System.out.println("\n1. Hit");
                System.out.println("2. Stay");
                System.out.print("Choose an option: ");

                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {

                    Card newCard = deck.dealCard();

                    if (newCard == null) {
                        System.out.println("The deck is empty!");
                        player.setStayed(true);
                        break;
                    }

                    player.getHand().addCard(newCard);

                    System.out.println("\nYou received: " + newCard);

                    if (player.getHand().isBust()) {
                        player.setBusted(true);
                        System.out.println("You went over 21! You busted!");
                        turnOver = true;
                    }

                } else if (choice.equals("2")) {

                    player.setStayed(true);
                    turnOver = true;

                    System.out.println(player.getName() + " stays.");

                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            }
        }

        // Display final results
        System.out.println("\n================================");
        System.out.println("          FINAL RESULTS");
        System.out.println("================================");

        int highestScore = 0;

        for (Player player : players) {

            int score = player.getHand().calculateScore();

            System.out.println("\n" + player.getName() + ":");

            player.getHand().displayHand();

            if (player.isBusted()) {
                System.out.println("Status: BUSTED");
            } else {
                System.out.println("Status: " + score);
            }

            if (!player.isBusted() && score > highestScore) {
                highestScore = score;
            }
        }

        // Determine the winner
        System.out.println("\n================================");
        System.out.println("           GAME WINNER");
        System.out.println("================================");

        if (highestScore == 0) {

            System.out.println("Nobody wins! All players busted.");

        } else {

            boolean foundWinner = false;

            for (Player player : players) {

                int score = player.getHand().calculateScore();

                if (!player.isBusted() && score == highestScore) {

                    System.out.println(player.getName()
                            + " wins with " + score + " points!");

                    foundWinner = true;
                }
            }
        }

        System.out.println("\nThanks for playing Blackjack!");

        scanner.close();
    }
}
