package ru.aleksandrov.Learn;

public class Main {
    public static void main(String[] args) {
        ActionsStorage<Runnable> storage = new ActionsStorage();
        Duck duck = new DuckWhite().setStorage(storage);
        Runnable voice = () -> System.out.println("Mega voice.");
        storage.addAction("voice", voice);
        duck.setActionName("voice");
        duck.setStorage(storage);

        Runnable fly = () -> System.out.println("High fly.");
        storage.addAction("fly", fly);
        duck.setActionName("fly");
        duck.performActions();
    }
}
