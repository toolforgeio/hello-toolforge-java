package io.toolforge.hello.io;

import static java.util.Objects.requireNonNull;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.Charset;

public class OutputSink {
  public static InputSource fromString(String s) {
    return new InputSource(URI.create(s));
  }

  private final URI uri;

  public OutputSink(URI uri) {
    this.uri = requireNonNull(uri);
  }

  public OutputStream getOutputStream() throws IOException {
    return getScheme().getOutputStream(getUri());
  }

  public Writer getWriter(Charset charset) throws IOException {
    return new OutputStreamWriter(getOutputStream(), charset);
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
