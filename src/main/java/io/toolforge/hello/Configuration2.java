package io.toolforge.hello;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.sigpwned.discourse.core.annotation.Configurable;
import com.sigpwned.discourse.core.annotation.OptionParameter;
import io.toolforge.hello.io.InputSource;
import io.toolforge.hello.io.OutputSink;

@Configurable
public final class Configuration2 {
  private static final LocalDate TODAY = LocalDate.now(ZoneOffset.UTC);

  @SuppressWarnings("serial")
  private static final Set<String> GREETING_ENUMERATION =
      Collections.unmodifiableSet(new HashSet<String>() {
        {
          add("Hi");
          add("Hello");
          add("Good morning");
          add("Good afternoon");
          add("Good evening");
        }
      });

  @OptionParameter(longName = "greeting", description = "How should the people be greeted?",
      required = true)
  public String greeting;

  @OptionParameter(longName = "names", required = true, description = "Who should be greeted?")
  public InputSource names;

  @OptionParameter(longName = "greetings.txt", required = true, description = "The greetings!")
  public OutputSink greetingsTxt;

  public Configuration2 validate() {
    if (!GREETING_ENUMERATION.contains(greeting)) {
      throw new IllegalArgumentException(
          "greeting must be one of: Hi, Hello, Good morning, Good afternoon, Good evening");
    }
    return this;
  }
}
