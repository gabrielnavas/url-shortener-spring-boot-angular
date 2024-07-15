package io.github.gabrielnavas.url_shortener.Link;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;

    public LinkResponse createLink(LinkRequest request) {
        try {
            URL url = new URL(request.urlOriginal());
            url.toURI();
        } catch (URISyntaxException | MalformedURLException ex) {
            throw new LinkIsNotValidException(request.urlOriginal());
        }

        verifyUrlOriginal(request.urlOriginal());

        final LocalDateTime now = getNow();
        final LocalDateTime expireDays = now.plusDays(7);

        final String urlShorted = generateToken();

        Link link = Link.builder()
                .urlOriginal(request.urlOriginal())
                .token(urlShorted)
                .createdAt(now)
                .expireAt(expireDays)
                .urlQrCode(generateQrCode())
                .isExpired(false)
                .build();

        link = linkRepository.save(link);
        return linkMapper.toLinkResponse(link);
    }

    public LinkResponse findLinkByToken(String token) {
        final LocalDateTime now = getNow();
        Optional<Link> optionalLink = linkRepository.findValidLinkByToken(token, now);
        if (optionalLink.isEmpty()) {
            throw new LinkNotFoundException(token);
        }
        final Link link = optionalLink.get();
        return linkMapper.toLinkResponse(link);
    }

    private String generateToken() {
        final int min = 5;
        final int max = 5;
        final int maxTries = 50;
        final LocalDateTime now = getNow();
        for (int i = 0; i < 50; i++) {
            String urlShorted = RandomStringUtils.randomAlphanumeric(min, max);
            Optional<Link> optionalLink = linkRepository.findValidLinkByToken(urlShorted, now);
            if (optionalLink.isEmpty()) {
                return urlShorted;
            }
        }
        throw new RuntimeException(String.format("you have exceeded %d tries", maxTries));
    }

    private void verifyUrlOriginal(String urlOriginal) {
        final LocalDateTime now = getNow();
        Optional<Link> optionalLink = linkRepository.findValidLinkByUrlOriginal(urlOriginal, now);
        if (optionalLink.isEmpty()) {
            return;
        }

        final Link link = optionalLink.get();

        if (link.getExpireAt().isBefore(getNow())) {
            link.setExpired(true);
            linkRepository.save(link);
            throw new LinkAlreadyExpiredAtException(link.getUrlOriginal(), link.getExpireAt());
        } else {
            throw new LinkAlreadyExistsException(urlOriginal);
        }
    }

    private LocalDateTime getNow() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
    }

    private String generateQrCode() {
        return "not implemented yet";
    }
}
