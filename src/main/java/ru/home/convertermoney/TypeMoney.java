package ru.home.convertermoney;

import java.util.Arrays;

public enum TypeMoney {
    RUB,
    EUR,
    USD,
    JPY,
    CNY;

    public static String getTypeByIndex(int number) {
        return Arrays.stream(TypeMoney.values())
                .filter(element -> element.ordinal() == number)
                .findFirst().get().toString();

    }
}
