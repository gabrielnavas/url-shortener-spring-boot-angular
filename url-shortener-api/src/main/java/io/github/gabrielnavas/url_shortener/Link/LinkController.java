package io.github.gabrielnavas.url_shortener.Link;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/url-shortener")
@RequiredArgsConstructor
@Tag(name = "Link")
public class LinkController {

    private final LinkService linkService;

    @PostMapping
    public ResponseEntity<LinkResponse> createLink(
            @RequestBody @Valid LinkRequest request
    ) {
        final LinkResponse linkResponse = linkService.createLink(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(linkResponse);
    }

    @GetMapping("/{token}")
    public ResponseEntity<LinkResponse> findLinkByToken(
            @PathVariable String token
    ) {
        final LinkResponse linkResponse = linkService.findLinkByToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(linkResponse);
    }
}
