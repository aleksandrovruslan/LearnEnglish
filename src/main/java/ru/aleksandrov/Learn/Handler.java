package ru.aleksandrov.Learn;

import java.util.HashMap;
import java.util.Map;

public class Handler {
    private Map<String, Object> variables = new HashMap<>();
    private String message;
    private Service service;

    public Handler(String message, Class<? extends Service> service) {
        this.message = message;
        try {
            this.service = service.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void processPost() {
        System.out.print(message);
        service.performPost();
    }

    public void processGet() {

    }

    private void initService() {
        try {
            service = service.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
