package br.com.alura.desafio.persistencia.soundmatch.repository;

import br.com.alura.desafio.persistencia.soundmatch.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
	Optional<Artist> findByNameContainingIgnoreCase(String artist);
}
