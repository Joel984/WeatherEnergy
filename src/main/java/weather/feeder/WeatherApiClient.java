package weather.feeder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiClient {
    private final String apiKey;
    public WeatherApiClient(String apiKey ) {
        this.apiKey = apiKey;
    }
    public String getApiKey(String city) {
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" +city+ "&appid=" +apiKey+ "&units=metric";
        HttpURLConnection connection = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result.toString();
    }
}
