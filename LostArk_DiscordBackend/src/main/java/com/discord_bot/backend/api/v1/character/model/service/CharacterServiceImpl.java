package com.discord_bot.backend.api.v1.character.model.service;

import com.discord_bot.backend.api.v1.character.model.mapper.CharacterMapper;
import com.discord_bot.backend.api.v1.character.model.vo.CharacterResponse;
import com.discord_bot.backend.api.v1.common.util.DataCrawler;
import com.discord_bot.backend.api.v1.common.util.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharacterServiceImpl implements CharacterService {

    private final CharacterMapper characterMapper;
    private final DataCrawler dataCrawler;
    private final JsonParser jsonParser;

    @Override
    public List<CharacterResponse.CharacterInfo> getCharacterList(String characterName, String filter) {
        String result = dataCrawler.getCharacterGroup(characterName);

        return jsonParser.parseCharacterGroup(result, filter);
    }
}
