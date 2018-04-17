package ru.aleksandrov.messages;

import java.util.List;
import java.util.Map;

public class ErrorMessage implements Message {
    private final static String KEY_MESSAGE = "errors";

    public ErrorMessage(Map<String, Object> variables, String message) {
        List<String> messages = null;
        initMessages(variables, messages, KEY_MESSAGE);
        messages.add(message);
    }
}
