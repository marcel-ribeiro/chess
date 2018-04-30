package com.marcel.chess.service.impl.validator.input;

import com.marcel.chess.model.constants.Message;
import com.marcel.chess.service.api.validator.input.InputValidatorService;
import com.marcel.chess.service.api.validator.input.InputValidatorType;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class InputSquareValidatorServiceImpl implements InputValidatorService {
    private Pattern pattern = Pattern.compile("[1-8]{2}");

    private InputValidatorType inputValidatorType = InputValidatorType.INPUT_SQUARE;
    private String invalidMessage;

    @Override
    public boolean isInputValid(String input) {
        this.invalidMessage = null;

        boolean isMatch = pattern.matcher(input).matches();
        if (!isMatch) {
            this.invalidMessage = Message.PLAYER_MOVE_REQUEST_INVALID_FORMAT.getText();
        }
        return isMatch;
    }

    @Override
    public String getInvalidMessage() {
        return this.invalidMessage;
    }

    @Override
    public InputValidatorType getInputValidatorType() {
        return inputValidatorType;
    }
}
