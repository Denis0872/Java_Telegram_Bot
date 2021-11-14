package com.company;

public class resultbyloc {
    public String weathernow;
    public double citytemp;
    public double feeltemp;
    public int windspeed;

    public String nextDayDes;
    public double nextDayTemp;
    public double nextDayFeelsLike;
    public double nextDayWindSpeed;

    public resultbyloc(double citytemp, double feeltemp, int windspeed, String weathernow, String nextDayDes, double nextDayTemp, double nextDayFeelsLike, double nextDayWindSpeed){
        this.citytemp = citytemp;
        this.feeltemp = feeltemp;
        this.windspeed = windspeed;
        this.weathernow = weathernow;


        this.nextDayDes = nextDayDes;
        this.nextDayTemp = nextDayTemp;
        this.nextDayFeelsLike = nextDayFeelsLike;
        this.nextDayWindSpeed = nextDayWindSpeed;

    }
    public String toString() {
        return
                "\nСейчас: " + weathernow +
                        "\nТемпература: " + citytemp + " C\u00B0" +
                        "\nОщущается как: " + feeltemp + " C\u00B0" +
                        "\nВетер: " + windspeed + "м/с" +
                        "\nНа завтра:" +
                        "\nОжидается: " + nextDayDes +
                        "\nТемпература: " + nextDayTemp + " C\u00B0" +
                        "\nОщущается как: " + nextDayFeelsLike + " C\u00B0" +
                        "\nВетер: " + nextDayWindSpeed + "м/с" ;



    }
}
