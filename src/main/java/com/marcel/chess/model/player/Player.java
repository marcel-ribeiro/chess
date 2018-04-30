package com.marcel.chess.model.player;

import com.marcel.chess.service.api.interaction.Color;
import org.springframework.core.style.ToStringCreator;

/**
 * <p>Object that represents and holds the information regarding a player</p>
 * <p>This includes:</p>
 * <p>- The name of the player</p>
 * <p>- The color this player is in the game (Traditionally, BLACK or WHITE)</p>
 * <p>- The score for the player</p>
 */
public class Player {
    private String name;
    private Color playerColor;
    private int score;

    /**
     * <p>Constructor</p>
     *
     * @param name
     * @param playerColor
     */
    public Player(String name, Color playerColor) {
        this.name = name;
        this.playerColor = playerColor;
        this.score = 0;
    }

    /**
     * <p>Getter</p>
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>Getter</p>
     *
     * @return playerColor
     */
    public Color getPlayerColor() {
        return playerColor;
    }


    /**
     * <p>Getter</p>
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * <p>Simple function that centralizes the way the score is manipulated</p>
     * <p>When you pass a number of points it increases the score of the player by that number of points</p>
     *
     * @return points
     */
    public void increaseScore(int points) {
        this.score += points;
    }


    /**
     * Overrides the toString() method to display all the player's info
     *
     * @return
     */
    @Override
    public String toString() {
        ToStringCreator toStringCreator = new ToStringCreator(this);
        return toStringCreator.append("name", name).append("playerColor", playerColor).toString();
    }
}
