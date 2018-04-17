package ru.aleksandrov.Learn.Decorator;

public class SimpleService implements Service {
    private final String message;
    private final int count;

    public SimpleService(String message, int count) {
        this.message = message;
        this.count = count;
    }

    @Override
    public void performPost() {
        System.out.println("Simple service Post()");
    }

    @Override
    public void performGet() {
        System.out.println("Simple service Get()");
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCount() {
        return count;
    }
}
