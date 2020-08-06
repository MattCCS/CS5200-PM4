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


@WebServlet("/showWhatRecipesExcludeCategory")
public class ShowWhatRecipesExcludeCategory extends HttpServlet {

	protected CustomQueriesDao customQueriesDao;

	@Override
	public void init() throws ServletException {
		customQueriesDao = CustomQueriesDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<List<String>> results = new ArrayList<>();

        String name = req.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Please enter a valid categoryName.");
        } else {
        	try {
        		results = customQueriesDao.whatRecipesExcludeCategory(name);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + name);
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/ShowWhatRecipesExcludeCategory.jsp").forward(req, resp);
	}
}
