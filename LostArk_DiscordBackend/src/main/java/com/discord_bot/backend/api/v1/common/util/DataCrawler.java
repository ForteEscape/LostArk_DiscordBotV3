package com.discord_bot.backend.api.v1.common.util;

public interface DataCrawler {

    String getCharacterInfo(String characterName);

    String getCharacterGroup(String characterName);

    String getGameNotices(String searchTitle, String noticeType);

    String getGameEvents();
}
