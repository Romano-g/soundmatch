package br.com.alura.desafio.persistencia.soundmatch.models;

import jakarta.persistence.*;

@Entity
@Table(name = "musics")
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String album;

	@ManyToOne
	private Artist artist;

	public Music () {}

	public Music (Artist artist, String name, String album) {
		this.artist = artist;
		this.name = name;
		this.album = album;
	}

	public String getName() {
		return name;
	}

	public String getAlbum() {
		return album;
	}

	@Override
	public String toString() {
		return "Nome: " + getName() + " | Álbum: " + getAlbum();
	}
}
