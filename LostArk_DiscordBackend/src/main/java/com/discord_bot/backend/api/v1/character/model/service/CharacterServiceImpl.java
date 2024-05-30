package com.discord_bot.backend.api.v1.character.model.service;

import com.discord_bot.backend.api.v1.character.model.vo.CharacterInfoDetail;
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

    private final DataCrawler dataCrawler;
    private final JsonParser jsonParser;

    @Override
    public List<CharacterResponse.CharacterInfo> getCharacterList(String characterName, String filter) {
        String result = dataCrawler.getCharacterGroup(characterName);

        return jsonParser.parseCharacterGroup(result, filter);
    }

    public CharacterInfoDetail getCharacterDetail(String characterName) {
        CharacterInfoDetail.CharacterProfile characterProfile = getCharacterProfile(characterName);
        CharacterInfoDetail.CharacterEngraveInfo characterEngrave = getCharacterEngrave(characterName);
        CharacterInfoDetail.CharacterCardInfo characterCard = getCharacterCard(characterName);
        List<CharacterInfoDetail.CharacterEquipments> characterEquipment = getCharacterEquipment(characterName);
        List<CharacterInfoDetail.GemDetail> characterGems = getCharacterGems(characterName);

        log.info(characterProfile.toString());

        return CharacterInfoDetail.builder()
                .profile(characterProfile)
                .cards(characterCard)
                .equipments(characterEquipment)
                .engraves(characterEngrave.engraveList())
                .gems(characterGems)
                .build();
    }

    private List<CharacterInfoDetail.GemDetail> getCharacterGems(String characterName) {
        String characterGems = dataCrawler.getCharacterGems(characterName);

        return jsonParser.parseCharacterGems(characterGems);
    }

    private List<CharacterInfoDetail.CharacterEquipments> getCharacterEquipment(String characterName) {
        String characterEquipment = dataCrawler.getCharacterEquipment(characterName);

        return jsonParser.parseCharacterEquipment(characterEquipment);
    }

    private CharacterInfoDetail.CharacterCardInfo getCharacterCard(String characterName) {
        String characterCard = dataCrawler.getCharacterCard(characterName);

        log.info(characterCard);
        return jsonParser.parseCharacterCards(characterCard);
    }

    private CharacterInfoDetail.CharacterEngraveInfo getCharacterEngrave(String characterName) {
        String characterEngrave = dataCrawler.getCharacterEngrave(characterName);

        return jsonParser.parseCharacterEngrave(characterEngrave);
    }

    private CharacterInfoDetail.CharacterProfile getCharacterProfile(String characterName) {
        String result = dataCrawler.getCharacterProfile(characterName);

        return jsonParser.parseCharacterProfile(result);
    }
}
