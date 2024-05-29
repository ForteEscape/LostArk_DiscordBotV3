package com.discord_bot.backend.api.v1.character.model.service;

import com.discord_bot.backend.api.v1.character.model.mapper.CharacterMapper;
import com.discord_bot.backend.api.v1.common.util.DataCrawler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharacterServiceImpl implements CharacterService {

    private final CharacterMapper characterMapper;
    private final DataCrawler dataCrawler;

    @Override
    public void test() {
        String result = dataCrawler.getGameNotices("", new String("공지".getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

        log.info("dataCrawl result : " + result);
    }
}
