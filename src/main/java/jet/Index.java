package jet;

import java.io.IOException;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rs.RsVelocity;

final class Index implements Take {
    private final Document document;
    Index(final Document doc) {
        this.document = doc;
    }
    @Override
    public Response act(final Request req) throws IOException {
        return new RsVelocity(
            Index.class.getResourceAsStream("index.html.vm"),
            new RsVelocity.Pair(
                "text",
                this.document
            )
        );
    }
}
