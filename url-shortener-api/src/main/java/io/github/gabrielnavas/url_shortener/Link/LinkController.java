package io.github.gabrielnavas.url_shortener.Link;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/url-shortener")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping
    public ResponseEntity<LinkResponse> createLink(
            @RequestBody @Valid LinkRequest request
    ) {
        final LinkResponse linkResponse = linkService.createLink(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(linkResponse);
    }

    @GetMapping("/{url-shortened}")
    public ResponseEntity<LinkResponse> getLink(
            @PathVariable("url-shortened") String urlShortened,
            @RequestHeader String host
    ) {
        final LinkResponse linkResponse = linkService.findLinkByUrlShortened(urlShortened);
        return ResponseEntity.status(HttpStatus.OK).body(linkResponse);
    }
}
