package ru.home.convertermoney.working;


import org.json.JSONObject;
import ru.home.convertermoney.Settings;
import ru.home.convertermoney.exceptions.ConverterException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProcessingConvert {

    public static void startConvert(String getMoney, String resultMoney, BigDecimal count) throws IOException {

        String apiUrl = String.format("https://api.apilayer.com/currency_data/convert?to=%s&from=%s&amount=%s", resultMoney, getMoney, count.toString());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl))
                .headers("User-Agent", Settings.USER_AGENT, "apikey", Settings.API_KEY)
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException exp) {
            throw new ConverterException("Ошибка в момент отправки GET-запроса" + exp.getMessage());
        } catch (IOException exp) {
            throw new ConverterException("Ошибка IOException в классе ProcessingConverted" + exp.getMessage());
        }

        if (response.statusCode() == HttpURLConnection.HTTP_OK) {
            JSONObject jsonObject = new JSONObject(response.body());
            System.out.println(resultMoney + " = " + jsonObject.get("result"));

        } else {
            System.out.println(Settings.REQUEST_FAILED);
        }
    }
}
