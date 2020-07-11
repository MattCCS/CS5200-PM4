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


@WebServlet("/createIngredient")
public class CreateRecipeIngredientLine extends HttpServlet {

    protected RecipeIngredientLineDao recipeIngredientLineDao;

    @Override
    public void init() throws ServletException {
    	recipeIngredientLineDao = RecipeIngredientLineDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<RecipeIngredientLine> results = new ArrayList<RecipeIngredientLine>();

        String id = req.getParameter("id");
        String recipeId = req.getParameter("recipeId");
        String ingredientLineId = req.getParameter("ingredientLineId");

        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else if (recipeId == null || recipeId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid recipeId.");
        } else if (ingredientLineId == null || ingredientLineId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ingredientLineId.");
        }
        
        else {
            try {
            	RecipeIngredientLine recipeIngredientLine = new RecipeIngredientLine(Integer.parseInt(id), Integer.parseInt(recipeId),ingredientLineId);
                results.add(recipeIngredientLineDao.create(recipeIngredientLine));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "success");
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/CreateIngredient.jsp").forward(req, resp);
    }
}
