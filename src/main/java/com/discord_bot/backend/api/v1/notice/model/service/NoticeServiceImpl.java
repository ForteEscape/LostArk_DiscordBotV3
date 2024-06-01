package com.discord_bot.backend.api.v1.notice.model.service;

import com.discord_bot.backend.api.v1.common.util.DataCrawler;
import com.discord_bot.backend.api.v1.common.util.JsonParser;
import com.discord_bot.backend.api.v1.notice.model.vo.News;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    private final DataCrawler dataCrawler;
    private final JsonParser jsonParser;

    @Override
    public List<News> getLatestNotices(String searchTitle, String filter) {
        String result = dataCrawler.getGameNotices(searchTitle, filter);

        return jsonParser.parseNotice(result);
    }
}
