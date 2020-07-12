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
}
