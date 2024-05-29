package com.discord_bot.backend.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataCrawlerImpl implements DataCrawler {

    @Value("${api.service-key}")
    private String SERVICE_KEY;

    @Value("${api.url.armories}")
    private String CHARACTER_ARMOR_URL;

    @Override
    public String getCharacterInfo(String characterName) {
        return "";
    }

    @Override
    public String getCharacterGroup(String characterName) {
        return "";
    }

    @Override
    public String getGameNotices(String searchTitle, String noticeType) {
        return "";
    }

    @Override
    public String getGameEvents() {
        return "";
    }
}
