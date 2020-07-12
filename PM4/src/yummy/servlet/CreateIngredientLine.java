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


@WebServlet("/createIngredientLine")
public class CreateIngredientLine extends HttpServlet {

    protected IngredientLineDao ingredientLineDao;

    @Override
    public void init() throws ServletException {
    	ingredientLineDao = IngredientLineDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<IngredientLine> results = new ArrayList<IngredientLine>();

        String id = req.getParameter("id");
        String amountNumerator = req.getParameter("amountNumerator");
        String amountDenominator = req.getParameter("amountDenominator");
        String unitOfMeasurement = req.getParameter("unitOfMeasurement");
        String foodId = req.getParameter("foodId");
        String ingredientId = req.getParameter("ingredientId");
        String description = req.getParameter("description");

        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else if (amountNumerator == null || amountNumerator.trim().isEmpty()) {
            messages.put("success", "Please enter a valid amountNumerator.");
        } else if (amountDenominator == null || amountDenominator.trim().isEmpty()) {
            messages.put("success", "Please enter a valid amountDenominator.");
        } else if ((foodId == null || foodId.trim().isEmpty()) && (ingredientId == null || ingredientId.trim().isEmpty())) {
            messages.put("success", "Please enter a valid foodId or ingredientId.");
        } else {
            try {
                Integer foodIdInt = null;
                if (foodId != null && !foodId.trim().isEmpty()) {
                	foodIdInt = Integer.parseInt(foodId);
                }
                Integer ingredientIdInt = null;
                if (ingredientId != null && !ingredientId.trim().isEmpty()) {
                    ingredientIdInt = Integer.parseInt(ingredientId);
                }
            	IngredientLine ingredientLine = new IngredientLine(id, Integer.parseInt(amountNumerator), Integer.parseInt(amountDenominator), unitOfMeasurement, foodIdInt, ingredientIdInt, description);
                results.add(ingredientLineDao.create(ingredientLine));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "success" );
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/CreateIngredientLine.jsp").forward(req, resp);
    }
}