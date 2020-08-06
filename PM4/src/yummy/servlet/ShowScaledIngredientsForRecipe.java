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


@WebServlet("/showScaledIngredientsForRecipe")
public class ShowScaledIngredientsForRecipe extends HttpServlet {

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
        int scaleUp = 1;
        int scaleDown = 1;

        try {
        	scaleUp = Integer.parseInt(req.getParameter("scaleUp"));
        } catch (Exception e) {
        	scaleUp = 1;
        }
        
        try {
        	scaleDown = Integer.parseInt(req.getParameter("scaleDown"));
        } catch (Exception e) {
        	scaleDown = 1;
        }

        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		results = customQueriesDao.showScaledIngredientsForRecipe(name, scaleUp, scaleDown);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + name);
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/ShowScaledIngredientsForRecipe.jsp").forward(req, resp);
	}
}
