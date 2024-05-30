package com.discord_bot.backend.api.v1.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class DataCrawlerImpl implements DataCrawler {

    private static final String KEY_PREFIX = "Bearer ";
    private static final String SEPARATOR = "/";

    @Value("${api.service-key}")
    private String SERVICE_KEY;

    @Value("${api.url.armories}")
    private String CHARACTER_ARMOR_URL;

    @Value("${api.url.characters}")
    private String CHARACTER_URL;

    @Value("${api.url.news}")
    private String NEWS_URL;

    @Value("${api.context-root}")
    private String CONTEXT_PATH;

    @Override
    public String getCharacterInfo(String characterName) {
        String urlString = CONTEXT_PATH + CHARACTER_ARMOR_URL + "?characterName=" + characterName;

        return parsingData(urlString);
    }

    @Override
    public String getCharacterGroup(String characterName) {
        characterName = URLEncoder.encode(characterName, StandardCharsets.UTF_8);
        String urlString = CONTEXT_PATH + CHARACTER_URL + SEPARATOR + characterName + SEPARATOR + "siblings";

        return parsingData(urlString);
    }

    @Override
    public String getGameNotices(String searchTitle, String noticeType) {
        String urlString;

        if (searchTitle.isBlank() && noticeType.isBlank()) {
            urlString = CONTEXT_PATH + NEWS_URL + "/notices";
        } else {
            if(searchTitle.isBlank()) {
                urlString = CONTEXT_PATH + NEWS_URL + "/notices?type=" + noticeType;
            } else if(noticeType.isBlank()) {
                urlString = CONTEXT_PATH + NEWS_URL + "/notices?searchText=" + searchTitle;
            } else {
                urlString = CONTEXT_PATH + NEWS_URL + "/notices?searchText=" + searchTitle + "&type=" + noticeType;
            }
        }

        return parsingData(urlString);
    }

    @Override
    public String getGameEvents() {
        return "";
    }

    private String parsingData(String urlString) {
        try {
            log.info("url " + urlString);

            URL url = new URL(urlString);
            log.info(url.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Authorization", KEY_PREFIX + SERVICE_KEY);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");

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
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
