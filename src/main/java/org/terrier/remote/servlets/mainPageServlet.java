package org.terrier.remote.servlets;

import org.terrier.remote.impl.ImportedIndexes;
import org.terrier.remote.model.IndexStats;
import org.terrier.structures.CollectionStatistics;
import org.terrier.structures.Index;
import org.terrier.remote.impl.IndexApiServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class mainPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // populate the indexes list with the names and stats of imported indexes
        HashMap<String, Index> i = ImportedIndexes.getIndexes();
        Map<String, List<String>> indexInfo = new HashMap<>();
        for(String key : i.keySet()){
            try{
                CollectionStatistics allStats = i.get(key).getCollectionStatistics();
                LinkedList<String> statsToDisplay= new LinkedList<>();

                //add required stats to the list
                statsToDisplay.add("Number of documents: " + Integer.toString(allStats.getNumberOfDocuments()));

                indexInfo.put(key,statsToDisplay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //when a get request is issued, set the indexes attribute to contain a map of imported indexes
        req.setAttribute("indexInfo", indexInfo);
        req.getRequestDispatcher("/interface/main.jsp").forward(req,resp);
    }
}