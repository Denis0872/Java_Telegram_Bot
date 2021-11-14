package com.company;
import java.sql.*;

public class service {
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String usr = "root";
    private static final String password = "12345";

    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;

    public static Object getUserLoc (String chatId, String uName, String uSecondName, String uNick, double lat, double lon) {
        try {
            String select = "SELECT count(uChatId) FROM telegram.bot2 where uChatId = " + chatId + "";
            conn = DriverManager.getConnection(url, usr, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select);
            rs.next();
            int count = rs.getInt(1);


            if (count == 0) {
                String insert4= "CREATE TABLE IF NOT EXISTS `telegram`.`bot2` (\n" +
                        "  `uChatId` INT NOT NULL,\n" +
                        "  `uName` VARCHAR(45) NULL,\n" +
                        "  `uSecondName` VARCHAR(45) NULL,\n" +
                        "  `uNick` VARCHAR(45) NULL,\n" +
                        "  `lat` DOUBLE NULL,\n" +
                        "  `lon` DOUBLE NULL,\n" +
                        "  `LastDate` DATE  NOT NULL ,\n" +
                        "  PRIMARY KEY (`chatId`));";
                String insert = "INSERT INTO `telegram`.`bot2` (`uName`,`uSecondName`,`uNick`,`uChatId`, `lon`, `lat`, `LastDate`) " +
                        "VALUES ('" + uName + "','" + uSecondName + "','" + uNick + "','" + chatId + "'," + lon + "," + lat +", now());";
                conn = DriverManager.getConnection(url, usr, password);
                stmt = conn.createStatement();
                int rs = stmt.executeUpdate(insert);

                return weather.parseWeather(lat,lon,"","");
            } else {
                String update = "UPDATE telegram.bot2 SET lon =" + lon + ", lat =" + lat + " where uChatId =" + chatId + ";";
                conn = DriverManager.getConnection(url, usr, password);
                stmt = conn.createStatement();
                int rs = stmt.executeUpdate(update);

                return weather.parseWeather(lat,lon,"","");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } finally {
            try {
                conn.close();
            } catch (SQLException ce) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    try {
                        rs.close();
                    } catch (SQLException re) {
                    }
                }
            }

        }
        return null;
    }
    public static Object subscribe (String chatId, String uName, String uSecondName, String uNick, double lat, double lon) {
        try {
            String select = "SELECT count(uChatId) FROM telegram.bot2 where uChatId = " + chatId + "";
            conn = DriverManager.getConnection(url, usr, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select);
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                String insert = "INSERT INTO `telegram`.`bot2` (`uName`,`uSecondName`,`uNick`,`uChatId`, `Subscribe`, `lon`, `lat`, `LastDate`) " +
                        "VALUES ('" + uName + "','" + uSecondName + "','" + uNick + "','" + chatId + "', 1," + lon + "," + lat +", now());";
                conn = DriverManager.getConnection(url, usr, password);
                stmt = conn.createStatement();
                int rs = stmt.executeUpdate(insert);

                return "Вы успешно подписались на обновления2";
            } else {
                String update = "UPDATE telegram.bot2 SET Subscribe = 1 where uchatId =" + chatId + "";
                conn = DriverManager.getConnection(url, usr, password);
                stmt = conn.createStatement();
                int rs = stmt.executeUpdate(update);

                return "Вы подписались";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } finally {
            try {
                conn.close();
            } catch (SQLException ce) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    try {
                        rs.close();
                    } catch (SQLException re) {
                    }
                }
            }

        }
        return null;
    }
    public static Object unsubscribe (String chatId, String uName, String uSecondName, String uNick, double lat, double lon) {
        try {
            String select = "SELECT count(uChatId) FROM telegram.bot2 where uChatId = " + chatId + "";
            conn = DriverManager.getConnection(url, usr, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(select);
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                String insert = "INSERT INTO `telegram`.`bot2` (`uName`,`uSecondName`,`uNick`,`uChatId`, `Subscribe`, `lon`, `lat`, `LastDate`) " +
                        "VALUES ('" + uName + "','" + uSecondName + "','" + uNick + "','" + chatId + "', 0," + lon + "," + lat +", now());";
                conn = DriverManager.getConnection(url, usr, password);
                stmt = conn.createStatement();
                int rs = stmt.executeUpdate(insert);

                return "Вы итак не подписаны на обновления";
            } else {
                String update = "UPDATE telegram.bot2 SET Subscribe = 0 where uchatId =" + chatId + "";
                conn = DriverManager.getConnection(url, usr, password);
                stmt = conn.createStatement();
                int rs = stmt.executeUpdate(update);

                return "Вы не подписаны на обновления";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        } finally {
            try {
                conn.close();
            } catch (SQLException ce) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    try {
                        rs.close();
                    } catch (SQLException re) {
                    }
                }
            }

        }
        return null;
    }


}
