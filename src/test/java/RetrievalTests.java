import org.junit.Test;
import org.terrier.remote.api.impl.IndexApiServiceImpl;
import org.terrier.remote.model.RemoteIndex;
import org.terrier.remote.model.RemoteResultSet;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RetrievalTests {

    @Test
    public void testRetrieveEntity() throws Exception{
        IndexApiServiceImpl api = new IndexApiServiceImpl();

        RemoteIndex index = new RemoteIndex();
        index.setPath("C:/Users/Benas/work/terrier-core/var/index");
        index.setName("data");

        api.importIndex(index, null);
        ArrayList<String> a = new ArrayList<>();
        a.add("trec.model");

        ArrayList<String> b = new ArrayList<>();
        b.add("BM25");

        Response response = api.retrieve( "data","SYSTEMS OF DATA CODING FOR INFORMATION TRANSFER", "1", a, b, null);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertTrue(((RemoteResultSet) response.getEntity()).getDocIds().size() > 0);
        assertTrue(((RemoteResultSet) response.getEntity()).getScores().size() > 0);
    }

}