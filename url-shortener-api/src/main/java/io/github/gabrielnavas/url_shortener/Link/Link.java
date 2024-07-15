package io.github.gabrielnavas.url_shortener.Link;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "links")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 500)
    private String urlOriginal;

    @Column(nullable = false, length = 100)
    private String token;

    @Column(nullable = false)
    private String urlQrCode;

    @Column(nullable = false)
    private LocalDateTime expireAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean isExpired;
}
