package com.discord_bot.backend.api.v1.common.util;

public interface DataCrawler {

    String getCharacterProfile(String characterName);

    String getCharacterEquipment(String characterName);

    String getCharacterCard(String characterName);

    String getCharacterEngrave(String characterName);

    String getCharacterGems(String characterName);

    String getCharacterGroup(String characterName);

    String getGameNotices(String searchTitle, String noticeType);

    String getGameEvents();
}
