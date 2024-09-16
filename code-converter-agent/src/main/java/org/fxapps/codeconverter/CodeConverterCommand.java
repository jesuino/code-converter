package org.fxapps.codeconverter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.fxapps.codeconverter.service.impl.CodeConverterJLamaService;
import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command
public class CodeConverterCommand implements Runnable {

	private static final Logger log = Logger.getLogger(CodeConverterCommand.class);

	@Inject
	// CodeConverterQuarkusService codeConverterService;
	// For full inference on Java use the following
	CodeConverterJLamaService codeConverterService;
	@Parameters(arity = "1", description = "Input file or URL")
	String inputFile;

	@Option(names = { "-l", "--lang" }, description = "The target language", defaultValue = "Java")
	String targetLanguage;

	@Option(names = { "-t", "--tests" }, description = "Create unit tests for the target code")
	boolean createTests;

	@Option(names = { "-e", "--explain" }, description = "Explain the provided code")
	boolean explainCode;

	@Override
	public void run() {

		String inputContent;
		var result = "";
		try {
			inputContent = retrieveContent(inputFile);
		} catch (IOException e) {
			log.error("Not able to load the input: " + e.getMessage());
			log.debug(e);
			return;
		}
		if (createTests) {
			log.info("Creating tests for the provided file");
			result = codeConverterService.createTests(inputContent);
		}
		if (explainCode) {
			log.info("Generating code explanation");
			result = codeConverterService.explainCode(inputContent);
		} else {
			log.info("Converting file to " + targetLanguage + "...");
			result = codeConverterService.convert(targetLanguage, inputContent);
		}
		System.out.println(result);
	}

	private static String retrieveContent(String dest) throws IOException {
		if (dest.startsWith("http")) {
			log.info("Downloading file " + dest);
			var url = URI.create(dest).toURL();
			try (InputStream in = url.openStream()) {
				// Note: using a buffered read should be better in performance
				byte[] bytes = in.readAllBytes();
				return new String(bytes, StandardCharsets.UTF_8);
			}
		}
		var path = Paths.get(dest);
		return Files.readString(path);
	}

}
