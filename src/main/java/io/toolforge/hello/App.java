package io.toolforge.hello;

import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.google.common.io.CharStreams;
import com.sigpwned.discourse.core.util.Discourse;

public class App {
  public static void main(String[] args) throws Exception {
    main(Discourse.configuration(Configuration2.class, args).validate());
  }

  public static void main(Configuration2 configuration) throws Exception {
    System.out.println("Hello!");

    List<String> names;
    try (Reader input = configuration.names.getReader(StandardCharsets.UTF_8)) {
      names = CharStreams.readLines(input);
    }

    System.out.println(
        "I will now greet " + names.size() + " names with `" + configuration.greeting + "'.");

    List<String> greetings =
        names.stream().map(name -> String.format("%s, %s!", configuration.greeting, name)).toList();

    try (Writer output = configuration.greetingsTxt.getWriter(StandardCharsets.UTF_8)) {
      output.write(String.join("\n", greetings));
    }

    System.out.println("All done!");
  }
}
