package ru.aleksandrov.Learn.Decorator;

public class MainDecorator {
    public static void main(String[] args) {
        Service service = new ThymeleafHanlder(new EndService(new StartService(new HomeService(new ListPageService(new HomeService(new SimpleService("my message", 5)))))));
        service.performGet();
    }
}
