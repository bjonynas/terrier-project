package org.terrier.remote.servlets;

import org.terrier.remote.impl.IndexApiServiceImpl;
import org.terrier.remote.model.RemoteIndex;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImportServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IndexApiServiceImpl api = new IndexApiServiceImpl();RemoteIndex index = new RemoteIndex();
        index.setPath(req.getParameter("indexPath"));
        index.setPrefix(req.getParameter("indexPrefix"));
        index.setIndexName(req.getParameter("indexName"));
        try{
            api.importIndex(index, null);
        } catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("/main");
    }
}
