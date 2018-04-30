package com.marcel.chess.model.validator;

import com.marcel.chess.model.constants.Message;

/**
 * Object that holds the status of a rule validation
 */
public class ValidationResult {
    private Message message;
    private boolean isValid;


    /**
     * <p>Constructor</p>
     *
     * @param isValid
     */
    public ValidationResult(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * <p>Constructor</p>
     *
     * @param message
     * @param isValid
     */
    public ValidationResult(Message message, boolean isValid) {
        this.message = message;
        this.isValid = isValid;
    }

    /**
     * <p>Getter</p>
     *
     * @return message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * <p>Setter</p>
     *
     * @param message
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * <p>Getter</p>
     *
     * @return isValid
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * <p>Setter</p>
     *
     * @param valid
     */
    public void setValid(boolean valid) {
        isValid = valid;
    }
}
