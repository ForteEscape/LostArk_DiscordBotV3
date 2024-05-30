package com.discord_bot.backend.api.v1.notice.model.service;

import com.discord_bot.backend.api.v1.notice.model.vo.News;

import java.util.List;

public interface NoticeService {

    List<News> getLatestNotices(String searchTitle, String filter);
}
