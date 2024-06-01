package com.discord_bot.backend.api.v1.character.model.service;

import com.discord_bot.backend.api.v1.character.model.vo.CharacterInfoDetail;
import com.discord_bot.backend.api.v1.character.model.vo.CharacterResponse;

import java.util.List;

public interface CharacterService {

    List<CharacterResponse.CharacterInfo> getCharacterList(String characterName, String filter);

    CharacterInfoDetail getCharacterDetail(String characterName);
}
