package com.marcel.chess.service.api.validator.input;

public interface InputValidatorService {
    boolean isInputValid(String input);

    String getInvalidMessage();

    InputValidatorType getInputValidatorType();
}
