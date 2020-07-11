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
 * ReadIngredientLine is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /readingredientLineDao
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/PM4/readingredientLineDao.
 */
@WebServlet("/readIngredientLine")
public class ReadIngredientLine extends HttpServlet {

	protected IngredientLineDao ingredientLineDao;

	@Override
	public void init() throws ServletException {
		ingredientLineDao = IngredientLineDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<IngredientLine> results = new ArrayList<IngredientLine>();

        String id = req.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else {
        	try {
        		results.add(ingredientLineDao.getById(id));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + id);
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/ReadIngredientLine.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<IngredientLine> results = new ArrayList<IngredientLine>();

        String id = req.getParameter("id");
        System.out.println(id);
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "[POST] Please enter a valid id.");
        } else {
            try {
                results.add(ingredientLineDao.getById(id));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + id);
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/ReadIngredientLine.jsp").forward(req, resp);
    }
}
