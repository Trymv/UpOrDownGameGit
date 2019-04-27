/**
 * The job of this class is to be a playing card.
 * So each card will have one out of fore categories:
 * diamond, heart, club or spade from 1-13,
 * where 11 is jack, 12 is queen, 13 is king and 1 is ace.
 * In this class the card will be giving a category and a number.
 *
 * @author TrymV
 * @version 0.1
 */
public class Card {
    private String name;
    private String category;
    private int number;

    /**
     * Constructor for objects of the class Card
     * Will set card number and category
     * @param name the card number
     * @param category the card category
     * @param number the number of the card 1-13
     * @throws IllegalArgumentException if name or category is set to null.
     */
    public Card(String name, String category, int number) {
        if(name != null || category != null) {
            this.name = name;
            this.category = category;
        }
        else {
            throw new IllegalArgumentException("Name or category was set to null");
        }
        this.number = number;
    }

    /**
     * Return name of the card
     * @return name of the card
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return category of the card
     * @return category of the card
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Return number of the card
     * @return number of the card
     */
    public int getNumber() {
        return this.number;
    }
}