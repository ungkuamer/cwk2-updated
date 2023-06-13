// Represents the collection of BaccaratCards that are in play
public class BaccaratHand extends CardCollection {

    // constructor
    public BaccaratHand() {
        super();
    }

    // adds a new card to the hand
    public void add(BaccaratCard cardToAdd) {
        super.add(cardToAdd);
    }

    // returns the size of the hand
    public int size() {
        return super.size();
    }

    // converts the rank and suit of the card to a readable string format
    public String toString() {
        Card currentCard;
        StringBuilder stb = new StringBuilder();

        for (int i = 0; i < super.size(); i++) {
            currentCard = cards.get(i);
            stb.append(currentCard.toString());

            if (i != super.size()-1) {
                stb.append(" ");
            }
        }

        return stb.toString();
    }

    // calculate the value of the hand
    public int value() {
        // baccarat hand max value is 9
        int intDiv = super.value()/10;
        return super.value()-(intDiv*10);
    }

    // checks if the current hand is natural
    public boolean isNatural() {
        if (size() == 2 && (value() == 8 || value() == 9)) {
            return true;
        }

        return false;
    }

}