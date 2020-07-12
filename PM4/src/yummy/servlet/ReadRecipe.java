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


@WebServlet("/readRecipe")
public class ReadRecipe extends HttpServlet {

	protected RecipeDao recipeDao;

	@Override
	public void init() throws ServletException {
		recipeDao = RecipeDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Recipe> results = new ArrayList<Recipe>();

        String name = req.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		results.add(recipeDao.getByName(name));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + name);
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/ReadRecipe.jsp").forward(req, resp);
	}
}
