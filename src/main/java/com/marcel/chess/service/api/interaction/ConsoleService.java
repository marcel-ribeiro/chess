package com.marcel.chess.service.api.interaction;

import com.marcel.chess.service.api.validator.input.InputValidatorType;

public interface ConsoleService {
    /**
     * @param text
     * @param color
     * @param inputValidatorType
     * @return
     */
    String requestInput(String text, Color color, InputValidatorType inputValidatorType);

    /**
     * <p>Prints an object using the default toString() and the default color (with Line breaks before and after)</p>
     *
     * @param object
     */
    void println(Object object);

    /**
     * <p>Prints a string using the default color (with Line breaks before and after)</p>
     *
     * @param text
     */
    void println(String text);

    /**
     * <p>Prints using the default toString() and the color passed as an argument (with Line breaks before and after)</p>
     *
     * @param object
     * @param color
     */
    void println(Object object, Color color);

    /**
     * <p>Prints a string using  the color passed as an argument (with Line breaks before and after)</p>
     *
     * @param text
     * @param color
     */
    void println(String text, Color color);

    /**
     * <p>Prints an object using the default toString() and the default color (without Line breaks before and after)</p>
     *
     * @param object
     */
    void print(Object object);

    /**
     * <p>Prints a string using the default color (without Line breaks before and after)</p>
     *
     * @param text
     */
    void print(String text);

    /**
     * <p>Prints using the default toString() and the color passed as an argument (without Line breaks before and after)</p>
     *
     * @param object
     * @param color
     */
    void print(Object object, Color color);

    /**
     * <p>Prints a string using  the color passed as an argument (without Line breaks before and after)</p>
     *
     * @param text
     * @param color
     */
    void print(String text, Color color);
}
