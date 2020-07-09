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


@WebServlet("/createNutrient")
public class CreateNutrient extends HttpServlet {

    protected NutrientDao nutrientDao;

    @Override
    public void init() throws ServletException {
        nutrientDao = NutrientDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Nutrient> results = new ArrayList<Nutrient>();

        String nutrientCodeId = req.getParameter("nutrientCodeId");
        String name = req.getParameter("name");
        if (nutrientCodeId == null || nutrientCodeId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid nutrientCodeId.");
        } else if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
            try {
                Nutrient nutrient = new Nutrient(Integer.parseInt(nutrientCodeId), name);
                results.add(nutrientDao.create(nutrient));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Successfully created " + name);
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/CreateNutrient.jsp").forward(req, resp);
    }
}
