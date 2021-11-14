package com.company;
import com.google.errorprone.annotations.Var;

public class result {

    public String cityName;
    public String weathernow;
    public double citytemp;
    public double feeltemp;
    public double humidity;
    public int windspeed;

    public result(String cityName, double citytemp, double feeltemp, double humidity, int windspeed, String weathernow){
        this.cityName = cityName;
        this.citytemp = citytemp;
        this.feeltemp = feeltemp;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.weathernow = weathernow;
    }
    public String toString() {
        return "Ваш город: " + cityName +
                "\nСейчас: " + weathernow +
                "\nТемпература: " + citytemp + " C\u00B0" +
                "\nОщущается как: " + feeltemp + " C\u00B0" +
                "\nВлажность: " + humidity +
                "\nВетер: " + windspeed + "м/с";

    }

}
