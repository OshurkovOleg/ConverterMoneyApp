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

    public static String getListType() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < TypeMoney.values().length; i++) {
            stringBuilder
                    .append(i)
                    .append(" - ")
                    .append(TypeMoney.values()[i].toString())
                    .append("\n");
        }

        return stringBuilder.toString();
    }

}
