package com.discord_bot.backend.api.v1.character.model.service;

import com.discord_bot.backend.api.v1.character.model.mapper.CharacterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterMapper characterMapper;


}
