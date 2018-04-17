package ru.aleksandrov.service;

import javax.servlet.http.HttpServletRequest;

public interface ExtractData {
    String validate (String name, HttpServletRequest request) throws RuntimeException;
}
