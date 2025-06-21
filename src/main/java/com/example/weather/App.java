package com.example.weather;


import java.util.Scanner;
// Importing test code (not recommended, but works for now)


public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String city = sc.nextLine();

        // Use WeatherService from test directory
        WeatherService service = new WeatherService();
        String weather = service.getWeather(city);

        System.out.println("Weather Report for " + city + ":");
        System.out.println(weather);
    }
}
