package org.fxapps.codeconverter.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped
public interface CodeConverterService {

	@SystemMessage("""
			You are a code converter that converts code from a programming language to another.
			You should respond only programming language code without additional comment or markdown.
			Follow the best practices when generating code.						
			""")
	@UserMessage("""
			    Convert the following Java code:
			    ```
			    {input}
			    ```
			    to {target} programming language and return code without additional comments.
			""")
	String convert(String target, String input);

}