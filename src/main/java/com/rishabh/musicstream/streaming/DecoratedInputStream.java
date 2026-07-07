package com.rishabh.musicstream.streaming;

import java.io.IOException;
import java.io.InputStream;

/**
 * A decorator over an {@link InputStream} that limits the number of bytes
 * visible to the consumer.
 *
 * <p>This class wraps another InputStream and behaves exactly like the
 * original stream until the configured byte limit is reached. Once the
 * specified number of bytes has been read, it behaves as if the end of
 * the stream has been reached by returning {@code -1} from all subsequent
 * read operations.</p>
 *
 * <p>The underlying InputStream may still contain additional data, but
 * that data is intentionally hidden from the consumer.</p>
 *
 * <h2>Why does this class exist?</h2>
 *
 * <p>HTTP Range requests allow a client (such as a web browser) to request
 * only a portion of a file, for example:</p>
 *
 * <pre>
 * Range: bytes=1000-1999
 * </pre>
 *
 * <p>Tomcat streams data by repeatedly invoking {@code read()} or
 * {@code read(byte[], int, int)} until the stream reports EOF
 * (returns {@code -1}). A normal {@code FileInputStream} continues reading
 * until the physical end of the file, which would send more bytes than
 * requested.</p>
 *
 * <p>By decorating the original InputStream with this class, the server
 * can expose only the requested byte range while leaving the underlying
 * stream unchanged.</p>
 *
 * <p>This is an example of the <b>Decorator Design Pattern</b>, where
 * additional behavior is added to an existing object without modifying
 * its implementation.</p>
 *
 *
 * @author <pre>Rishabh Tiwari</pre>
 */
public class DecoratedInputStream extends InputStream {

    private final InputStream delegate;
    private long remaining;

    public DecoratedInputStream(InputStream delegate, long requiredBytes) {
        this.delegate = delegate;
        this.remaining = requiredBytes;
    }

    @Override
    public int read() throws IOException {

        if (remaining <= 0) {
            return -1;
        }

        int data = delegate.read();

        if (data != -1) {
            remaining--;
        }

        return data;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {

        if (remaining <= 0) {
            return -1;
        }

        int bytesToRead = (int) Math.min(len, remaining);

        int bytesRead = delegate.read(b, off, bytesToRead);

        if (bytesRead != -1) {
            remaining -= bytesRead;
        }

        return bytesRead;
    }

    @Override
    public long skip(long n) throws IOException {

        long bytesToSkip = Math.min(n, remaining);

        long skipped = delegate.skip(bytesToSkip);

        remaining -= skipped;

        return skipped;
    }

    @Override
    public int available() throws IOException {
        return (int) Math.min(delegate.available(), remaining);
    }

    @Override
    public void close() throws IOException {
        delegate.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        delegate.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        delegate.reset();
    }

    @Override
    public boolean markSupported() {
        return delegate.markSupported();
    }
}