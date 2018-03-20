package org.terrier.remote.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.terrier.remote.api.ApiResponseMessage;
import org.terrier.remote.impl.ImportedIndexes;
import org.terrier.remote.impl.IndexApiServiceImpl;
import org.terrier.remote.model.IndexStats;
import org.terrier.remote.model.Metadata;
import org.terrier.remote.model.RemoteResultSet;
import org.terrier.remote.model.ResultDocument;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StatsServlet extends HttpServlet{

    IndexApiServiceImpl api;
    String latestIndex;
    LinkedList<String> allIndexes;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        api = new IndexApiServiceImpl();
        latestIndex = "";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the name of the index by removing the leading / from the path
        String indexName = req.getPathInfo().substring(1);
        latestIndex = indexName;
        Response s = null;
        try{
            s = api.stats(indexName, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinkedList<String> indexStats = new LinkedList<>();
        indexStats.add("Number of documents: " + ((IndexStats) (s.getEntity())).getNumberOfDocuments());
        indexStats.add("Number of terms: " + ((IndexStats) (s.getEntity())).getNumberOfTerms());
        indexStats.add("Number of tokens: " + ((IndexStats) (s.getEntity())).getNumberOfTokens());
        indexStats.add("Number of pointers: " + ((IndexStats) (s.getEntity())).getNumberOfPointers());

        req.setAttribute("indexName", indexName);
        req.setAttribute("indexStats", indexStats);
        req.getRequestDispatcher("/interface/stats.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryString = req.getParameter("queryString");
        String queryId = req.getParameter("queryId");
        Response response = null;
        LinkedList<String> queryControlNames = new LinkedList<String>();
        LinkedList<String> queryControlValues = new LinkedList<String>();

        queryControlNames.add("queryExpansion");
        if(req.getParameter("queryExpansion").equals("yes"))
            queryControlValues.add("true");
        else
            queryControlValues.add("false");

        try{
            response = api.retrieve(latestIndex, queryString, queryId, "", "", queryControlNames, queryControlValues, null);
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            req.setAttribute("queryString", queryString);
            req.setAttribute("indexName", latestIndex);

            if(req.getParameter("displayType").equals("PlainText")) {
                RemoteResultSet results = ((RemoteResultSet) (response.getEntity()));

                List<ResultDocument> docs = results.getDocuments();
                StringBuilder resultString = new StringBuilder();
                for(int x=0; x<docs.size(); x++){
                    ResultDocument currentDoc = docs.get(x);
                    Metadata currentMeta = currentDoc.getMetadata();
                    resultString.append("Document ID: " + currentDoc.getDocId()+"\n");

                    //include up to 300 characters of the first 3 meta keys
                    for(int k=0; (k<currentMeta.getMetaKeys().size() && k<3) ; k++){
                        String item = currentMeta.getMetaItems().get(k);
                        if(item.length() > 300){
                            item = item.substring(0, 300);
                        }
                        resultString.append( item + "\n");
                    }
                }
                req.setAttribute("results", resultString.toString().trim());

            } else if( req.getParameter("displayType").equals("JSON")){
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getEntity());
                req.setAttribute("results", json);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
        req.getRequestDispatcher("/interface/results.jsp").forward(req,resp);

    }
}
