package io.github.gabrielnavas.url_shortener.Link;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LinkResponse(
        String urlOriginal,
        String token,
        String urlQrCode,
        LocalDateTime expireAt,
        LocalDateTime createdAt
) {
}
