package io.toolforge.hello.io;

import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

/**
 * Does the heavy lifting for mapping URIs to byte streams.
 * 
 * @see InputStream
 * @see OutputStream
 */
/* default */ enum Scheme {
  FILE {
    @Override
    public InputStream getInputStream(URI uri) throws IOException {
      return Files.newInputStream(Paths.get(uri));
    }

    @Override
    public OutputStream getOutputStream(URI uri) throws IOException {
      return Files.newOutputStream(Paths.get(uri), StandardOpenOption.WRITE,
          StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
  },
  HTTP {
    @Override
    public InputStream getInputStream(URI uri) throws IOException {
      InputStream result = null;

      HttpURLConnection cn = (HttpURLConnection) uri.toURL().openConnection();
      try {
        cn.setRequestMethod("GET");
        cn.setDoInput(true);
        cn.setDoOutput(false);
        cn.connect();

        final InputStream input = cn.getInputStream();

        result = new FilterInputStream(input) {
          @Override
          public void close() throws IOException {
            try {
              super.close();
            } finally {
              cn.disconnect();
            }
          }
        };
      } finally {
        if (result == null)
          cn.disconnect();
      }

      return result;
    }

    @Override
    public OutputStream getOutputStream(URI uri) throws IOException {
      OutputStream result = null;

      HttpURLConnection cn = (HttpURLConnection) uri.toURL().openConnection();
      try {
        cn.setRequestMethod("PUT");
        cn.setDoInput(true);
        cn.setDoOutput(true);
        cn.connect();

        final OutputStream output = cn.getOutputStream();

        result = new FilterOutputStream(output) {
          @Override
          public void close() throws IOException {
            try {
              super.close();
            } finally {
              try {
                cn.getInputStream().close();
              } finally {
                cn.disconnect();
              }
            }
          }
        };
      } finally {
        if (result == null)
          cn.disconnect();
      }

      return result;
    }
  };

  private static final String FILE_SCHEME = "file";

  private static final String HTTP_SCHEME = "http";

  private static final String HTTPS_SCHEME = "https";

  public static Scheme fromUri(URI uri) {
    if (uri == null)
      throw new NullPointerException();
    return switch (Optional.ofNullable(uri.getScheme()).orElse(FILE_SCHEME).toLowerCase()) {
      case FILE_SCHEME -> Scheme.FILE;
      case HTTP_SCHEME, HTTPS_SCHEME -> Scheme.HTTP;
      default -> {
        throw new IllegalArgumentException("unrecognized scheme " + uri);
      }
    };
  }

  public abstract InputStream getInputStream(URI uri) throws IOException;

  public abstract OutputStream getOutputStream(URI uri) throws IOException;
}
