package com.discord_bot.backend.api.v1.character.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.Collections;

public record CharacterResponse() {

    @Schema(description = "캐릭터 정보 api")
    @Builder
    public static record CharacterInfo() {

        public static CharacterInfo from() {
            return CharacterInfo.builder().build();
        }
    }
}
