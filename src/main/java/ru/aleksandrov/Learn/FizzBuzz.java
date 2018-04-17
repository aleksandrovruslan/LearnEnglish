package ru.aleksandrov.Learn;

public class FizzBuzz {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder(8);
        for (int i = 1; i < 101; i++) {
            result.delete(0, 7);
            if (i % 3 == 0) result.append("Fizz");
            if (i % 5 == 0) result.append("Buzz");
            if (result.length() == 0) result.append(i);
            System.out.println(result);
        }
    }
}
