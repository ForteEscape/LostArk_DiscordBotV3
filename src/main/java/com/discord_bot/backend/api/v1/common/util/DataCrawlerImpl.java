package com.discord_bot.backend.api.v1.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
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
    public String getCharacterProfile(String characterName) { // profile parsing
        characterName = URLEncoder.encode(characterName, StandardCharsets.UTF_8);
        String urlString = CONTEXT_PATH + CHARACTER_ARMOR_URL + SEPARATOR + characterName + SEPARATOR + "profiles";

        return parsingData(urlString);
    }

    @Override
    public String getCharacterEquipment(String characterName) {
        characterName = URLEncoder.encode(characterName, StandardCharsets.UTF_8);
        String urlString = CONTEXT_PATH + CHARACTER_ARMOR_URL + SEPARATOR + characterName + SEPARATOR + "equipment";

        return parsingData(urlString);
    }

    @Override
    public String getCharacterCard(String characterName) {
        characterName = URLEncoder.encode(characterName, StandardCharsets.UTF_8);
        String urlString = CONTEXT_PATH + CHARACTER_ARMOR_URL + SEPARATOR + characterName + SEPARATOR + "cards";

        return parsingData(urlString);
    }

    @Override
    public String getCharacterEngrave(String characterName) {
        characterName = URLEncoder.encode(characterName, StandardCharsets.UTF_8);
        String urlString = CONTEXT_PATH + CHARACTER_ARMOR_URL + SEPARATOR + characterName + SEPARATOR + "engravings";

        return parsingData(urlString);
    }

    @Override
    public String getCharacterGems(String characterName) {
        characterName = URLEncoder.encode(characterName, StandardCharsets.UTF_8);
        String urlString = CONTEXT_PATH + CHARACTER_ARMOR_URL + SEPARATOR + characterName + SEPARATOR + "gems";

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
        searchTitle = StringUtils.hasLength(searchTitle) ? URLEncoder.encode(searchTitle, StandardCharsets.UTF_8) : "";
        noticeType = StringUtils.hasLength(noticeType) ? URLEncoder.encode(noticeType, StandardCharsets.UTF_8) : "";

        if (searchTitle.isBlank() && noticeType.isBlank()) {
            urlString = CONTEXT_PATH + NEWS_URL + "/notices";
        } else {
            if (searchTitle.isBlank()) {
                urlString = CONTEXT_PATH + NEWS_URL + "/notices?type=" + noticeType;
            } else if (noticeType.isBlank()) {
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
            URL url = new URL(urlString);
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
            throw new RuntimeException(e);
        }
    }
}
