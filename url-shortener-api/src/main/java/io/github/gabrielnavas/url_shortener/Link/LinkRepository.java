package io.github.gabrielnavas.url_shortener.Link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {

    @Query("""
                SELECT link
                FROM Link link
                WHERE link.urlShorted = :urlShorted
                AND link.expireAt > :now
            """)
    Optional<Link> findByUrlShorted(String urlShorted, LocalDateTime now);

    @Query("""
                SELECT link
                FROM Link link
                WHERE link.urlOriginal = :urlOriginal
                AND link.expireAt > :now
            """)
    Optional<Link> findByUrlOriginal(String urlOriginal, LocalDateTime now);
}
