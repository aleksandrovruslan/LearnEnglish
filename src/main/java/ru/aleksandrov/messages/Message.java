package ru.aleksandrov.messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Message {
    default void initMessages(Map<String, Object> variables, List<String> messages, String keyMessage) {
        if (variables.containsKey(keyMessage)) {
            if (variables.get(keyMessage) instanceof List
                    && ((List) variables.get(keyMessage)).get(0) instanceof String && variables.get(keyMessage) != null){
                messages = (List<String>) variables.get(keyMessage);
            }
        } else {
            messages = new ArrayList<>();
            variables.put(keyMessage, messages);
        }
    }
}
