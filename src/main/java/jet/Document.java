package jet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

interface Document {
    String content() throws IOException;
    void update(String text) throws IOException;
    final class InFile implements Document {
        private final Path path;
        public InFile() throws IOException {
            this.path = Files.createTempFile("jet", ".txt");
            this.update("empty document");
        }
        @Override
        public String toString() {
            try {
                return this.content();
            } catch (final IOException ex) {
                throw new IllegalStateException(ex);
            }
        }
        @Override
        public String content() throws IOException {
            return new String(Files.readAllBytes(this.path));
        }
        @Override
        public void update(final String text) throws IOException {
            Files.write(this.path, text.getBytes());
        }
    }
}
