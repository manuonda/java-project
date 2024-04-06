package com.openai.chat.dto;

import java.util.List;

public record ActorFilms(
        String actor,
        List<String> movies
) {
}
