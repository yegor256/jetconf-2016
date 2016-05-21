package jet;

import java.io.IOException;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rq.RqForm;
import org.takes.rs.RsText;

final class Save implements Take {
    private final Document document;
    Save(final Document doc) {
        this.document = doc;
    }
    @Override
    public Response act(final Request req) throws IOException {
        final String text = new RqForm.Base(req).param("text").iterator().next();
        this.document.update(text);
        return new RsText(
            String.format("thanks, %d chars were saved", text.length())
        );
    }
}
