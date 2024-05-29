package com.discord_bot.backend.api.v1.character.controller;

import com.discord_bot.backend.api.v1.character.model.service.CharacterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/character")
@Tag(name="Character", description = "캐릭터 관련 api 엔드포인트")
public class CharacterController {

    private final CharacterService characterService;

    public ResponseEntity<?> getCharacterInfo(String characterName) {
        return null;
    }
}
