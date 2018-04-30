package com.marcel.chess.service.impl.interaction;

import com.google.common.collect.Maps;
import com.marcel.chess.service.api.interaction.Color;
import com.marcel.chess.service.api.interaction.ConsoleService;
import com.marcel.chess.service.api.validator.input.InputValidatorService;
import com.marcel.chess.service.api.validator.input.InputValidatorType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class ConsoleServiceImpl implements ConsoleService {
    private Scanner scanner;
    private Map<InputValidatorType, InputValidatorService> inputValidatorServiceMap;

    public ConsoleServiceImpl(List<InputValidatorService> inputValidatorServices) {
        this.scanner = new Scanner(System.in);

        this.inputValidatorServiceMap = Maps.newHashMap();
        for (InputValidatorService inputValidatorService : inputValidatorServices) {
            inputValidatorServiceMap.put(inputValidatorService.getInputValidatorType(), inputValidatorService);
        }
    }

    @Override
    public String requestInput(String text, Color color, InputValidatorType inputValidatorType) {
        String input = null;
        InputValidatorService inputValidatorService = this.inputValidatorServiceMap.get(inputValidatorType);
        do {
            if (input != null) {
                this.println(inputValidatorService.getInvalidMessage());
            }

            this.println(text, color);
            input = this.scanner.next();
        } while (!inputValidatorService.isInputValid(input));

        return input;
    }


    /*println with new line*/
    @Override
    public void println(Object object) {
        this.println(object, Color.DEFAULT);
    }

    @Override
    public void println(String text) {
        this.println(text, Color.DEFAULT);
    }

    @Override
    public void println(Object object, Color color) {
        if (ObjectUtils.isEmpty(object)) {
            return;
        }
        this.println(object.toString(), color);
    }

    @Override
    public void println(String text, Color color) {
        System.out.println(ColorUtil.getColoredText(text, color));
    }

    /*print without new line*/
    @Override
    public void print(Object object) {
        this.print(object, Color.DEFAULT);
    }

    @Override
    public void print(String text) {
        this.print(text, Color.DEFAULT);
    }

    @Override
    public void print(Object object, Color color) {
        if (ObjectUtils.isEmpty(object)) {
            return;
        }
        print(object.toString(), color);
    }

    @Override
    public void print(String text, Color color) {
        System.out.print(ColorUtil.getColoredText(text, color));
    }


}
