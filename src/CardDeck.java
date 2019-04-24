import java.util.ArrayList;
import java.util.Random;

/**
 * This class will hold on objects of Card in a ArrayList.
 *
 * @author TrymV
 * @version 0.1
 */
public class CardDeck {
    private ArrayList<Card> deckOfCards;
    private Random rand;

    /**
     * Constructor for object of the class CardDeck
     */
    public CardDeck() {
        this.deckOfCards = new ArrayList<>();
        this.rand = new Random();
    }

    /**
     * Add a card to the deck
     * @param card the card to be added
     */
    public void addCard(Card card) {
        deckOfCards.add(card);
    }

    /**
     * Removes a card from the array list depending on the name and category
     * the user send inn with the parameters.
     * @param cardToBeRemovedName name of the card to be removed
     * @param cardToBeRemovedCategory category of the card to be removed
     */
    public void removeCardFromDeck(String cardToBeRemovedName, String cardToBeRemovedCategory) {
        boolean correctCardHasBeenFound = false;
        Card cardToBeDeleted = null;

        if(!deckOfCards.isEmpty()) {
            for (Card card : deckOfCards) {
                if (card.getName().equals(cardToBeRemovedName) && card.getCategory().equals(cardToBeRemovedCategory)) {
                    cardToBeDeleted = card;
                    correctCardHasBeenFound = true;
                }
            }
            if (correctCardHasBeenFound) {
                deckOfCards.remove(cardToBeDeleted);
            }
        }
    }

    /**
     * Check if there is any cards in the deck.
     * Will return true if the ArrayList cardDeck is empty.
     * @return true if cardDeck is empty
     */
    public boolean deckIsEmpty() {
        boolean deckIsEmpty = true;

        if(!deckOfCards.isEmpty()) {
            deckIsEmpty = false;
        }
        return deckIsEmpty;
    }

    /**
     * List all cards in the deck.
     * @return cardList with the category and name of each card in the deck
     */
    public String listAllCards() {
        StringBuilder cardList = new StringBuilder();

        for(Card card:deckOfCards) {
            cardList.append(card.getCategory()).append(" of ").append(card.getName()).append("\n");
        }
        return cardList.toString().trim();
    }

    /**
     * Get a random card from the card deck.
     * @return random card from the card deck
     */
    public Card getRandomCard() {
        Card cardToReturn = null;
        //Set the variable to a random number from 0 - the size of the array list.
        int randomNumber = rand.nextInt(deckOfCards.size());

        for(Card card:deckOfCards) {
            if(deckOfCards.indexOf(deckOfCards.get(randomNumber)) == deckOfCards.indexOf(card)) {
                cardToReturn = card;
            }
        }
        return cardToReturn;
    }

    /**
     * Clear the list "deckOfCards" from all objects.
     */
    public void emptyDeck() {
        deckOfCards.clear();
    }

    /**
     * Delete this
     * @return size of the deck.
     */
    public int getSize(){
        return deckOfCards.size();
    }
}