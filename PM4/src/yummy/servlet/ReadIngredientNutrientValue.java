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


@WebServlet("/readIngredientNutrientValue")
public class ReadIngredientNutrientValue extends HttpServlet {

	protected IngredientNutrientValueDao ingredientNutrientValueDao;

	@Override
	public void init() throws ServletException {
		ingredientNutrientValueDao = IngredientNutrientValueDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<IngredientNutrientValue> results = new ArrayList<IngredientNutrientValue>();

        String id = req.getParameter("id");

        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else {
        	try {
        		results.add(ingredientNutrientValueDao.getById(Integer.parseInt(id)));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + id);
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/ReadIngredientNutrientValue.jsp").forward(req, resp);
	}
}
