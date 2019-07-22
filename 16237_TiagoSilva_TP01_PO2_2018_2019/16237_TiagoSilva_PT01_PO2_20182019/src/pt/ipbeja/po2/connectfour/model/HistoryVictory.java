package pt.ipbeja.po2.connectfour.model;

/**
 * @author Tiago Silva 16237
 * @version 06-05-2019
 */

public class HistoryVictory {
    private String player;
    int score;

    /**
     * Constructor of class, received the player e score and saved
     * @param player is the player win
     * @param score score of the player
     */
    public HistoryVictory(String player, int score) {
        this.player = player;
        this.score = score;
    }

    /**
     * Gets the score the player and respective player
     * @return a string with the player anda score
     */
    public String toString(){
        return this.player + " -> " + score;
    }

    /**
     * Gets the score the player
     * @return the score
     */
    public int getScore() {
        return score;
    }


}
