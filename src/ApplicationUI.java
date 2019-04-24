import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Makes up the user interface (text based) of the application.
 * Responsible for all user interaction, like displaying the menu
 * and receiving input from the user.
 *
 * @author TrymV
 * @version 0.1
 */
public class ApplicationUI {
    private CardDeck cardDeck;
    private PlayerList playerList;
    private Scanner reader;
    private Event event;

    /**
     * The menu of the game witch will be displayed when there is a choice to be made.
     */
    private String[] gameMenu = {
            "1. Do a round",
            "2. List cards in deck",
            "3. ShowStats",
            "4. List all players",
            "5. Restart game",
            "6. Settings",
            "7. Help",
    };

    /**
     * The menu of game settings you can change.
     * For example add or remove players.
     */
    private String[] settingsMenu = {
            "1. Add player",
            "2. Remove player",
            "3. Event settings",
            "4. Back to main menu",
    };

    /**
     * The event menu where you can change
     * the event settings.
     */
    private String[] eventMenu = {
            "1. Turn event on or off",
            "2. Set event chance",
            "3. ???",
            "4. Back to settings",
    };

    /**
     * The constructor of the application.
     */
    public ApplicationUI() {
        this.cardDeck = new CardDeck();
        this.playerList = new PlayerList();
        this.event = new Event(playerList);
    }

    /**
     * This method is what controls the game.
     * Here you will be available to play and reset the game.
     */
    public void start() {
        boolean quit = false;
        makeDeckWithCard();

            while (!quit) {
                if (playerList.isListEmpty()) {
                    System.out.println("You need at least one player to play");
                    addNewPlayer();
                }
                else {
                try {
                    int menuSelection = this.showMenu("gameMenu");
                    switch (menuSelection) {

                        case 1: //Do a round
                            System.out.println("How many rounds do you witch to play through? (1-5)");
                            playGame(readNextInt());
                            break;

                        case 2: //List all cards in deck
                            System.out.println(cardDeck.listAllCards());
                            break;

                        case 3: //List the score of every player
                            System.out.println(playerList.getListWithPlayerScores());
                            break;

                        case 4: //List all players
                            listAllPlayers();
                            break;

                        case 5: //Restart game
                            resetGame();
                            break;

                        case 6: //Setting menu
                            settings();
                            break;

                        case 7: //Help
                            System.out.println(playerList.listAllPlayersWithPunishment());
                            break;

                        case 8: //Exit game
                            System.out.println("\nThank you for playing UpOrDown!\n");
                            quit = true;
                            break;

                        default:

                    }
                } catch (InputMismatchException ime) {
                    System.out.println("\nERROR: Please provide a number between 1 and " + this.gameMenu.length + "..\n");
                }
            }
        }
    }

    /**
     * In this menu you should be available to change settings of the game and add/remove players.
     */
    private void settings() {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            try {
                int menuSelection = this.showMenu("settingsMenu");
                switch (menuSelection) {

                    case 1: //Add player
                        addNewPlayer();
                        break;

                    case 2: //Remove player
                        removePlayer();
                        break;

                    case 3: //Event settings
                        eventSettings();
                        break;

                    case 4: //Back to main menu
                        backToMainMenu = true;
                        break;

                    default:

                }
            }
            catch (InputMismatchException ime) {
                System.out.println("\nERROR: Please provide a number between 1 and " + this.settingsMenu.length + "..\n");
            }
        }
    }

    /**
     * In this menu you should be available to change settings of the game and add/remove players.
     */
    private void eventSettings() {
        boolean backToSettings = false;

        while (!backToSettings) {
            try {
                int menuSelection = this.showMenu("eventMenu");
                switch (menuSelection) {

                    case 1: //Turn event on or off.
                        turnEventOnOrOff();
                        break;

                    case 2: //Set the chance for event to incur.
                        setEventChance();
                        break;

                    case 3:
                        break;

                    case 4: //Back to settings menu
                        backToSettings = true;
                        break;

                    default:
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("\nERROR: Please provide a number between 1 and " + this.eventMenu.length + "..\n");
            }
        }
    }

    /**
     * Display a menu depending on what parameter sent inn.
     * The menu will be displayed for the user and
     * there will be expected a input between 1 and the max number of menu items.
     * If the user input anything else, an InputMismatchException is thrown.
     * The method will return the valid input from the user.
     * @param menuType name of the menu to be shown
     * @return the menu to be shown
     * @throws InputMismatchException if the user input something invalid
     */
    private int showMenu(String menuType) throws InputMismatchException {
        int maxMenuItemNumber = 0;

        switch(menuType) {

            case "gameMenu":
                System.out.println("\n**** Please choose what to do next ****\n");

                //Display the menu for the user.
                for (String menuItem : gameMenu) {
                    System.out.println(menuItem);
                }
                maxMenuItemNumber = gameMenu.length + 1;
                // Add the "Exit"-choice to the menu
                System.out.println(maxMenuItemNumber + ". Exit\n");
                System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");
                // Read input from user
                break;

            case "settingsMenu":
                System.out.println("\n**** Please choose what to do next ****\n");

                for(String menuItem: settingsMenu) {
                    System.out.println(menuItem);
                }
                maxMenuItemNumber = settingsMenu.length;
                System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");
                break;

            case "eventMenu":
                System.out.println("\n**** Please choose what to do next ****\n");

                for(String menuItem: eventMenu) {
                    System.out.println(menuItem);
                }
                maxMenuItemNumber = eventMenu.length;
                System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");
                break;

                default:
        }

        int menuSelection = readNextInt();
        if ((menuSelection < 1) || (menuSelection > maxMenuItemNumber)) {
            throw new InputMismatchException();
        }
        return menuSelection;
    }

    /**
     * This method will make 13 cards in each category: diamond, heart, club and spade.
     * Then the cards will be added to the card deck.
     */
    private void makeDeckWithCard() {
        if (cardDeck.deckIsEmpty()) {
            for (int category = 1; category <= 4; category++) {
                //Sets the name of the card to either diamond, heart, club or spade
                String cardCategory = setCardCategory(category);

                for (int number = 1; number <= 13; number++) {
                    //Sets the name of the card to a number, except if the number is 1, 11, 12 or 13.
                    cardDeck.addCard(new Card(setCardName(number), cardCategory, number));
                }
            }
            System.out.println("Deck with card was successfully created.");
        }
        else {
            System.out.println("ERROR, deck still got cards left.");
        }
    }

    /**
     * Return the category of the cards depending on input number.
     * 1 will return diamond, 2 will return heart, 3 will return club and 4 will return spade.
     * @param category a number between 1 and 4 to set category of the card
     * @return category of the card depending on input number
     */
    private String setCardCategory(int category) {
        String setCategory;

        switch(category) {
            case 1:
                setCategory = "Diamond";
                break;

            case 2:
                setCategory = "Heart";
                break;

            case 3:
                setCategory = "Club";
                break;

            case 4:
                setCategory = "Spade";
                break;

            default: //Default should not happen
                setCategory = "ERROR";
                break;
        }
        return setCategory;
    }

    /**
     * This method will change the name of the card if the card has the number 1, 11, 12 or 13.
     * @param cardNumber the number of the card
     * @return name of the card as String
     */
    private String setCardName(int cardNumber) {
        String cardName;
        switch(cardNumber) {
            case 1: //Set card name to "Ace" if cardNumber is 1.
                cardName = "Ace";
                break;

            case 11: //Set card name to "Jack" if cardNumber is 11.
                cardName = "Jack";
                break;

            case 12: //Set card name to "Queen" if cardNumber is 12.
                cardName = "Queen";
                break;

            case 13: //Set card name to "King" if cardNumber is 13.
                cardName = "King";
                break;

                default: //Set card name to card number.
                    cardName = Integer.toString(cardNumber);
                    break;
        }
        return cardName;
    }

    /**
     * Play through the game for each player in the player list.
     * Each player will do one game each.
     * You can for through more game depending on parameter.
     * readyForPunishCheck is made to be sure the check will only be done if
     * at least one round has been done.
     * @param amountOfGames the amount of game to play through.
     */
    private void playGame(int amountOfGames) {
        boolean readyForPunishCheck = false;
        if (amountOfGames > 5) {
            amountOfGames = 5;
        }
        for (int games = 0; games < amountOfGames; games++) {
            for (int i = 0; i < playerList.getListSize(); i++) {
                String player = playerList.getPlayerNameWithIndex(i);
                doAGameRound(player);
                readyForPunishCheck = true;
            }
        }
        if (readyForPunishCheck) {
            punishmentCheck();
        }
    }

    /**
     * Checks if any player has a punishment.
     * If at least one player has a judgment this method will list all player with
     * a judgment.
     */
    private void punishmentCheck() {
        if(playerList.anyPlayersWithPunishment()) {
            System.out.println("Judgment day is upon us! \n" + playerList.listAllPlayersWithPunishment() + "All listed players have to do a penalty!");
        }
        else {
            System.out.println("No judgment today.");
        }
    }

    /**
     * Do a round where you get 2 chances to guess the card.
     * If incorrect on the first guess you get another guess with a little help.
     */
    private void doAGameRound(String player) {
        Card cardToBeGuessed = cardDeck.getRandomCard();
        String eventText = event.doRandomEvent(playerList.getPlayerWithName(player));
        System.out.println(eventText);

        System.out.println(player + " guess what card between 1 and 13:");
        int userAnswer = readNextInt();
        userAnswer = checkUserAnswer(userAnswer);

        if(!cardDeck.deckIsEmpty()) {
            if (userAnswer == cardToBeGuessed.getNumber()) {
                System.out.println(player + " got it on first try!\n" + "The card was: " + cardToBeGuessed.getNumber() + " of " + cardToBeGuessed.getCategory() + "\n");
                cardDeck.removeCardFromDeck(cardToBeGuessed.getName(), cardToBeGuessed.getCategory());
                playerList.changePlayerScore(player, 3);
            }
            else {
                if (userAnswer < cardToBeGuessed.getNumber()) {
                    System.out.println("Up: ");
                }
                else {
                    System.out.println("Down: ");
                }
                userAnswer = readNextInt();
                userAnswer = checkUserAnswer(userAnswer);
                if (userAnswer == cardToBeGuessed.getNumber()) {
                    System.out.println(player + " got it!\n" + "The card was: " + cardToBeGuessed.getNumber() + " of " + cardToBeGuessed.getCategory()+ "\n");
                    cardDeck.removeCardFromDeck(cardToBeGuessed.getName(), cardToBeGuessed.getCategory());
                    playerList.changePlayerScore(player, 1);
                }
                else {
                    System.out.println(player + " didn't get it!\n" + "The card is: " + cardToBeGuessed.getNumber() + " of " + cardToBeGuessed.getCategory() + "\n");
                    playerList.changePlayerScore(player, -1);
                }
            }
        }
        else {
            System.out.println("The deck is empty!");
        }
    }

    /**
     * Checks if user input is in range of 1-13.
     * If value is under 1 it will be set to 1. If above it will be set to 13.
     * @param userAnswer the value to be checked.
     * @return same value as parameter if value is valid (1-13). If not it will be set to either 1 or 13.
     */
    private int checkUserAnswer(int userAnswer) {
        int returnValue = userAnswer;
        if(userAnswer <= 0) {
            returnValue = 1;
            System.out.println("The value was too low. Value has to be between 1-13. So it was set to 1.");
        }
        else if(userAnswer > 13) {
            returnValue = 13;
            System.out.println("The value was too high. Value has to be between 1-13. So it was set to 13.");
        }
        return returnValue;
    }

    /**
     * This method will first empty the rest of the card deck before refilling it again.
     * You will also be asked if you are sure you want to reset the game.
     */
    private void resetGame() {
        boolean waitingForAnswer = true;

        while(waitingForAnswer) {
            System.out.println("Are you sure you want to reset the game? (yes or no)");
            String answer = readNextLine();
            if(answer.equals("yes")) {
                System.out.println("Game has been restarted.");
                cardDeck.emptyDeck();
                makeDeckWithCard();
                waitingForAnswer = false;
            }
            else if(answer.equals("no")) {
                System.out.println("Game will not be restarted.");
                waitingForAnswer = false;
            }
        }
    }

    /**
     * Read next line from the user input.
     * If no player with this name exist the player will be made and added to list.
     * You also need at least one alphabetic in the player name.
     */
    private void addNewPlayer() {
        System.out.println("Enter name of player you want to add:");
        String playerName = readNextLine().trim();
        boolean atLeastOneAlphabetic = playerName.matches(".*[a-zA-Z]+.*");

        if(atLeastOneAlphabetic) {
            if (!playerList.doesThePlayerExist(playerName)) {
                playerList.addPlayer(new Player(playerName));
                System.out.println(playerName + " was successfully added to the game.");
            } else {
                System.out.println("Player with the name " + playerName + " already exist.");
            }
        }
        else {
            System.out.println("You need at least one alphabetic in the player name.");
        }
    }

    /**
     * Read next line from the user input.
     * If this player exist in playerList, the player will be removed.
     */
    private void removePlayer() {
        System.out.println("Enter name of player you want to remove:");
        String playerToBeRemoved = readNextLine();

        if(playerList.doesThePlayerExist(playerToBeRemoved)) {
            playerList.removePlayer(playerToBeRemoved);
            System.out.println(playerToBeRemoved + " was successfully removed from the game.");
        }
        else {
            System.out.println("No player with the name " + playerToBeRemoved + " exist.");
        }
    }

    /**
     * List all players in the game/list.
     */
    private void listAllPlayers() {
        System.out.println("The players in the game is:\n" + playerList.listAllPlayers());
    }

    /**
     * With this method you can turn event on or off depending on it's state.
     */
    private void turnEventOnOrOff() {
        if(event.isEventOn()) {
            System.out.println("Event is currently on. Do you witch to turn it off? (yes or no)");
            if(readNextLine().equals("yes")) {
                event.setEventState(false);
                System.out.println("Event is now turned off.");
            }
            else {
                System.out.println("Event will remain turned on.");
            }
        }
        else {
            System.out.println("Event is currently off. Do you witch to turn it on? (yes or no");
            if(readNextLine().equals("yes")) {
                event.setEventState(true);
                System.out.println("Event is now turned on.");
            }
            else {
                System.out.println("Event will remain turned off.");
            }
        }
    }

    /**
     * Sets the chance for event to incur.
     * 1 will be 1%, 50 will be 50% and 100 will give 100% chance.
     * Can only be set to number between 1 and 100.
     */
    private void setEventChance() {
        System.out.println("The chance for a event is currently " + event.getEventChance() + "%." + "\nPlease enter chance for event (1-100):");
        event.setChanceForEvent(readNextInt());
        System.out.println("Event has now a " + event.getEventChance() + "% chance to incur.");
    }

    /**
     * Read the next number from the user input from the terminal.
     * If the input is not a number this method will return 0.
     * @return the next int from the terminal.
     */
    private int readNextInt() {
        int lineToBeRead = 0;
        try {
            this.reader = new Scanner(System.in);
            lineToBeRead = reader.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("ERROR! Input has to be a number.");
        }
        return lineToBeRead;
    }

    /**
     * Read the next line of text the user input into the terminal.
     * @return the line of text from the user.
     */
    private String readNextLine() {
        this.reader = new Scanner(System.in);
        return reader.nextLine();
    }
}