/**
 * This job will hold on the player name, score and punishment score of a player.
 * The score can't be higher than 15 and can't be below -10.
 * If score goes below -10 the punishment will go up by 1.
 *
 * @author TrymV
 * @version 0.1
 */
public class Player {
    private String name;
    private float score;
    private int punishment;

    /**
     * Constructor for objects of the class Player.
     * Will set player name, score and punishment.
     * Score and punishment will be set to default value of zero.
     * @param name name of the player.
     * @throws IllegalArgumentException if name is set to null.
     */
    public Player(String name) {
        if(name != null) {
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("Name was set to null");
        }
        this.score = 0;
        this.punishment = 0;
    }

    /**
     * Return the player name
     * @return player name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the player score
     * @return the player score
     */
    public float getScore() {
        return this.score;
    }

    /**
     * Return the punishment score.
     * @return punishment score.
     */
    public int getPunishment() {
        return this.punishment;
    }

    /**
     * Increase or decrease the score depending on the income score.
     * If score go below -15 the score will be reset to 0 and add 1 to punishment.
     * Also if score is above 15 the score will be set to 15.
     * @param scoreChange the increase or decrease of the score.
     */
    public void setScore(float scoreChange) {
        this.score = this.score + scoreChange;
        if(this.score <= -15) {
            this.score = 0;
            this.punishment++;
        }
        if(this.score > 15) {
            this.score = 15;
        }
    }

    /**
     * Double's the score of the player.
     * If score go below -15 the score will be reset to 0 and add 1 to punishment.
     * Also if score is above 15 the score will be set to 15.
     */
    public void doubleScore() {
        this.score = this.score*2;
        if(this.score <= -15) {
            this.score = 0;
            this.punishment++;
        }
        if(this.score > 15) {
            this.score = 15;
        }
    }

    /**
     * Reset the punishment to 0 if it's 1 or higher.
     */
    public void resetPunishment(){
        if(this.punishment >= 1) {
            this.punishment = 0;
        }
    }

    /**
     * Set the player score to 0.
     */
    public void resetScore() {
        this.score = 0;
    }

    /**
     * Sets the punishment to a value.
     * @param punishmentChange the value you want to set punishment to.
     * @throws IllegalArgumentException if value is set to a negative number.
     */
    public void setPunishment(int punishmentChange) {
        if(punishmentChange >= 0) {
            this.punishment = punishmentChange;
        }
        else {
            throw new IllegalArgumentException("Can't set punishment to a negative number.");
        }
    }

    /**
     * Reverse the score of the player.
     * -1 --> +1, +10 --> -10, -7 --> +7
     */
    public void reversePoints() {
        this.score = this.score*-1;
    }
}