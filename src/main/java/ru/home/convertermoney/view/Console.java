package ru.home.convertermoney.view;

import ru.home.convertermoney.Settings;
import ru.home.convertermoney.TypeMoney;
import ru.home.convertermoney.working.ProcessingConvert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;


public class Console {
    String startCurrency;
    String resultCurrency;
    BigDecimal amountToConvert;

    public void start() {

        System.out.println(Settings.USER_GREETING);
        System.out.println(Settings.MENU);

        startCurrency = getResultUserChoiceCurrency(Settings.CHOOSE_TYPE_MONEYS);
        resultCurrency = getResultUserChoiceCurrency(Settings.CHOOSE_TYPE_MONEYS_RESULT);
        amountToConvert = getAmountToConvert();

        try {
            ProcessingConvert.startConvert(startCurrency, resultCurrency, amountToConvert);
        } catch (IOException exp) {
            System.out.println("Поймали исключение которое нужно обработать" + exp.getMessage());
        }
    }

    private String getResultUserChoiceCurrency(String messageForUser) {

        System.out.print(messageForUser);

        while (true) {
            try {
                int selectUserNumberTypeCurrency = Settings.scanner.nextInt();

                if (selectUserNumberTypeCurrency < 0 || selectUserNumberTypeCurrency > Settings.MAX_LENGTH_TYPE) {
                    System.out.println(Settings.NUMBER_NOT_FOUND);
                    continue;
                }

                return TypeMoney.getTypeByIndex(selectUserNumberTypeCurrency);

            } catch (InputMismatchException exp) {
                System.out.println(Settings.NUMBER_NOT_FOUND);
                Settings.scanner.nextLine();
            }
        }
    }


    private BigDecimal getAmountToConvert() throws InputMismatchException {

        System.out.print(Settings.AMOUNT_OF_MONEY);

        while (true) {
            try {
                BigDecimal result = Settings.scanner.nextBigDecimal();

                if (result.compareTo(new BigDecimal(Settings.MIN_AMOUNT_CONVERSION)) < 0
                        || result.compareTo(new BigDecimal(Settings.MAX_AMOUNT_CONVERSION)) > 0) {
                    System.out.println(Settings.AMOUNT_RANGE_VIOLATED);
                    continue;
                }
                return result;
            } catch (InputMismatchException exp) {
                System.out.println(Settings.NUMBER_NOT_FOUND);
                Settings.scanner.nextLine();
            }
        }


    }
}

