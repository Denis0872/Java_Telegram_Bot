package com.company;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Date;



public class Bot extends TelegramLongPollingBot  {
    public String chatId;
    public String uName;
    public String mytime;
    public String message;
    public String uSecondName;
    public String uNick;

    public void onUpdateReceived(Update update) {


        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                this.message = update.getMessage().getText().trim();
                this.chatId = update.getMessage().getChatId().toString();
                this.mytime = new Date((long) update.getMessage().getDate() * 1000).toString();
                User user = update.getMessage().getFrom();
                this.uName = user.getFirstName();
                this.uSecondName = user.getLastName();
                this.uNick = user.getUserName();
                double lat = 0;
                double lon = 0;
                System.out.println("Пользователь: " + user.getFirstName() + " " + user.getLastName() + " akкаунт " + user.getUserName() + " написал: "
                        + message + " at " + mytime + " " + chatId);
                SendMessage msg = new SendMessage();
                msg.setChatId(chatId);
                try {
                    msg.setText((String) cmd.inputmessage(message, chatId, uName, uSecondName, uNick, lat, lon));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg.setReplyMarkup(cmd.welcomeKeyboard());

                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().hasLocation()) {

                this.chatId = update.getMessage().getChatId().toString();
                User user = update.getMessage().getFrom();
                this.uName = user.getFirstName();
                this.uSecondName = user.getLastName();
                this.uNick = user.getUserName();
                double lat = update.getMessage().getLocation().getLatitude();
                double lon = update.getMessage().getLocation().getLongitude();
                SendMessage msg = new SendMessage();
                msg.setChatId(chatId);
                msg.setText((String) cmd.inputloc(message, chatId, uName, uSecondName, uNick, lat, lon));
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Wheather_Petergof_bot";
    }

    @Override
    public String getBotToken() {
        return "1679751960:AAFQb-XP8Sy8LDfUrB1wZgwy2-uw8M3mGik";
    }
}
