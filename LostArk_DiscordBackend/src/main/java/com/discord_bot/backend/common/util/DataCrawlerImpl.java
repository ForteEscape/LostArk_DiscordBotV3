package com.discord_bot.backend.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class DataCrawlerImpl implements DataCrawler {

    private static final String KEY_PREFIX = "Bearer ";

    @Value("${api.service-key}")
    private String SERVICE_KEY;

    @Value("${api.url.armories}")
    private String CHARACTER_ARMOR_URL;

    @Value("${api.url.characters}")
    private String CHARACTER_URL;

    @Value("${api.url.news}")
    private String NEWS_URL;

    @Override
    public String getCharacterInfo(String characterName) {
        String urlString = CHARACTER_ARMOR_URL + "?characterName=" + characterName;

        return parsingData(urlString);
    }

    @Override
    public String getCharacterGroup(String characterName) {
        String urlString = CHARACTER_URL + "?characterName=" + characterName;

        return parsingData(urlString);
    }

    @Override
    public String getGameNotices(String searchTitle, String noticeType) {
        String urlString = NEWS_URL + "?searchTitle=" + searchTitle + "&noticeType=" + noticeType;

        return parsingData(urlString);
    }

    @Override
    public String getGameEvents() {
        return "";
    }

    private String parsingData(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("ContentType", "application/json");
            conn.setRequestProperty("Authorization", KEY_PREFIX + SERVICE_KEY);

            int responseCode = conn.getResponseCode();

            BufferedReader br;

            if (responseCode >= 200 && responseCode < 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
            conn.disconnect();

            return sb.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
