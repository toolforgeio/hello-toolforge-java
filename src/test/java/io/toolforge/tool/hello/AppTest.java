/*-
 * =================================LICENSE_START==================================
 * toolforge-ngrams-tool
 * ====================================SECTION=====================================
 * Copyright (C) 2022 - 2023 ToolForge
 * ====================================SECTION=====================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==================================LICENSE_END===================================
 */
package io.toolforge.tool.hello;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.Test;
import com.google.common.io.Files;
import io.toolforge.toolforge4j.io.InputSource;
import io.toolforge.toolforge4j.io.OutputSink;

public class AppTest {
  @Test
  public void smokeTest() throws Exception {
    List<String> names = List.of("Frodo", "Bilbo", "Aragorn");

    File namesTxt = File.createTempFile("names.", ".txt");
    namesTxt.deleteOnExit();
    Files.asCharSink(namesTxt, StandardCharsets.UTF_8).write(String.join("\n", names));

    File salutationsTxt = File.createTempFile("salutations.", ".txt");
    salutationsTxt.deleteOnExit();

    Configuration configuration = new Configuration();
    configuration.greeting = "Hi";
    configuration.names = new InputSource(namesTxt.toURI());
    configuration.salutationsTxt = new OutputSink(salutationsTxt.toURI());

    App.main(configuration);

    List<String> lines = Files.readLines(salutationsTxt, StandardCharsets.UTF_8);

    assertThat(lines,
        is(names.stream().map(name -> App.salutation(configuration.greeting, name)).toList()));
  }
}
