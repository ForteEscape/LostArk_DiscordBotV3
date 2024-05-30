package com.discord_bot.backend.api.v1.character.controller;

import com.discord_bot.backend.api.v1.character.model.service.CharacterService;
import com.discord_bot.backend.api.v1.character.model.vo.CharacterResponse;
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
@RequestMapping("/api/v1/character")
@Tag(name = "Character", description = "캐릭터 관련 api 엔드포인트")
public class CharacterController {

    private final CharacterService characterService;

    @Operation(description = "해당 계정이 가진 모든 캐릭터를 가져오는 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "캐릭터 조회 성공", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CharacterResponse.CharacterInfo.class)))
            }),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping
    public ResponseEntity<List<CharacterResponse.CharacterInfo>> getCharacterList(
            @RequestParam(value = "characterName") String characterName,
            @RequestParam(value = "filter", required = false) String filter) {
        List<CharacterResponse.CharacterInfo> characterList = characterService.getCharacterList(characterName, filter);
        return ResponseEntity.ok(characterList);
    }

}
