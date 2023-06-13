import java.util.Scanner;

public class Baccarat {

    // class that contains different attributes, mechanics and logic of the game
    public static class Gameplay {
        int roundCount, playerWin, bankerWin, ties, whoWin, thirdCard;
        Shoe gameShoe;
        BaccaratHand player, banker;
        Card nextCard;

        // constructor
        public Gameplay() {
            gameShoe = new Shoe(6);
            gameShoe.shuffle();

            roundCount = 1;
            playerWin = 0;
            bankerWin = 0;
            ties = 0;
        }

        // starts a new round with a clean hand
        public void newRound() {
            player = new BaccaratHand();
            banker = new BaccaratHand();

            for (int i = 0; i < 2; i++) {
                player.add(gameShoe.deal());
                banker.add(gameShoe.deal());
            }
        }

        // checks card value and if a third card is needed
        public void gameLogic() {
            thirdCard = 0;

            if (player.value() < 6) {
                nextCard = gameShoe.deal();
                player.add(nextCard);
                thirdCard = 1;

                if (banker.value() < 3) {
                    banker.add(gameShoe.deal());
                }

                if (banker.value() == 3) {
                    if (nextCard.value() != 8) {
                        banker.add(gameShoe.deal());
                    }
                }

                if (banker.value() == 4) {
                    if (nextCard.value() > 1 && nextCard.value() < 8) {
                        banker.add(gameShoe.deal());
                    }
                }

                if (banker.value() == 5) {
                    if (nextCard.value() > 3 && nextCard.value() < 8) {
                        banker.add(gameShoe.deal());
                    }
                }

                if (banker.value() == 6) {
                    if (nextCard.value() == 6 || nextCard.value() == 7) {
                        banker.add(gameShoe.deal());
                    }
                }

            } else if (player.value() > 5 && banker.value() < 5) {
                banker.add(gameShoe.deal());
                thirdCard = 2;
            }

            roundCount += 1;
        }

        // checks with side wins the current round
        public void winGame() {
            whoWin = 0;

            if (player.value() > banker.value()) {
                playerWin += 1;
                whoWin = 1;
            } else if (player.value() < banker.value()) {
                bankerWin += 1;
                whoWin = 2;
            } else if (player.value() == banker.value()) {
                ties += 1;
                whoWin = 3;
            }
        }

        // prints both side hand value
        public void printValue() {
            System.out.format("Player: %s = %d\n", player.toString(), player.value());
            System.out.format("Banker: %s = %d\n", banker.toString(), banker.value());
        }

        // prints the summary of the number of rounds played
        public void summary() {
            if (roundCount == 1) {
                System.out.format("\n%d round played\n", roundCount);
            } else {
                System.out.format("\n%d rounds played\n", roundCount-1);
            }

            System.out.format("%d player wins\n", playerWin);
            System.out.format("%d banker wins\n", bankerWin);
            System.out.format("%d ties\n", ties);
        }
    }

    // main program
    public static void main(String[] args) {
        boolean gameMode = false;
        // starts the game
        Gameplay currentGame = new Gameplay();

        // check which mode does the user chose
        if (args.length > 0) {
            if (args[0].equals("-i") || args[0].equals("--interactive")) {
                gameMode = true;
            }
        }

        // interactive mode
        if (gameMode) {
            boolean endGame = false;

            while (!endGame) {
                currentGame.newRound();

                System.out.format("\nRound %d\n", currentGame.roundCount);

                currentGame.printValue();
                currentGame.gameLogic();
                if (currentGame.thirdCard == 1) {
                    System.out.print("Dealing third card to player...\n");
                    currentGame.printValue();
                } else if (currentGame.thirdCard == 2) {
                    System.out.print("Dealing third card to banker...\n");
                    currentGame.printValue();
                }

                currentGame.winGame();

                if (currentGame.whoWin == 1) {
                    System.out.print("Player win!\n");
                } else if (currentGame.whoWin == 2) {
                    System.out.print("Banker win!\n");
                } else if (currentGame.whoWin == 3) {
                    System.out.print("Tie\n");
                }

                // checks if the user would like to continue or not
                System.out.print("Another round? (y/n): ");
                Scanner scanner = new Scanner(System.in);
                String answer = scanner.next();
                endGame = !answer.equals("y") && !answer.equals("Y");

                // end game if Shoe has less than 6 cards
                if (currentGame.gameShoe.size() < 6) {
                    endGame = true;
                }
            }

        } else { // non-interactive mode
            boolean endGame = false;

            while (!endGame) {
                currentGame.newRound();
                currentGame.gameLogic();
                currentGame.winGame();

                if (currentGame.gameShoe.size() < 6) {
                    endGame = true;
                }
            }
        }

        currentGame.summary();
    }

}
