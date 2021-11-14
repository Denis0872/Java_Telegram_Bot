package com.company;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class weather {
    public static String getWeather (double lat, double lon, String city, String message1) {

        String openApi = "6a6d12380407ba0a506a3fa4567947f5";
        String url = null;
        if (lat != 0 && lon != 0 && city == ""&&message1=="")
        {
            url ="https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&lang=ru&appid=" + openApi + "&units=metric&exclude=alerts";
        }
        else if(lat == 0 && lon == 0 && city != ""&&message1==""){
            url ="http://api.openweathermap.org/data/2.5/weather?q=" + city + ",RU&APPID=" + openApi + "&lang=RU&units=metric";
        }
        else
            url = "http://api.openweathermap.org/data/2.5/weather?q=" + message1 + "&units=metric&APPID=6a6d12380407ba0a506a3fa4567947f5";



        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputline;
            StringBuffer response = new StringBuffer();
            while ((inputline = in.readLine()) != null) {
                response.append(inputline);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object parseWeather (double lat, double lon, String city, String message1) {
        JSONObject weather = new JSONObject(getWeather(lat,lon, city, message1));
        if (lat != 0 && lon != 0 && city == "")
        {
            String weathernow = weather.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("description");
            double citytemp = weather.getJSONObject("current").getDouble("temp");
            double feeltemp = weather.getJSONObject("current").getDouble("feels_like");
            int windspeed = weather.getJSONObject("current").getInt("wind_speed");
            //String icon=weather.getJSONObject("current").getString("icon");

            String nextDayDes = weather.getJSONArray("daily").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description");
            double nextDayTemp = weather.getJSONArray("daily").getJSONObject(1).getJSONObject("temp").getDouble("day");
            double nextDayFeelsLike = weather.getJSONArray("daily").getJSONObject(1).getJSONObject("feels_like").getDouble("day");
            double nextDayWindSpeed = weather.getJSONArray("daily").getJSONObject(1).getDouble("wind_speed");

            return new resultbyloc(citytemp,feeltemp,windspeed,weathernow,nextDayDes,nextDayTemp,nextDayFeelsLike,nextDayWindSpeed).toString();
        } else
        {
            String cityname = weather.getString("name");
            double citytemp = weather.getJSONObject("main").getDouble("temp");
            double feeltemp = weather.getJSONObject("main").getDouble("feels_like");
            double humidity = weather.getJSONObject("main").getDouble("humidity");
            int windspeed = weather.getJSONObject("wind").getInt("speed");
           //String icon=weather.getJSONObject("current").getString("icon");

            JSONArray array = weather.getJSONArray("weather");
            JSONObject object = array.getJSONObject(0);
            String weathernow = object.getString("description");
            return new result(cityname,citytemp,feeltemp,humidity,windspeed,weathernow).toString();
        }

    }

}
