package com.discord_bot.backend.api.v1.character.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record CharacterResponse() {

    @Schema(description = "원정대 캐릭터 정보 api")
    public static record CharacterInfo(
            @Schema(name = "server", description = "캐릭터의 소속 서버를 나타냅니다.", example = "실리안")
            @JsonProperty(value = "ServerName")
            String server,

            @Schema(name = "characterName", description = "캐릭터의 이름을 나타냅니다.", example = "2nd컴패니언")
            @JsonProperty(value = "CharacterName")
            String characterName,

            @Schema(name = "characterLevel", description = "캐릭터의 현재 전투 레벨을 나타냅니다.", example = "60")
            @JsonProperty(value = "CharacterLevel")
            int characterLevel,

            @Schema(name = "characterClass", description = "캐릭터의 직업을 나타냅니다.", example = "아르카나")
            @JsonProperty(value = "CharacterClassName")
            String characterClass,

            @Schema(name = "itemAvgLevel", description = "캐릭터의 아이템 레벨 평균을 나타냅니다.", example = "1,649.3")
            @JsonProperty(value = "ItemAvgLevel")
            String itemAvgLevel,

            @Schema(name = "itemMaxLevel", description = "캐릭터가 달성한 최대 아이템 레벨 평균을 나타냅니다.", example = "1,649.3")
            @JsonProperty(value = "ItemMaxLevel")
            String itemMaxLevel
    ) implements Comparable<CharacterInfo> {

        @Override
        public int compareTo(CharacterInfo o) {
            String processedString = this.itemMaxLevel().replace(",", "");
            String processedStringOther = o.itemMaxLevel().replace(",", "");

            return Double.compare(Double.parseDouble(processedStringOther), Double.parseDouble(processedString));
        }
    }
}
