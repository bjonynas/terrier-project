package org.terrier.remote.servlets;

import org.terrier.remote.impl.IndexApiServiceImpl;
import org.terrier.remote.model.IndexStats;
import org.terrier.remote.model.RemoteResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.LinkedList;

public class statsServlet extends HttpServlet{

    IndexApiServiceImpl api;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        api = new IndexApiServiceImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get the name of the index by removing the leading / from the path
        String indexName = req.getPathInfo().substring(1);
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

        req.setAttribute("searched", false);
        req.setAttribute("indexName", indexName);
        req.setAttribute("indexStats", indexStats);
        req.getRequestDispatcher("/interface/stats.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String indexName = req.getPathInfo().substring(1);
        String queryString = req.getParameter("queryString");
        String queryId = req.getParameter("queryId");
        Response response = null;

        try{
            response = api.retrieve(indexName, queryString, queryId, "", "", new LinkedList<String>(),new LinkedList<String>(), null);
        } catch (Exception e){
            e.printStackTrace();
        }

        String results = response.getEntity().toString();
        System.out.println(results);

        req.setAttribute("indexName", indexName);
        req.setAttribute("queryString", queryString);
        req.setAttribute("searched", true);
        req.setAttribute("results", results);
        req.getRequestDispatcher("/interface/stats.jsp").forward(req,resp);
    }
}
