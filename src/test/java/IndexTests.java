import org.junit.Test;
import org.terrier.remote.api.impl.IndexApiServiceImpl;
import org.terrier.remote.model.IndexStats;
import org.terrier.remote.model.RemoteIndex;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IndexTests {

    @Test
    public void testImportIndex() throws Exception{

        IndexApiServiceImpl api = new IndexApiServiceImpl();

        RemoteIndex index = new RemoteIndex();
        index.setPath("C:/Users/Benas/work/terrier-core/var/index");
        index.setName("data");

        Response response = api.importIndex(index, null);

        assertEquals( 200, response.getStatus() );
        assertEquals( "data", response.getEntity());
    }

    @Test
    public void testStats() throws Exception {
        IndexApiServiceImpl api = new IndexApiServiceImpl();

        RemoteIndex index = new RemoteIndex();
        index.setPath("C:/Users/Benas/work/terrier-core/var/index");
        index.setName("data");
        api.importIndex(index, null);

        Response response = api.stats("data", null);

        assertEquals(200, response.getStatus());
        assertTrue( ((IndexStats) response.getEntity()).getNumberOfDocuments() > 0);
    }
}
