package com.discord_bot.backend.common.util;

public interface DataCrawler {

    String getCharacterInfo(String characterName);

    String getCharacterGroup(String characterName);

    String getGameNotices(String searchTitle, String noticeType);

    String getGameEvents();
}
