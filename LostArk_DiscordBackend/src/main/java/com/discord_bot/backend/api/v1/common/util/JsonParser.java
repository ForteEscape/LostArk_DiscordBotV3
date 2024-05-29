package com.discord_bot.backend.api.v1.common.util;

import com.discord_bot.backend.api.v1.notice.model.vo.News;

import java.util.List;

public interface JsonParser {

    List<News> parseNotice(String json);
}
