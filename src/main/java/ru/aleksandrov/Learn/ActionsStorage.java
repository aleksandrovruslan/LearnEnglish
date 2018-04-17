package ru.aleksandrov.Learn;

import java.util.HashMap;
import java.util.Map;

public class ActionsStorage<T> {
    private Map<String, T> actions = new HashMap<>();

    public void addAction(final String actionName, final T action) {
        assertContainsNameAction(actionName);
        actions.put(actionName, action);
    }

    public T getAction(final String actionName) {
        return actions.get(actionName);
    }

    public boolean contains(final String actionName) {
        return actions.containsKey(actionName);
    }

    protected static void assertArgumentNotNull(final String actionName) {
        if (actionName instanceof String && !"".equals(actionName)) return;
        if (actionName != null) return;
        throw new RuntimeException("Argument is empty.");
    }

    protected void assertContainsNameAction (final String actionName) {
        ActionsStorage.assertArgumentNotNull(actionName);
        if (contains(actionName)) {
            throw new RuntimeException("Action \"" + actionName + "\" exist");
        }
    }
}
