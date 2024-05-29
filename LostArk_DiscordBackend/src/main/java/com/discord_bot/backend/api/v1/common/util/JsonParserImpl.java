package com.discord_bot.backend.api.v1.common.util;

import com.discord_bot.backend.api.v1.notice.model.vo.News;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonParserImpl implements JsonParser {

    private final ObjectMapper objectMapper;

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
}
