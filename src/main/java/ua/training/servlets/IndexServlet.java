package ua.training.servlets;

import ua.training.db.NoteBook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {

    private NoteBook records;
    private String index = "/WEB-INF/view/index.jsp";

    @Override
    public void init() throws ServletException {
        records = NoteBook.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("count", records.getCount());
        req.setAttribute("records", records.getRecords().values() );
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(index);
        requestDispatcher.forward(req, resp);
    }
}
