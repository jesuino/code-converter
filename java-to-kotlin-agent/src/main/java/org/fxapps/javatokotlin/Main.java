package org.fxapps.javatokotlin;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.fxapps.javatokotlin.service.JavaToKotlinService;
import org.jboss.logging.Logger;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class Main implements QuarkusApplication {

	@Inject
	JavaToKotlinService javaToKotlinService;

	private static final Logger log = Logger.getLogger(Main.class);

	@Override
	public int run(String... args) throws Exception {
		if (args.length > 0) {
			var path = Paths.get(args[0]);
			if (path.toFile().exists()) {
				var javaContent = Files.readString(path);
				log.info("Converting java file to kotlin...");
				var kotlinContent = javaToKotlinService.convertJavaToKotlin(javaContent);
				System.out.println(kotlinContent);
			} else {
				log.error("Can't open file path: " + path);
			}
		} else {
			log.warn("Please provide the path to a valid Java file.");
		}

		return 0;
	}

}
