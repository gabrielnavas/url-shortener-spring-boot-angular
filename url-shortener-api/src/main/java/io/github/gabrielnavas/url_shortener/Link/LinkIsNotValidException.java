package io.github.gabrielnavas.url_shortener.Link;

public class LinkIsNotValidException extends RuntimeException {
    public LinkIsNotValidException(String link) {
        super(String.format("Link isn't valid: %s", link));
    }
}
