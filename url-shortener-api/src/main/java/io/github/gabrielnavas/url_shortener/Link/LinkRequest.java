package io.github.gabrielnavas.url_shortener.Link;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LinkRequest(
        @NotBlank
        @NotNull
        @Size(min = 1, max = 500)
        @JsonProperty("url")
        String urlOriginal
) {
}
