package br.com.alura.desafio.persistencia.soundmatch.main;

import br.com.alura.desafio.persistencia.soundmatch.models.Artist;
import br.com.alura.desafio.persistencia.soundmatch.models.Music;
import br.com.alura.desafio.persistencia.soundmatch.repository.ArtistRepository;
import br.com.alura.desafio.persistencia.soundmatch.services.ChatGpt;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
	private Scanner scanner = new Scanner(System.in);

	private ArtistRepository repository;
	private Optional<Artist> artistOptional;

	public Main(ArtistRepository repository) {
		this.repository = repository;
	}

	public void showMenu() {
		var option = -1;

		while (option != 0) {
			System.out.println("""
					Escolha uma das opções:
										
					1 - Cadastrar artista;
					2 - Cadastrar uma música;
					3 - Listar músicas;
					4 - Buscar músicas por artista;
					5 - Pesquisar dados sobre um artista;
										
					0 - Sair.
					""");
			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					addArtist();
					break;
				case 2:
					addTrack();
					break;
				case 3:
					listSongs();
					break;
				case 4:
					searchMusic();
					break;
				case 5:
					searchArtistChatGpt();
					break;
				case 0:
					System.out.println("\nEncerrando...\n");
					break;
				default:
					System.out.println("\nOpção inválida!\n");
					break;
			}
		}
	}

	private void addArtist() {
		System.out.println("Qual o nome do artista que gostaria de adicionar?");
		var name = scanner.nextLine();

		Artist artist = new Artist(name);

		repository.save(artist);

		System.out.println("\nArtista adicionado: " + artist.getName() + "\n");
	}

	private void addTrack() {
		System.out.println("\nDe qual artista é a música?");
		var artist = scanner.nextLine();

		artistOptional = repository.findByNameContainingIgnoreCase(artist);

		if (artistOptional.isPresent()) {
			var findArtist = artistOptional.get();

			System.out.println("\nQual a música para adicionar ao artista " + findArtist.getName() + "?");
			var findMusic = scanner.nextLine();

			System.out.println("\nA qual álbum a música " + findMusic + " pertence?");
			var findAlbum = scanner.nextLine();

			Music music = new Music(findArtist, findMusic, findAlbum);

			findArtist.setMusicList(music);
			repository.save(findArtist);

			System.out.println("\nAdicionado: " + music.getName() + " | Álbum: " +
					music.getAlbum() + " | Artista: " + findArtist.getName() + "\n");
		} else {
			System.out.println("\nArtista inválido!\n");
		}
	}

	private void listSongs() {
		List<Artist> artists = repository.findAll();

		System.out.println();
		artists.stream()
				.sorted(Comparator.comparing(Artist::getName))
				.forEach(System.out::println);
		System.out.println();
	}

	private void searchMusic() {
		System.out.println("\nQual artista quer buscar?");
		var artist = scanner.nextLine();

		artistOptional = repository.findByNameContainingIgnoreCase(artist);

		if (artistOptional.isPresent()) {
			var findArtist = artistOptional.get();
			System.out.println("\n" + findArtist + "\n");
		} else {
			System.out.println("Artista inválido");
		}
	}

	private void searchArtistChatGpt() {
		System.out.println("Sobre qual artista quer pesquisar?");
		var artist = scanner.nextLine();

		var response = ChatGpt.obterTraducao(artist);

		System.out.println(response + "\n");
	}
}
