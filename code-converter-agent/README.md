# code-converter-agent

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

```
wsiqueir@work-localhost:~/projects/java-to-kotlin/code-converter-agent/target$ curl -s https://raw.githubusercontent.com/jesuino/java-ml-projects/master/utilities/classification-bot/src/main/java/org/fxapps/classification/bot/MapUtil.java
package org.fxapps.classification.bot;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MapUtil {
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
		return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}
}
wsiqueir@work-localhost:~/projects/java-to-kotlin/code-converter-agent/target$ ./code-converter https://raw.githubusercontent.com/jesuino/java-ml-projects/master/utilities/classification-bot/src/main/java/org/fxapps/classification/bot/MapUtil.java -l kotlin
2024-09-04 23:07:28,492 INFO  [org.fxa.cod.CodeConverterCommand] (main) Downloading file https://raw.githubusercontent.com/jesuino/java-ml-projects/master/utilities/classification-bot/src/main/java/org/fxapps/classification/bot/MapUtil.java
2024-09-04 23:07:28,518 INFO  [org.fxa.cod.CodeConverterCommand] (main) Converting file to kotlin...
```
Result: 
```kotlin
fun <K, V : Comparable<V>> sortByValue(map: Map<K, V>): Map<K, V> {
    return map.entries.sortedBy { it.value }.associate { it.key to it.value }
}
```


![image](https://github.com/user-attachments/assets/551efe47-a465-4671-8cb4-c3dc53d53cc1)
