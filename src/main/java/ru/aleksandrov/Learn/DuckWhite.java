package ru.aleksandrov.Learn;

import java.util.HashSet;
import java.util.Set;

public class DuckWhite implements Duck {
    private Set<String> actionsName = new HashSet<>();
    private ActionsStorage<Runnable> storage;

    @Override
    public Duck setStorage(ActionsStorage<Runnable> storage) {
        this.storage = storage;
        return this;
    }

    @Override
    public boolean setActionName(String actionName) {
        return actionsName.add(actionName);
    }

    @Override
    public boolean removeActionName(String actionName) {
        return actionsName.remove(actionName);
    }

    @Override
    public void performActions() {
        actionsName.forEach(s -> storage.getAction(s).run());
    }
}
