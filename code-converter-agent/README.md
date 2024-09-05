# java-to-kotlin-agent

This project uses Quarkus, the Supersonic Subatomic Java Framework.

The result of this application is a command line utility that receives a path to a Code file and return its Java equivalent or you can select the output language.

### Requirements:

* Maven 
* Java JDK 21
* Ollama (possibly with model codegemma:7b)

### Building

Having Java JDK 21, just run `mvn clean install -Pnative`

### Running

Once built, you can run the executable `code-covnerter-agent` that can be found in `target` folder. Remember to pass a path to a Java file as a parameter.

Example:

![image](https://github.com/user-attachments/assets/5e9575da-085a-4d8a-b6e2-ce182e256ea1)
