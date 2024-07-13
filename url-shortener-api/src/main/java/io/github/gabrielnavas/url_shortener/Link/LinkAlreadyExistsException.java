package io.github.gabrielnavas.url_shortener.Link;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LinkAlreadyExistsException extends RuntimeException {
    public LinkAlreadyExistsException(
            @NotBlank
            @NotNull
            @Size(min = 1, max = 100)
            String link
    ) {
        super(String.format("Link already exists: %s", link));
    }
}
