package br.com.alura.desafio.persistencia.soundmatch.services;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ChatGpt {
	public static String obterTraducao(String artist) {
		OpenAiService service = new OpenAiService(System.getenv("GPT_KEY"));

		CompletionRequest request = CompletionRequest.builder()
				.model("gpt-3.5-turbo-instruct")
				.prompt("faça um resumo curto sobre o artista: " + artist)
				.maxTokens(1000)
				.temperature(0.7)
				.build();

		var resposta = service.createCompletion(request);
		return resposta.getChoices().get(0).getText();
	}
}
