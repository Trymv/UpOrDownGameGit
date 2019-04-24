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
            int randomEvent = rand.nextInt(5)+1;

            switch (randomEvent) {
                case 1: //Positive event with a rare chance of being very bad.
                    if(rand.nextInt(100)+1 <= 98) {
                        pointEvent(playerToBeAffected, 1);
                        eventInfo = playerToBeAffected.getName() + " feels just a little bit better. (+1 point)";
                    }
                    else {
                        //A 2% chance to instantly set punishment to 1 if this case happens.
                        playerToBeAffected.setPunishment(1);
                        eventInfo = playerToBeAffected.getName() + " this is an unlucky day for you! (+1 punishment)";
                    }
                    break;

                case 2: //Negative event
                    pointEvent(playerToBeAffected, -2);
                    eventInfo = playerToBeAffected.getName() + " need some more to drink. (-2 points)";
                    break;

                case 3: //Positive event
                    pointEvent(playerToBeAffected, 2);
                    eventInfo = playerToBeAffected.getName() + " has to slow down! (+2 point)";
                    break;

                case 4: //Negative event
                    pointEvent(playerToBeAffected, -3);
                    eventInfo = playerToBeAffected.getName() + " need more! (-3 points)";
                    break;

                case 5: //Natural event
                    if(rand.nextInt(100)+1 <= 50) {
                        playerToBeAffected.doubleScore();
                        eventInfo = playerToBeAffected.getName() + " got some new wisdom! (Points double)";
                    }
                    else {
                        playerList.doubleScoreOfEveryPlayer();
                        eventInfo = playerToBeAffected.getName() + " shares his wisdom! (Points of every player double's)";
                    }
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



    //***** End of Positive Events *****
    //***** Start of Natural Events *****

    /**
     * Give or reduce the player score.
     * @param scoreChange the change in score. Can be both + and -.
     */
    private void pointEvent(Player playerToBeAffected, float scoreChange) {
        playerToBeAffected.setScore(scoreChange);
    }

    //***** End of Natural Events *****
    //***** Start of Negative Events *****

    //***** End of Negative Events *****
}

