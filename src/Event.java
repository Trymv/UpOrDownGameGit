import java.util.Random;

/**
 * This class takes inn a player and the full player list.
 * If an event incur either the player or every player will be affected with either loosing points or gaining points.
 * Some events could also do special things like making a rule.
 */
public class Event {
    private boolean eventIsOn;
    private PlayerList playerList;
    private Random rand;
    private int chanceForEventToIncur;

    /**
     * Constructor for objects of the class Event.
     */
        public Event(PlayerList playerList) {
            if(playerList != null) {
                this.playerList = playerList;
            }
            else {
                throw new IllegalArgumentException("playerList in event was null.");
            }
            this.eventIsOn = true;
            this.rand = new Random();
            this.chanceForEventToIncur = 50;
        }

    /**
     * Return the state of the event.
     * If the event is on or off.
     * @return true if event is on.
     */
    public boolean isEventOn() {
            return this.eventIsOn;
    }

    /**
     * Send in true to turn event on or false to turn event off.
     * @param state true og false depending on if you want event on or off.
     */
    public void setEventState(boolean state) {
        eventIsOn = state;
    }

    /**
     * Set the chance for an event to incur.
     * Example: 100 is 100%, 50 is 50% and 1 is 1%.
     * If chance is set to over 100 the it will be set to 100.
     * If set to less than 1 it will be set to 1.
     * @param changeForEvent the chance for an event.
     */
    public void setChanceForEvent(int changeForEvent) {
        this.chanceForEventToIncur = changeForEvent;
        if(changeForEvent > 100) {
            this.chanceForEventToIncur = 100;
        }
        else if(changeForEvent < 1) {
            this.chanceForEventToIncur = 1;
        }
    }

    /**
     * Returns the chance for event to incur.
     * 100 is 100%, 50 is 50%, 1 is 1% and so on.
     * @return the chance for event to incur.
     */
    public int getEventChance() {
        return this.chanceForEventToIncur;
    }

    /**
     * If random number rolls the same number as chanceForEventToIncur is and
     * event is on a random event will be chosen with another random
     * number roll.
     * @param playerToBeAffected the player for event to affect.
     */
    public String doRandomEvent(Player playerToBeAffected) {
        String eventInfo = "No events today.";
        if (rand.nextInt(100)+1 <= chanceForEventToIncur && isEventOn()) {
            //The number in this "randomEvent" has to be the same as number of cases.
            int randomEvent = rand.nextInt(3)+1;

            switch (randomEvent) {
                case 1: //Positive event witch will give points to the player.
                    eventInfo = positivePointEvent(playerToBeAffected);
                    break;

                case 2: //Negative event witch will subtract points from the player.
                    eventInfo = negativePointEvent(playerToBeAffected);
                    break;

                case 3: //Could be good and bad.
                    eventInfo = oneOrThreeGuesses(playerToBeAffected);
                    break;

                default:
                    eventInfo = "Your lucky day! An error occurred.";

            }
        }
        if(!isEventOn()) {
            eventInfo = "Event is currently turned off.";
        }
        return eventInfo;
    }

    //***** Positive Events *****

    /**
     * Does a roll and affect the player with a positive point score.
     * The roll will result in different outcomes.
     * @param playerToBeAffected the player to be affected.
     * @return a string with result information.
     */
    private String positivePointEvent(Player playerToBeAffected) {
        int randomNumber = rand.nextInt(100) + 1;
        String eventInfo = "Error";

        if (randomNumber <= 50) {
            playerToBeAffected.setScore(1);
            eventInfo = playerToBeAffected.getName() + " feels just a little bit better. (+1 point)";
        }
        else if(randomNumber <= 80) {
            playerToBeAffected.setScore(2);
            eventInfo = playerToBeAffected.getName() + " has to slow down! (+2 points)";
        }
        else if(randomNumber <= 85) {
            playerToBeAffected.setScore(3);
            eventInfo = playerToBeAffected.getName() + " dude you are wasted... (+3 points)";
        }
        else if (randomNumber <= 100) { //Natural event witch will either double the points of the affected player or to everyone.
            eventInfo = doublePointEvent(playerToBeAffected);
        }
        return eventInfo;
    }

    //***** End of Positive Events *****
    //***** Natural Events *****

    /**
     * Does a roll and affect either the player or every player in the game by doubling points.
     * The roll will result in different outcomes.
     * @param playerToBeAffected the player to be affected.
     * @return a string with result information.
     */
    private String doublePointEvent(Player playerToBeAffected) {
        int randomNumber = rand.nextInt(100)+1;
        String eventInfo;

        if(randomNumber <= 50) {
            playerToBeAffected.doubleScore();
            eventInfo = playerToBeAffected.getName() + " got some new wisdom! (Points double)";
        }
        else {
            playerList.doubleScoreOfEveryPlayer();
            eventInfo = playerToBeAffected.getName() + " shares his wisdom! (Points of every player double's)";
        }
        return eventInfo;
    }

    /**
     * Write and return the event text depending on a roll.
     * This is natural since it could be both positive and negative.
     * @param playerToBeAffected the player to be affected.
     * @return a string with result information.
     */
    private String oneOrThreeGuesses(Player playerToBeAffected) {
        int randomNumber = rand.nextInt(100)+1;
        String eventInfo;

        if(randomNumber <=60) {
            //If this text get changed the oneOrThreeGuesses wont work except if it's also changed in the UI!
            eventInfo = "Bad luck! Only one guess for " + playerToBeAffected.getName() + ".";
        }
        else {
            //If this text get changed the oneOrThreeGuesses wont work except if it's also changed in the UI!
            eventInfo = playerToBeAffected.getName() + " got an extra guess!";
        }
        return eventInfo;
    }

    //***** End of Natural Events *****
    //***** Negative Events *****

    /**
     * Does a roll and affect the player with a negative point score.
     * The roll will result in different outcomes.
     * @param playerToBeAffected the player to be affected.
     * @return a string with result information.
     */
    private String negativePointEvent(Player playerToBeAffected) {
        int randomNumber = rand.nextInt(100) + 1;
        String eventInfo = "Error";

        if (randomNumber <= 40) {
            playerToBeAffected.setScore(-1);
            eventInfo = playerToBeAffected.getName() + " just need a little more to drink. (-1 point)";
        }
        else if(randomNumber <= 75) {
            playerToBeAffected.setScore(-2);
            eventInfo = playerToBeAffected.getName() + " need some more to drink. (-2 points";
        }
        else if(randomNumber <= 98) {
            playerToBeAffected.setScore(-3);
            eventInfo = playerToBeAffected.getName() + " need more! (-3 points)";
        }
        else if(randomNumber <= 100) {
            //A 2% chance to instantly set punishment to 1 if this case happens.
            playerToBeAffected.setPunishment(1);
            eventInfo = playerToBeAffected.getName() + " this is an unlucky day for you! (+1 punishment)";
        }
        return eventInfo;
    }

    //***** End of Negative Events *****
}