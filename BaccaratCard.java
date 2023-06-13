// Represents the single instance of a card in the game
public class BaccaratCard extends Card{

    // constructor
    public BaccaratCard(Rank rank, Suit suit) {
        super(rank, suit);
    }

    // returns the rank of the card
    public Rank getRank() {
        return super.getRank();
    }

    // returns the suit of the card
    public Suit getSuit() {
        return super.getSuit();
    }

    // converts the rank and suit in a readable string format
    public String toString() {
        return super.toString();
    }

    // calculate the value of the card
    public int value() {
        // 9 is the highest value for a card
        if (super.value() > 9) {
            return 0;
        }
        return super.value();
    }

    // compare between two cards
    public int compareTo(BaccaratCard cardToCompare) {
        Rank currentRank = super.getRank();
        Rank compareRank = cardToCompare.getRank();
        Suit currentSuit = super.getSuit();
        Suit compareSuit = cardToCompare.getSuit();

        // different rank
        if (currentRank.compareTo(compareRank) > 0) {
            if (currentSuit.compareTo(compareSuit) < 0) {
                return -1;
            }
            return 1;
        }

        if (currentRank.compareTo(compareRank) < 0) {
            return -1;
        }

        // same rank different suit
        if (currentRank == compareRank) {
            if (currentSuit.compareTo(compareSuit) > 0) {
                return 1;
            }
            if (currentSuit.compareTo(compareSuit) < 0) {
                return -1;
            }
        }

        return 0;
    }

}