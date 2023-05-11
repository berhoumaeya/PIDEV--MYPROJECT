/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailTelegram;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TelegramService {
    public void sendMessageToChat(String botToken, String chatId, String message) {
        try {
            String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
            urlString = String.format(urlString, botToken, chatId, URLEncoder.encode(message, "UTF-8"));

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
