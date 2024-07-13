package io.github.gabrielnavas.url_shortener.Exception;

import lombok.Builder;

import java.util.List;

@Builder
public record ApiError(
        List<String> errors
) {
}
