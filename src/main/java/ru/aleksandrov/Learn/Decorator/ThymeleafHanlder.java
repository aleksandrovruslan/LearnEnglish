package ru.aleksandrov.Learn.Decorator;

import ru.aleksandrov.service.ThymeleafHandler;

public class ThymeleafHanlder implements TemplateHandler, Service {
    private Service service;

    public ThymeleafHanlder(Service service) {
        this.service = service;
    }

    @Override
    public void performPost() {
        before();
        service.performPost();
        after();
    }

    @Override
    public void performGet() {
        before();
        service.performGet();
        before();
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public void before() {
        System.out.println("Header actions");
    }

    @Override
    public void after() {
        System.out.println("Template actions");
    }
}
