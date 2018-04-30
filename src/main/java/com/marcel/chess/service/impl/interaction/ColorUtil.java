package com.marcel.chess.service.impl.interaction;

import com.marcel.chess.service.api.interaction.Color;

public class ColorUtil {
    public static String getColoredText(String text, Color color) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(color.getPrefix()).append(text).append(color.getSuffix()).toString();
    }
}
