package io.toolforge.tool.hello;

import static java.lang.String.format;
import java.io.BufferedReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import com.sigpwned.discourse.core.util.Discourse;

/**
 * This is our main tool class. It is set as the program entry point in our POM, and is where our
 * tool execution starts.
 */
public class App {
  /**
   * The actual program entry point. We just parse the arguments and delegate.
   */
  public static void main(String[] args) throws Exception {
    main(Discourse.configuration(Configuration.class, args).validate());
  }

  /**
   * The practical program entry point. We take our parsed program arguments and do our work. The
   * log statements are purely for our users' information, and are not required.
   */
  public static void main(Configuration configuration) throws Exception {
    // It's polite to say hello!
    System.out.println("Hello!");

    // Read all the names out of our input
    List<String> names;
    try (BufferedReader input =
        new BufferedReader(configuration.names.getReader(StandardCharsets.UTF_8))) {
      names = input.lines().toList();
    }

    // Tell the user we understood their input
    String greeting = Optional.ofNullable(configuration.greeting).orElse("Hi");
    System.out.println("I will now greet " + names.size() + " names with `" + greeting + "'.");

    // Write our program output
    try (Writer output = configuration.salutationsTxt.getWriter(StandardCharsets.UTF_8)) {
      for (String name : names) {
        output.write(salutation(greeting, name));
        output.write("\n");
      }
    }

    // It's polite to say goodbye!
    System.out.println("Goodbye!");
  }

  public static String salutation(String greeting, String name) {
    return format("%s, %s!", greeting, name);
  }
}
