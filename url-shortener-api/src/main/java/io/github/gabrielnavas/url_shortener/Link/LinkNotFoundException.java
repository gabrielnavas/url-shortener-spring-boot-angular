package io.github.gabrielnavas.url_shortener.Link;

public class LinkNotFoundException extends RuntimeException {

    public LinkNotFoundException(String url) {
        super(String.format("Link not found: %s", url));
    }
}
