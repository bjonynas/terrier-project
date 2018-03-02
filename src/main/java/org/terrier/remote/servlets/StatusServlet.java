package org.terrier.remote.servlets;

import org.terrier.remote.impl.StatusApiServiceImpl;
import org.terrier.remote.model.ServerStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatusServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatusApiServiceImpl api = new StatusApiServiceImpl();

        try {
            ServerStatus status = ((ServerStatus) (api.status(null).getEntity()));
            String availableMemory = status.getAvailableMemory();
            String usedMemory = status.getUsedMemory();
            String importedIndexes = status.getNumberOfIndexes();

            req.setAttribute("availableMemory", availableMemory);
            req.setAttribute("usedMemory", usedMemory);
            req.setAttribute("importedIndexes", importedIndexes);
            req.getRequestDispatcher("/interface/status.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
