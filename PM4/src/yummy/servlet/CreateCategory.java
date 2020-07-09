package yummy.servlet;

import yummy.dal.*;
import yummy.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/createCategory")
public class CreateCategory extends HttpServlet {

    protected CategoryDao categoryDao;

    @Override
    public void init() throws ServletException {
        categoryDao = CategoryDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Category> results = new ArrayList<Category>();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
            try {
                Category category = new Category(Integer.parseInt(id), name);
                results.add(categoryDao.create(category));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Successfully created " + name);
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/CreateCategory.jsp").forward(req, resp);
    }
}
