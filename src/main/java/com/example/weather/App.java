package com.example.weather;


import java.util.Scanner;
// Importing test code (not recommended, but works for now)


public class App {
    public static void main(String[] args) {
    	 if (args.length == 0) {
    	        System.out.println("City name not provided");
    	        return;
    	}
    	 String city = args[0];
        // Use WeatherService from test directory
        WeatherService service = new WeatherService();
        String weather = service.getWeather(city);

        System.out.println("Weather Report for " + city + ":");
        System.out.println(weather);
    }
}
