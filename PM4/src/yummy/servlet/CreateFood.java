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


@WebServlet("/createFood")
public class CreateFood extends HttpServlet {

    protected FoodDao foodDao;

    @Override
    public void init() throws ServletException {
    	foodDao = FoodDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Food> results = new ArrayList<Food>();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String categoryId = req.getParameter("categoryId");

        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid id.");
        } else if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
            try {
                Integer categoryIdInt = null;
                if (categoryId != null && !categoryId.trim().isEmpty()) {
                    categoryIdInt = Integer.parseInt(categoryId);
                }
            	Food food = new Food(Integer.parseInt(id), name, categoryIdInt);
                results.add(foodDao.create(food));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "success" );
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/CreateFood.jsp").forward(req, resp);
    }
}
