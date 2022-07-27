package ru.home.convertermoney.repository;

import ru.home.convertermoney.Settings;
import ru.home.convertermoney.exceptions.ConverterException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CurrenciesFiles {

    public static final String UNABLE_TO_PARSE_FILE = "Unable to parse file";

    public static String[] getAllCurrenciesFromFile() {

        try (Stream<String> lines = Files.lines(Path.of(Settings.PATH_TO_FILE_WITH_ALL_CURRENCY_TYPE))) {

            List<String> list = lines.map(line -> line.split("\""))
                    .flatMap(Arrays::stream)
                    .filter(line -> line.length() == 3)
                    .toList();

            return list.toArray(new String[0]);

        } catch (InvalidPathException | IOException e) {
            throw new ConverterException(UNABLE_TO_PARSE_FILE + e);
        }
    }
}

