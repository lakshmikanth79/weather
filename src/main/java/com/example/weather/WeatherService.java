package com.example.weather;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherService {
    private final String API_KEY = "724b6c493818dcd5e8c467f9b47ad1de";

    public String getWeather(String city) {
        String inline = "";
        try {
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=" + API_KEY + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return "wrong entry";
            }

            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inline += sc.nextLine();
            }
            sc.close();

            // Manual parsing
            String tempStr = getValue(inline, "\"temp\":");
            String humidityStr = getValue(inline, "\"humidity\":");
            String windStr = getValue(inline, "\"speed\":");
            String descStr = getValue(inline, "\"description\":\"", "\"");

            // Parse values
            double temp = tempStr != null ? Double.parseDouble(tempStr) : -1;
            int humidity = humidityStr != null ? Integer.parseInt(humidityStr) : -1;
            double windSpeed = windStr != null ? Double.parseDouble(windStr) : -1;
            String description = descStr != null ? descStr : "unknown";

            // Suggestion logic
            String suggestion = (temp > 35 || humidity > 75 || description.contains("rain") || windSpeed > 10)
                    ? "Better to stay inside!"
                    : "Good day to go outside!";

            return "temperature: " + Math.round(temp) + "°C\n"
                 + "humidity: " + humidity + "%\n"
                 + "wind speed: " + windSpeed + " m/s\n"
                 + "condition: " + description + "\n"
                 + "suggestion: " + suggestion;

        } catch (IOException | NumberFormatException e) {
            return "wrong entry";
        }
    }

    // Helper method to extract value after a key
    private String getValue(String json, String key) {
        return getValue(json, key, ",");
    }

    // Overloaded: extract value between key and endChar
    private String getValue(String json, String key, String endChar) {
        try {
            int start = json.indexOf(key);
            if (start == -1) return null;
            start += key.length();
            int end = json.indexOf(endChar, start);
            return json.substring(start, end).replace("\"", "").trim();
        } catch (Exception e) {
            return null;
        }
    }
}
