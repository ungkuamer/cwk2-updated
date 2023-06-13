import java.util.Collections;

// Represents the collection of decks of cards that are not in play
public class Shoe extends CardCollection{

    int numOfDecks;

    // Shoe class constructor
    public Shoe(int numOfDecks) {

        super();

        // check if number of decks is valid
        if (numOfDecks == 6 || numOfDecks == 8) {
            this.numOfDecks = numOfDecks;
        } else {
            throw new CardException("Invalid Number of Decks");
        }

        // adding all the cards to the collection
        for (int i = 0; i < numOfDecks; i++) {
            for (Card.Suit s : Card.Suit.values()) {
                for (Card.Rank r : Card.Rank.values()) {
                    Card cardToAdd = new Card(r, s);
                    cards.add(cardToAdd);
                }
            }
        }

    }

    // returns the size of the Shoe
    public int size() {
        return super.size();
    }

    // randomize the order of the Shoe
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // check if the Shoe is empty and return the first card if not
    public BaccaratCard deal() {

        if (super.isEmpty()) {
            throw new CardException("Shoe is Empty");
        }

        Card currentCard = cards.remove(0);
        BaccaratCard cardToReturn = new BaccaratCard(currentCard.getRank(), currentCard.getSuit());

        return cardToReturn;
    }

}