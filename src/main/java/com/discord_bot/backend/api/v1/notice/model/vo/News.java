package com.discord_bot.backend.api.v1.notice.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "공지사항 결과")
public record News(
        @Schema(name = "title", description = "공지 제목", example = "점검 안내")
        @JsonProperty("Title")
        String title,

        @Schema(name = "date", description = "공지 작성 일자", example = "2024-05-12T13:24:12:32")
        @JsonProperty("Date")
        LocalDateTime date,

        @Schema(name = "link", description = "본문 링크", example = "https://lostark.game.onstove.com/News/Notice/Views/12794")
        @JsonProperty("Link")
        String link,

        @Schema(name = "type", description = "공지 종류", example = "점검")
        @JsonProperty("Type")
        String type
) {

}
