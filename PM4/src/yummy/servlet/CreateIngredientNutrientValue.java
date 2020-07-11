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


@WebServlet("/createIngredientNutrientValue")
public class CreateIngredientNutrientValue extends HttpServlet {

    protected IngredientNutrientValueDao ingredientNutrientValueDao;

    @Override
    public void init() throws ServletException {
    	ingredientNutrientValueDao = IngredientNutrientValueDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<IngredientNutrientValue> results = new ArrayList<IngredientNutrientValue>();

        String id = req.getParameter("id");
        String ingredientId = req.getParameter("ingredientId");
        String nutrientCodeId = req.getParameter("nutrientCodeId");
        String nutrientValue = req.getParameter("nutrientValue");
        String nutrientValueUnit = req.getParameter("nutrientValueUnit");

        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else if (ingredientId == null || ingredientId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ingredientId.");
        } else if (nutrientCodeId == null || nutrientCodeId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid nutrientCodeId.");
        } else if (nutrientValue == null || nutrientValue.trim().isEmpty()) {
            messages.put("success", "Please enter a valid nutrientValue.");
        } else if (nutrientValueUnit == null || nutrientValueUnit.trim().isEmpty()) {
            messages.put("success", "Please enter a valid nutrientValueUnit.");
        } else {
            try {
            	IngredientNutrientValue ingredientNutrientValue = new IngredientNutrientValue(Integer.parseInt(id),Integer.parseInt(ingredientId),Integer.parseInt(nutrientCodeId),Integer.parseInt(nutrientValue),nutrientValueUnit);
                results.add(ingredientNutrientValueDao.create(ingredientNutrientValue));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "success" );
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/CreateIngredientNutrientValue.jsp").forward(req, resp);
    }
}
