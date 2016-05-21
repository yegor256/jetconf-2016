package jet;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.takes.Response;
import org.takes.Take;
import org.takes.rq.RqFake;
import org.takes.rs.RsPrint;

public final class IndexTest {

    @Test
    public void rendersHtml() throws Exception {
        final Document document = new Document.InFile();
        final String content = "hey, you!";
        document.update(content);
        final Take index = new Index(document);
        final Response response = index.act(new RqFake());
        final String body = new RsPrint(response).printBody();
        MatcherAssert.assertThat(
            body,
            CoreMatchers.containsString(content)
        );
    }

}
