package org.fxapps.codeconverter.service.impl;

import org.fxapps.codeconverter.service.CodeConverterService;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped
public interface CodeConverterQuarkusService extends CodeConverterService {

	@SystemMessage(CODE_CONVERT_SYSTEM_MESSAGE)
	@UserMessage(CODE_CONVERT_USER_MESSAGE)
	String convert(String target, String input);

	@SystemMessage(CODE_TEST_SYSTEM_MESSAGE)
	@UserMessage(CODE_TEST_USER_MESSAGE)
	String createTests(String input);

	@SystemMessage(CODE_EXPLAIN_SYSTEM_MESSAGE)
	@UserMessage(CODE_EXPLAIN_USER_MESSAGE)
	String explainCode(String input);

}
