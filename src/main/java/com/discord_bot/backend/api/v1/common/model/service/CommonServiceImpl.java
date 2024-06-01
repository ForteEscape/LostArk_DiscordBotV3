package com.discord_bot.backend.api.v1.common.model.service;

import com.discord_bot.backend.api.v1.common.model.vo.AuctionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommonServiceImpl implements CommonService{

    @Override
    public AuctionResponse getAuctionResponse(String price, String type) {
        int typeNumber = Integer.parseInt(type);

        int soldFee = 0;
        int distribution = 0;
        int breakEvenPrice = 0;
        int recommendPrice = 0;
        int bestPrice = 0;

        double priceData = Double.parseDouble(price);

        soldFee = (int)Math.floor(priceData * 0.05);
        distribution = (int)Math.floor(priceData / (typeNumber - 1));
        breakEvenPrice = (int)Math.floor(priceData * 0.95 * ((typeNumber - 1) / (double)typeNumber));
        recommendPrice = (int)Math.floor((double)breakEvenPrice / 1.1);
        bestPrice = (int)Math.floor((double)recommendPrice / 1.1);

        return AuctionResponse.builder()
                .soldFee(soldFee)
                .distribution(distribution)
                .breakEvenPrice(breakEvenPrice)
                .recommendPrice(recommendPrice)
                .bestPrice(bestPrice)
                .build();
    }
}
