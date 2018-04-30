package com.marcel.chess.service.api.interaction;

/**
 * <p>Enum that holds the list of colors used in the game</p>
 * <p>The colors in the screen are created by adding a prefix and a suffix to the text when it's printed out, that's why there's a getter for each one of these properties</p>
 */
public enum Color {
    RED(Color.ANSI_RED),
    GREEN(Color.ANSI_GREEN),
    DEFAULT(Color.ANSI_DEFAULT);

    private static final String ANSI_DEFAULT = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private String prefix;
    private String suffix;

    Color(String prefix) {
        this.prefix = prefix;
        this.suffix = ANSI_DEFAULT;
    }

    /**
     * <p>Getter</p>
     *
     * @return prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * <p>The colors in the screen are created by adding a </p>
     *
     * @return suffix
     */
    public String getSuffix() {
        return suffix;
    }
}