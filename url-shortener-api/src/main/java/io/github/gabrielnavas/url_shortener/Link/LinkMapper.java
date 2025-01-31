package io.github.gabrielnavas.url_shortener.Link;

import org.springframework.stereotype.Service;

@Service
public class LinkMapper {
    public LinkResponse toLinkResponse(Link link) {
        return LinkResponse.builder()
                .urlOriginal(link.getUrlOriginal())
                .token(link.getToken())
                .createdAt(link.getCreatedAt())
                .expireAt(link.getExpireAt())
                .urlQrCode(link.getUrlQrCode())
                .build();
    }
}
