package ua.training.servlets;

import ua.training.db.NoteBook;
import ua.training.model.NotUniqueValueException;
import ua.training.model.Record;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ua.training.servlets.RegexContainer.*;

public class RegistrationServlet extends HttpServlet {

    private NoteBook records;
    private List<String> errorMessages;
    private String form = "/WEB-INF/view/form.jsp";
    private Record record;

    @Override
    public void init() throws ServletException {
        records = NoteBook.getInstance();
        record = new Record();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("record", record);
        req.setAttribute("errors", errorMessages);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(form);
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> data = new ConcurrentHashMap<>();

        data.put("login", req.getParameter("login"));
        data.put("firstName", req.getParameter("firstName"));
        data.put("email", req.getParameter("email"));
        errorMessages = new ArrayList<>();

        record.setAll(data);

        if (isDataCorrect(data, errorMessages) && isDataUnique(data, errorMessages)){//double check
            try{
                records.addRecord(record);
            }
            catch (NotUniqueValueException ex){
                errorMessages.add("Sorry! Something went wrong. Please, resend your data.");
                doGet(req, resp);
            }
            record = new Record();
        }

        doGet(req, resp);
    }

    private boolean isDataCorrect(Map<String, String> data, List<String> errorMessages) {
        boolean isCorrect = true;

        if (!data.get("login").matches(LOGIN_REGEX)){
            errorMessages.add(String.format("Login %s is out of format!", data.get("login")));
            isCorrect = false;
        }

        if (!data.get("firstName").matches(FIRST_NAME_REGEX)){
            errorMessages.add(String.format("First name %s is out of format!", data.get("firstName")));
            isCorrect = false;
        }

        if (!data.get("email").matches(EMAIL_REGEX)){
            errorMessages.add(String.format("Email %s is out of format!", data.get("email")));
            isCorrect = false;
        }

        return isCorrect;
    }

    private boolean isDataUnique(Map<String, String> data, List<String> errorMessages){
        boolean isUnique = true;

        if (!records.isLoginUnique(data.get("login"))) {
            errorMessages.add(String.format("Sorry! Login %s is already taken!", data.get("login")));
            isUnique = false;
        }

        if (!records.isEmailUnique(data.get("email"))) {
            errorMessages.add(String.format("Sorry! Email %s is already taken!", data.get("email")));
            isUnique = false;
        }

        return isUnique;
    }


}
