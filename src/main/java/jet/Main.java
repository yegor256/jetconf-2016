package jet;

import java.io.IOException;
import org.takes.facets.fork.FkMethods;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Back;
import org.takes.http.BkBasic;
import org.takes.http.BkParallel;
import org.takes.http.BkSafe;
import org.takes.http.Exit;
import org.takes.http.FtBasic;
import org.takes.rs.RsJSON;
import org.takes.rs.RsText;

public final class Main {
    public static void main(final String... args) throws IOException {
        final Document document = new Document.InFile();
        final Back back = new BkParallel(
            new BkBasic(
                new TkFork(
                    new FkMethods(
                        "GET",
                        new TkFork(
                            new FkRegex("/", new Index(document)),
                            new FkRegex("/robots.txt", new RsJSON(new RsText("{}"))),
                            new FkRegex("/hello.html", "hello")
                        )
                    ),
                    new FkMethods("POST", new Save(document))
                )
            ),
            10
        );
        new FtBasic(
            new BkSafe(
                back
            ),
            8080
        ).start(Exit.NEVER);
    }
}
