package org.fxapps.codeconverter.service.impl;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fxapps.codeconverter.service.CodeConverterService;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.jlama.JlamaChatModel;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CodeConverterJLamaService implements CodeConverterService {

	private static final String MODEL_PATH = "tjake/Yi-Coder-1.5B-Chat-Jlama";
	private ChatLanguageModel model;

	@ConfigProperty(name = "huggingfaces.token", defaultValue = "")
	String huggingFacestoken;

	@PostConstruct
	void prepare() {
		model = JlamaChatModel.builder().authToken(huggingFacestoken).modelName(MODEL_PATH).temperature(0.3f).build();
	}

	@Override
	public String convert(String target, String input) {
		var userMessage = CODE_CONVERT_USER_MESSAGE.replaceAll("\\{target\\}", target).replaceAll("\\{input\\}", input);
		return generate(CODE_CONVERT_SYSTEM_MESSAGE, userMessage);
	}

	@Override
	public String createTests(String input) {
		var userMessage = CODE_TEST_USER_MESSAGE.replaceAll("\\{input\\}", input);
		return generate(CODE_TEST_SYSTEM_MESSAGE, userMessage);
	}

	@Override
	public String explainCode(String input) {
		var userMessage = CODE_EXPLAIN_USER_MESSAGE.replaceAll("\\{input\\}", input);
		return generate(CODE_EXPLAIN_SYSTEM_MESSAGE, userMessage);
	}

	private String generate(String systemMessage, String userMessage) {
		return model.generate(SystemMessage.from(systemMessage), UserMessage.from(userMessage)).content().text();
	}

}
