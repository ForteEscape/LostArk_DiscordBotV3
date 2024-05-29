package com.discord_bot.backend.api.v1.notice.model.service;

import com.discord_bot.backend.api.v1.notice.model.vo.News;
import com.discord_bot.backend.api.v1.common.util.DataCrawler;
import com.discord_bot.backend.api.v1.common.util.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    private final DataCrawler dataCrawler;
    private final JsonParser jsonParser;

    @Override
    public List<News> getLatestNotices() {
        String result = dataCrawler.getGameNotices("", new String("공지".getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

        return jsonParser.parseNotice(result);
    }
}
