package com.company;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class cmd extends Bot
{
    private static Component message;

    public static Object inputmessage(String msg, String chatId, String uName, String uSecondName, String uNick, double lat, double lon)throws IOException {

        String error = "Такого города нетy";
        String WelcomeMessage = "Добро пожаловать" + "\n"
                + "Я Telegram бот." + "\n"
                + " узнай погоду" + "\n"
                + "Для справки нажми /help";

        String HelpMessage = "Я могу подсказать погоду ";

        return switch (msg) {
            case ("Помощник") -> WelcomeMessage;
            case ("/help") -> HelpMessage;
            case ("Локация") -> inputloc(msg, chatId, uName, uSecondName, uNick, lat, lon);
            case ("subscribe") -> service.subscribe(chatId, uName, uSecondName, uNick, lat, lon);
            case ("unsubscribe") -> service.unsubscribe(chatId, uName, uSecondName, uNick, lat, lon);
            case ("Санкт-Петербург") -> weather.parseWeather(lat, lon, "Saint%20Petersburg", "").toString();
            case ("Парыж") -> weather.parseWeather(lat, lon, "", "Paris").toString();
            default -> weather.parseWeather(lat, lon, "", msg).toString();
        };
    }


    public static Object inputloc(String msg, String chatId, String uName, String uSecondName, String uNick, double lat, double lon) {
        return  service.getUserLoc(chatId, uName, uSecondName, uNick, lat,lon);

    }
    public static ReplyKeyboardMarkup welcomeKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton keySPB = new KeyboardButton();
        keySPB.setText("Санкт-Петербург");
        keyboardFirstRow.add(keySPB);
        KeyboardButton keyParis = new KeyboardButton();
        keyParis.setText("Парыж");
        keyboardFirstRow.add(keyParis);
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardButton keyLOC = new KeyboardButton();
        keyLOC.setText("локация");
        keyboardSecondRow.add(keyLOC);
        keyLOC.getRequestLocation();
        keyLOC.setRequestLocation(true);
        KeyboardButton keyAsk = new KeyboardButton();
        keyAsk.setText("Помощник");
        keyboardSecondRow.add(keyAsk);
        KeyboardRow keyboardRow3 = new KeyboardRow();
        KeyboardButton subscribe = new KeyboardButton();
        subscribe.setText("subscribe");
        KeyboardButton unsubscribe = new KeyboardButton();
        unsubscribe.setText("unsubscribe");
        keyboardRow3.add(subscribe);
        keyboardRow3.add(unsubscribe);
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardRow3);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }
}
