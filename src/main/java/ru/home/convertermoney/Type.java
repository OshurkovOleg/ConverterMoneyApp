package ru.home.convertermoney;

import java.util.Arrays;

public enum Type {
    RUB,
    EUR,
    USD,
    JPY,
    CNY;

    public static String getTypeByIndex(int number) {
        return Arrays.stream(Type.values())
                .filter(element -> element.ordinal() == number)
                .findFirst().get().toString();

    }
}
