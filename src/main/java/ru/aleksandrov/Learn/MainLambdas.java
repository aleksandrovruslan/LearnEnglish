package ru.aleksandrov.Learn;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

public class MainLambdas {
    public static final ThreadLocal<Date> formatter = ThreadLocal.withInitial(() -> new Date());

    public static void main(String[] args) {
        String[] russians = {"", "jhasjdh", "sadhjhas", "", "dsfdsrew", "werytytr", "fd", "ew", "fdf", "dsff"};

        Arrays.stream(russians).filter(r -> r.length() > 3).forEach((s) -> {
            s = s + " test";
            System.out.println(s);
        });
    }
}
