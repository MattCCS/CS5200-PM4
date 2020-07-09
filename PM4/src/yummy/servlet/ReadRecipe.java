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


/**
 * ReadRecipe is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /readrecipe
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/PM4/readrecipe.
 */
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
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering ReadRecipe.jsp.
        	messages.put("previousFirstName", name);
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/ReadRecipe.jsp").forward(req, resp);
	}
}
