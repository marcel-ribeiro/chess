package com.marcel.chess.service.impl.validator.input;

import com.marcel.chess.model.constants.Message;
import com.marcel.chess.service.api.validator.input.InputValidatorService;
import com.marcel.chess.service.api.validator.input.InputValidatorType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class DefaultInputValidatorServiceImpl implements InputValidatorService {
    private InputValidatorType inputValidatorType = InputValidatorType.DEFAULT;
    private String invalidMessage;

    @Override
    public boolean isInputValid(String input) {
        this.invalidMessage = null;

        if (ObjectUtils.isEmpty(input)) {
            this.invalidMessage = Message.PLAYER_MOVE_REQUEST_INVALID_EMPTY.getText();
            return false;
        }
        return true;
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
