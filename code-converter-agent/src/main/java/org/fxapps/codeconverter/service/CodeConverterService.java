package org.fxapps.codeconverter.service;

public interface CodeConverterService {

	public static final String CODE_CONVERT_SYSTEM_MESSAGE = """
			You are a code converter that converts code from a programming language to another.
			You should respond only programming language code without additional comment or markdown.
			Follow the best practices when generating code.
			""";
	public static final String CODE_CONVERT_USER_MESSAGE = """
			    Convert the following code:
			    ```
			    {input}
			    ```
			    to {target} programming language and return code without additional comments.
			""";

	public static final String CODE_TEST_SYSTEM_MESSAGE = """
			You are responsible to create Unit tests for the given code.
			You should respond only programming language code without additional comment or markdown.
			Follow the best practices when generating code.
			""";
	public static final String CODE_TEST_USER_MESSAGE = """
			    Create unit tests for the following code:
			    ```
			    {input}
			    ```
			   Return only code without additional comments.
			""";

	/**
	 * Receives a code from any programming language and convert to another language
	 * specific by target.
	 * 
	 * @param target The target (output) programming language
	 * @param input  The inpurt source code payload
	 * 
	 * @return The converted code to the target language
	 */
	String convert(String target, String input);

	/**
	 * Create unit tests for a given input
	 * 
	 * @param input The source code to create tests
	 * @return Unit tests for the given code
	 */
	String createTests(String input);

}