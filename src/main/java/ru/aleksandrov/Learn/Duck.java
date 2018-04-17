package ru.aleksandrov.Learn;

public interface Duck {
    Duck setStorage(ActionsStorage<Runnable> storage);

    boolean setActionName(String actionName);

    boolean removeActionName(String actionName);

    void performActions();
}
