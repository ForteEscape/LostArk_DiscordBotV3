package com.discord_bot.backend.api.v1.common.util;

import com.discord_bot.backend.api.v1.character.model.vo.CharacterInfoDetail;
import com.discord_bot.backend.api.v1.character.model.vo.CharacterResponse;
import com.discord_bot.backend.api.v1.notice.model.vo.News;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JsonParserImpl implements JsonParser {

    private final ObjectMapper objectMapper;
    private static final Set<String> equipmentTypeFilterSet;

    static {
        equipmentTypeFilterSet = new HashSet<>(Arrays.asList("팔찌", "부적", "문장", "나침반", "어빌리티 스톤"));
    }

    @Override
    public List<News> parseNotice(String json) {
        try {
            News[] result = objectMapper.readValue(json, News[].class);
            LocalDateTime now = LocalDateTime.now();

            return Arrays.stream(result)
                    .filter(e -> e.date().isAfter(now.minusDays(7L)))
                    .toList();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CharacterResponse.CharacterInfo> parseCharacterGroup(String json, String filter) {
        try {
            CharacterResponse.CharacterInfo[] result = objectMapper.readValue(json, CharacterResponse.CharacterInfo[].class);
            List<CharacterResponse.CharacterInfo> resultList;

            if (!filter.isBlank()) {
                resultList = Arrays.stream(result)
                        .filter(e -> e.server().equals(filter))
                        .sorted()
                        .toList();
            } else {
                resultList = Arrays.asList(result);
            }

            return resultList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CharacterInfoDetail.CharacterProfile parseCharacterProfile(String result) {
        try {
            return objectMapper.readValue(result, CharacterInfoDetail.CharacterProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CharacterInfoDetail.CharacterEngraveInfo parseCharacterEngrave(String characterEngrave) {
        try {
            JsonNode node = objectMapper.readTree(characterEngrave);
            JsonNode effectArray = node.get("Effects");

            List<CharacterInfoDetail.CharacterEngraveInfo.Engrave> engraveList = new ArrayList<>();

            for(int i = 0; i < effectArray.size(); i++) {
                JsonNode effect = effectArray.get(i);
                String effectIcon = effect.get("Icon").asText();
                String effectName = effect.get("Name").asText();

                engraveList.add(new CharacterInfoDetail.CharacterEngraveInfo.Engrave(effectIcon, effectName));
            }

            return new CharacterInfoDetail.CharacterEngraveInfo(engraveList);
        }  catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CharacterInfoDetail.CharacterCardInfo parseCharacterCards(String characterCard) {
        try {
            JsonNode node = objectMapper.readTree(characterCard);

            JsonNode cards = node.get("Cards");
            List<CharacterInfoDetail.Card> cardList = new ArrayList<>();

            for (int i = 0; i < cards.size(); i++) {
                JsonNode card = cards.get(i);

                int slot = card.get("Slot").asInt();
                String name = card.get("Name").asText();
                String awakeCount = card.get("AwakeCount").asText();

                cardList.add(new CharacterInfoDetail.Card(slot, name, awakeCount));
            }

            List<CharacterInfoDetail.CardSet> cardSetList = new ArrayList<>();

            JsonNode effect = node.get("Effects");
            for(int i = 0; i < effect.size(); i++) {
                JsonNode effectItem = effect.get(i).get("Items");

                for(int j =0; j < effectItem.size(); j++) {
                    JsonNode item = effectItem.get(j);

                    String itemName = item.get("Name").asText();
                    String itemDescription = item.get("Description").asText();

                    cardSetList.add(new CharacterInfoDetail.CardSet(itemName, itemDescription));
                }
            }

            return new CharacterInfoDetail.CharacterCardInfo(cardList, cardSetList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CharacterInfoDetail.CharacterEquipments> parseCharacterEquipment(String characterEquipment) {
        try {
            CharacterInfoDetail.CharacterEquipments[] result = objectMapper.readValue(characterEquipment,
                    CharacterInfoDetail.CharacterEquipments[].class);

            return Arrays.stream(result)
                    .filter(e -> !equipmentTypeFilterSet.contains(e.type()))
                    .toList();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
