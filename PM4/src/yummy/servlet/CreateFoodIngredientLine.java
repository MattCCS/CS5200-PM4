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


@WebServlet("/createFoodIngredientLine")
public class CreateFoodIngredientLine extends HttpServlet {

    protected FoodIngredientLineDao foodIngredientLineDao;

    @Override
    public void init() throws ServletException {
    	foodIngredientLineDao = FoodIngredientLineDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<FoodIngredientLine> results = new ArrayList<FoodIngredientLine>();

        String id = req.getParameter("id");
        String foodId = req.getParameter("foodId");
        String ingredientLineId = req.getParameter("ingredientLineId");

        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else if (foodId == null || foodId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid foodId.");
        } else if (ingredientLineId == null || ingredientLineId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ingredientLineId.");
        
        } else {
            try {
            	FoodIngredientLine foodIngredientLine = new FoodIngredientLine(Integer.parseInt(id), Integer.parseInt(foodId), ingredientLineId);
                results.add(foodIngredientLineDao.create(foodIngredientLine));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "success" );
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/CreateFoodIngredientLine.jsp").forward(req, resp);
    }
}
