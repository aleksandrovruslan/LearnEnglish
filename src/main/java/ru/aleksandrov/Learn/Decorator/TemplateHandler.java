package ru.aleksandrov.Learn.Decorator;

public interface TemplateHandler {
    default void before() {
        System.out.println("Before template function.");
    }

    default void after() {
        System.out.println("After template function.");
    }
}
