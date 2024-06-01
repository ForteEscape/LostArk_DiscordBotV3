package com.discord_bot.backend.api.v1.notice.controller;

import com.discord_bot.backend.api.v1.notice.model.service.NoticeService;
import com.discord_bot.backend.api.v1.notice.model.vo.News;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
@Tag(name="공지사항 API", description = "공지사항 관련 api")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(description = "현재 시간으로 부터 7일 이전까지의 공지를 조회하는 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "공지 조회 성공", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = News.class)))
            }),
            @ApiResponse(responseCode = "500", description = "서버 문제 발생")
    })
    @GetMapping("/notices")
    public ResponseEntity<List<News>> getNotices(
            @RequestParam(value = "searchTitle", required = false) String searchTitle,
            @RequestParam(value = "filter", required = false) String filter
    ) {
        List<News> latestNotices = noticeService.getLatestNotices(searchTitle, filter);

        return ResponseEntity.ok(latestNotices);
    }
}
