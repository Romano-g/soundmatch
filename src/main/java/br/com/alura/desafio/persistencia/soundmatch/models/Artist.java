package br.com.alura.desafio.persistencia.soundmatch.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Music> musicList = new ArrayList<>();

	public Artist() {}

	public Artist (String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Music> getMusicList() {
		return musicList;
	}

	public void setMusicList(Music music) {
		musicList.add(music);
	}

	@Override
	public String toString() {
		return getName() + " | Músicas: " + getMusicList();
	}
}
