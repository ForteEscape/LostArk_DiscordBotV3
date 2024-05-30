package com.discord_bot.backend.api.v1.common.controller;

import com.discord_bot.backend.api.v1.common.model.service.CommonService;
import com.discord_bot.backend.api.v1.common.model.vo.AuctionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/common")
@Tag(name = "공통 api", description = "공통 기능 api")
public class CommonController {

    private final CommonService commonService;

    @Operation(summary = "경매 api", description = "경매 입찰가에 대한 계산 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "경매 계산 성공", content = {
                    @Content(schema = @Schema(implementation = AuctionResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/auction")
    public ResponseEntity<?> getAuctionPay(@RequestParam("price") String price, @RequestParam("type") String type) {
        AuctionResponse auctionResponse = commonService.getAuctionResponse(price, type);

        return ResponseEntity.ok(auctionResponse);
    }
}
