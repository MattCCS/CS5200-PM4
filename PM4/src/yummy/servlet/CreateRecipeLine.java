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


@WebServlet("/createRecipeLine")
public class CreateRecipeLine extends HttpServlet {

    protected RecipeLineDao recipeLineDao;

    @Override
    public void init() throws ServletException {
    	recipeLineDao = RecipeLineDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<RecipeLine> results = new ArrayList<RecipeLine>();

        String id = req.getParameter("id");
        String amountNumerator = req.getParameter("amountNumerator");
        String amountDenominator = req.getParameter("amountDenominator");
        String unitOfMeasurement = req.getParameter("unitOfMeasurement");
        String foodId = req.getParameter("foodId");
        String recipeId = req.getParameter("recipeId");
        String description = req.getParameter("description");

        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else if (amountNumerator == null || amountNumerator.trim().isEmpty()) {
            messages.put("success", "Please enter a valid amountNumerator.");
        } else if (amountDenominator == null || amountDenominator.trim().isEmpty()) {
            messages.put("success", "Please enter a valid amountDenominator.");
        } else if ((foodId == null || foodId.trim().isEmpty()) && (recipeId == null || recipeId.trim().isEmpty())) {
            messages.put("success", "Please enter a valid foodId or recipeId.");
        } else {
            try {
            	RecipeLine recipeLine = new RecipeLine(id, Integer.parseInt(amountNumerator), Integer.parseInt(amountDenominator), unitOfMeasurement, GenericServlet.intOrNull(foodId), GenericServlet.intOrNull(recipeId), description);
                results.add(recipeLineDao.create(recipeLine));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "success" );
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/CreateRecipeLine.jsp").forward(req, resp);
    }
}
