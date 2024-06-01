package com.discord_bot.backend.api.v1.common.model.service;

import com.discord_bot.backend.api.v1.common.model.vo.AuctionResponse;

public interface CommonService {

    AuctionResponse getAuctionResponse(String price, String type);
}
