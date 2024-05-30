package com.discord_bot.backend.api.v1.common.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "경매 계산 결과 DTO")
public record AuctionResponse(
        @Schema(name = "soldFee", description = "수수료")
        int soldFee,
        @Schema(name = "distribution", description = "분배금")
        int distribution,
        @Schema(name = "breakEvenPrice", description = "손익분기점")
        int breakEvenPrice,
        @Schema(name = "recommendPrice", description = "추천 입찰금")
        int recommendPrice,
        @Schema(name = "bestPrice", description = "최고의 입찰금")
        int bestPrice
) {
}
