package org.fxapps.javatokotlin.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped
public interface JavaToKotlinService {

	@SystemMessage("""
			You are an utility to convert Java code to Kotlin.
			You will receive the Java code and should only return the corresponding Kotlin code.
			Please don't use markdown or any additional text, just the corresponding Kotlin code.
			""")
	@UserMessage("""
			    Return the equivalent kotlin code for the following Java code {javaCode}
			""")
	String convertJavaToKotlin(String javaCode);

}