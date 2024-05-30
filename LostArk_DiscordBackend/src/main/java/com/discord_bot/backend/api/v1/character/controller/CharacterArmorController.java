package com.discord_bot.backend.api.v1.character.controller;

import com.discord_bot.backend.api.v1.character.model.service.CharacterService;
import com.discord_bot.backend.api.v1.character.model.vo.CharacterInfoDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/armories")
@RequiredArgsConstructor
public class CharacterArmorController {

    private final CharacterService characterService;

    @Operation(summary = "캐릭터 장비 정보", description = "캐릭터의 장비 정보 출력 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 장비 조회 성공", content = {
                    @Content(schema = @Schema(implementation = CharacterInfoDetail.class))
            }),
            @ApiResponse(responseCode = "500", description = "서버 에러 발생")
    })
    @GetMapping
    public ResponseEntity<CharacterInfoDetail> getCharacterDetailInfo(@RequestParam("characterName") String characterName) {
        CharacterInfoDetail result = characterService.getCharacterDetail(characterName);

        return ResponseEntity.ok(result);
    }
}
