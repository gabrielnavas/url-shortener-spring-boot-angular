package io.github.gabrielnavas.url_shortener.Link;

import java.time.LocalDateTime;

public class LinkAlreadyExpiredAtException extends RuntimeException {
    public LinkAlreadyExpiredAtException(String urlOriginal, LocalDateTime expireAt) {
        super(String.format("Link %s already expired at %s", urlOriginal, expireAt));
    }
}
