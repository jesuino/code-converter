package org.fxapps.javatokotlin;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.fxapps.javatokotlin.service.JavaToKotlinService;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class Main implements QuarkusApplication {
	
	@Inject
	JavaToKotlinService javaToKotlinService;

	@Override
	public int run(String... args) throws Exception {
		if (args.length > 0) {
			var path = Paths.get(args[0]);			
			if (path.toFile().exists()) {
				var javaContent = Files.readString(path);
				System.out.println("Converting java file to kotlin...");
				var kotlinContent = javaToKotlinService.convertJavaToKotlin(javaContent);
				System.out.println(kotlinContent);
			} else {
				System.out.println("Can't open file path: " + path);
			}
		} else {
			System.out.println("Please provide the path to a valid Java file.");
		}
		
		return 0;
	}
	
}
