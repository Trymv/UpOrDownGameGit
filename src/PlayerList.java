import java.util.ArrayList;

/**
 * This class hold on a list of all player
 *
 * @author TrymV
 * @version 0.1
 */
public class PlayerList {
    private ArrayList<Player> listOfPlayers;

    /**
     * Constructor for object of the class PlayerList
     */
    public PlayerList() {
        this.listOfPlayers = new ArrayList<>();
    }

    /**
     * Adds a new player to the list.
     * @param player name of player to add
     */
    public void addPlayer(Player player) {
        listOfPlayers.add(player);
    }

    /**
     * Remove a player from the player list depending on the name
     * the user send inn with the parameter.
     * @param playerToRemove the player to be removed
     */
    public void removePlayer(String playerToRemove) {
        Player playerToBeRemoved = null;

        for (Player player : listOfPlayers) {
            if (player.getName().equals(playerToRemove)) {
                playerToBeRemoved = player;
            }
        }
        if (playerToBeRemoved != null) {
            listOfPlayers.remove(playerToBeRemoved);
        }
    }

    /**
     * Checks if a player with the incoming name already exist in the list.
     * @param playerName name of player to see if already exist
     * @return false if no player with income name exist in the list
     */
    public boolean doesThePlayerExist(String playerName) {
        boolean doesThisPlayerExist = false;

        for(Player player:listOfPlayers) {
            if (player.getName().equals(playerName)) {
                doesThisPlayerExist = true;
            }
        }
        return doesThisPlayerExist;
    }

    /**
     * Checks if the list is empty.
     * If list is empty the method will return false.
     * @return false if list is not empty.
     */
    public boolean isListEmpty() {
        boolean listIsEmpty = true;
        if(!listOfPlayers.isEmpty()) {
            listIsEmpty = false;
        }
        return listIsEmpty;
    }

    /**
     * List all players in the list and return the list as a String.
     * @return playerList as a String.
     */
    public String listAllPlayers() {
        StringBuilder playerList = new StringBuilder();

        for(Player player:listOfPlayers) {
            playerList.append(player.getName()).append("\n");
        }
        return playerList.toString();
    }

    /**
     * Return size of the player list.
     * @return size of listOfPlayers.
     */
    public int getListSize() {
        return listOfPlayers.size();
    }

    /**
     * Find a player depending on the players index in the list.
     * @param playerIndex the index of the player to be found.
     * @return name of the player with the parameter index.
     */
    public String getPlayerNameWithIndex(int playerIndex) {
        String playerName = "";
            for (Player player : listOfPlayers) {
                if (listOfPlayers.indexOf(player) == playerIndex) {
                    playerName = player.getName();
                }
            }
        return playerName;
    }

    /**
     * Gets a connection to a player with the name of the player.
     * Warning! If no player with the name exist this method will return null
     * and could result in a NullPointerException.
     * @param playerName name of player to get a connection to.
     * @return the found player with the sent in name or null if no player with name exist.
     */
    public Player getPlayerWithName(String playerName) {
        Player playerToReturn = null;
        for(Player player:listOfPlayers) {
            if(player.getName().equals(playerName)) {
                playerToReturn = player;
            }
        }
        return playerToReturn;
    }

    /**
     * Find a player by name and then either increase or decrease the player score depending on input.
     * @param playerName name of the player.
     * @param scoreChange score change from -10 to 15.
     */
    public void changePlayerScore(String playerName, float scoreChange) {
        for (Player player : listOfPlayers) {
            if (player.getName().equals(playerName)) {
                player.setScore(scoreChange);
            }
        }
    }

    /**
     * Check the list if any players has punishment above 0.
     * If the method find at least 1 it will return true.
     * @return true if at least one player has punishment above 0.
     */
    public boolean anyPlayersWithPunishment() {
        boolean playersWithPunishment = false;

        for(Player player:listOfPlayers) {
            if(player.getPunishment() > 0) {
                playersWithPunishment = true;
            }
        }
        return playersWithPunishment;
    }

    /**
     * List all players with 1 or more punishment.
     * @return playerList with all players with 1 or above punishment.
     */
    public String listAllPlayersWithPunishment() {
        StringBuilder playerList = new StringBuilder();

        if(anyPlayersWithPunishment()) {
            for (Player player : listOfPlayers) {
                if (player.getPunishment() > 0) {
                    playerList.append(player.getName()).append("\n");
                    player.resetPunishment();
                }
            }
        }
        return playerList.toString();
    }

    /**
     * List the score of all players.
     * @return a list of the score of every player.
     */
    public String getListWithPlayerScores() {
        StringBuilder playerList = new StringBuilder();
        for(Player player:listOfPlayers) {
            playerList.append(player.getName()).append("'s score is: ").append(player.getScore()).append("\n");
        }
        return playerList.toString();
    }

    /**
     * Doubles the score of every player in the list.
     */
    public void doubleScoreOfEveryPlayer() {
        for(Player player:listOfPlayers) {
            player.doubleScore();
        }
    }

    /**
     * Reset score and punishment of every player in the list.
     */
    public void resetAllPlayerStats() {
        for(Player player:listOfPlayers) {
            player.resetPunishment();
            player.resetScore();
        }
    }

    /**
     * Reverse the score of every player in the list.
     */
    public void reverseAllPoints() {
        for(Player player:listOfPlayers) {
            player.reversePoints();
        }
    }
}