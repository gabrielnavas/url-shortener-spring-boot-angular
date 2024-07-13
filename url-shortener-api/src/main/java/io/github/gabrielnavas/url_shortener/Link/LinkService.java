package io.github.gabrielnavas.url_shortener.Link;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

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
        verifyUrlOriginal(request.urlOriginal());

        final LocalDateTime now = getNow();
        final LocalDateTime expireDays = now.plusDays(7);

        final String urlShorted = generateRandomShortUrl();

        Link link = Link.builder()
                .urlOriginal(request.urlOriginal())
                .urlShorted(urlShorted)
                .createdAt(now)
                .expireAt(expireDays)
                .urlQrCode(generateQrCode(urlShorted))
                .isExpired(false)
                .build();

        link = linkRepository.save(link);
        return linkMapper.toLinkResponse(link);
    }

    public LinkResponse findLinkByUrlShortened(String urlShortened) {
        final LocalDateTime now = getNow();
        Optional<Link> optionalLink = linkRepository.findByUrlShorted(urlShortened, now);
        if (optionalLink.isEmpty()) {
            throw new LinkNotFoundException(urlShortened);
        }
        final Link link = optionalLink.get();
        return linkMapper.toLinkResponse(link);
    }

    private String generateRandomShortUrl() {
        final int min = 5;
        final int max = 5;
        final int maxTries = 50;
        final LocalDateTime now = getNow();
        for (int i = 0; i < 50; i++) {
            String urlShorted = RandomStringUtils.randomAlphanumeric(min, max);
            Optional<Link> optionalLink = linkRepository.findByUrlShorted(urlShorted, now);
            if (optionalLink.isEmpty()) {
                return urlShorted;
            }
        }
        throw new RuntimeException(String.format("you have exceeded %d tries", maxTries));
    }

    private void verifyUrlOriginal(String urlOriginal) {
        final LocalDateTime now = getNow();
        Optional<Link> optionalLink = linkRepository.findByUrlOriginal(urlOriginal, now);
        if (optionalLink.isPresent()) {
            final Link link = optionalLink.get();
            if (link.getExpireAt().isBefore(getNow())) {
                link.setExpired(true);
                linkRepository.save(link);
                throw new LinkAlreadyExpiredAtException(link.getUrlOriginal(), link.getExpireAt());
            } else {
                throw new LinkAlreadyExistsException(urlOriginal);
            }
        }
    }

    private LocalDateTime getNow() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
    }

    private String generateQrCode(String urlShortened) {
        return "not implemented yet";
    }
}
