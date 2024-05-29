package com.discord_bot.backend.api.v1.notice.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record News(
        @JsonProperty("Title")
        String title,

        @JsonProperty("Date")
        LocalDateTime date,

        @JsonProperty("Link")
        String link,

        @JsonProperty("Type")
        String type
) {

}
