package restaurant.servlet;

import restaurant.dal.*;
import restaurant.model.*;

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
 * FindRecipe is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findrecipe
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/PM4/findrecipe.
 */
@WebServlet("/findrecipe")
public class FindRecipe extends HttpServlet {

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

        List<Recipe> recipes = new ArrayList<Recipe>();

        String name = req.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "[GET] Please enter a valid name.");
        } else {
        	try {
        		recipes.add(recipeDao.getRecipeByName(name));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + name);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindRecipe.jsp.
        	messages.put("previousFirstName", name);
        }
        req.setAttribute("recipes", recipes);

        req.getRequestDispatcher("/FindRecipe.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Recipe> recipes = new ArrayList<Recipe>();

        String name = req.getParameter("name");
        System.out.println(name);
        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "[POST] Please enter a valid name.");
        } else {
        	try {
            	recipes.add(recipeDao.getRecipeByName(name));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + name);
        }
        req.setAttribute("recipes", recipes);

        req.getRequestDispatcher("/FindRecipe.jsp").forward(req, resp);
    }
}
