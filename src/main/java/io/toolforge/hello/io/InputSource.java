package io.toolforge.hello.io;

import static java.util.Objects.requireNonNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.Charset;
import com.sigpwned.chardet4j.Chardet;

public class InputSource {
  public static InputSource fromString(String s) {
    return new InputSource(URI.create(s));
  }

  private final URI uri;

  public InputSource(URI uri) {
    this.uri = requireNonNull(uri);
  }

  public InputStream getInputStream() throws IOException {
    return getScheme().getInputStream(uri);
  }

  public Reader getReader(Charset defaultCharset) throws IOException {
    return Chardet.decode(getInputStream(), defaultCharset);
  }

  /**
   * @return the uri
   */
  private URI getUri() {
    return uri;
  }

  private Scheme getScheme() throws IOException {
    Scheme result;
    try {
      result = Scheme.fromUri(getUri());
    } catch (IllegalArgumentException e) {
      throw new IOException("unrecognized scheme " + getUri());
    }
    return result;
  }
}
