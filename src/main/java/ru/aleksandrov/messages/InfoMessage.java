package ru.aleksandrov.messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoMessage implements Message{
    private final static String KEY_MESSAGE = "messages";

    public InfoMessage(Map<String, Object> variables, String message) {
        List<String> messages = new ArrayList<>();
        initMessages(variables, messages, KEY_MESSAGE);
        messages.add(message);
        variables.put("messages", messages);
    }
}