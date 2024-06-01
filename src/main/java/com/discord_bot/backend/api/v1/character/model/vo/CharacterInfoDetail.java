package com.discord_bot.backend.api.v1.character.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record CharacterInfoDetail(
        CharacterProfile profile,
        List<CharacterEquipments> equipments,
        CharacterCardInfo cards,
        List<CharacterEngraveInfo.Engrave> engraves,
        List<GemDetail> gems
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CharacterProfile(
            @JsonProperty(value = "CharacterImage")
            String characterImagePath,

            @JsonProperty(value = "CharacterName")
            String characterName,

            @JsonProperty(value = "ServerName")
            String serverName,

            @JsonProperty(value = "CharacterLevel")
            int characterLevel,

            @JsonProperty(value = "CharacterClassName")
            String characterClassName,

            @JsonProperty(value = "ItemAvgLevel")
            String itemAvgLevel,

            @JsonProperty(value = "ExpeditionLevel")
            int expeditionLevel,

            @JsonProperty(value = "TownName")
            String townName,

            @JsonProperty(value = "Title")
            String title,

            @JsonProperty(value = "GuildName")
            String guildName,

            @JsonProperty(value = "Stats")
            List<Status> status
    ) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Status(
                @JsonProperty(value = "Type")
                String type,
                @JsonProperty(value = "Value")
                String value
        ) {

        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CharacterEquipments(
            @JsonProperty(value = "Type")
            String type,
            @JsonProperty(value = "Name")
            String name,
            @JsonProperty(value = "Grade")
            String grade
    ) {

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CharacterEngraveInfo(
            List<Engrave> engraveList
    ) {

        public record Engrave(
                String icon,
                String name
        ) {

        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CharacterCardInfo(
            List<Card> cardInfo,
            List<CardSet> cardSetInfo
    ) {

    }

    public record Card(
            int slot,
            String name,
            String awakeCount
    ) {

    }

    public record CardSet(
            String name,
            String description
    ) {

    }

    @Builder
    public record GemDetail(
            int gemSlot,
            int gemLevel,
            String skillName,
            String description
    ){

    }

    public record Gem(
            int gemSlot,
            String name,
            String description
    ) {

    }
}
