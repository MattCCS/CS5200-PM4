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


@WebServlet("/readFoodGroup")
public class ReadFoodGroup extends HttpServlet {

	protected FoodGroupDao foodGroupDao;

	@Override
	public void init() throws ServletException {
		foodGroupDao = FoodGroupDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<FoodGroup> results = new ArrayList<FoodGroup>();

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Boolean noId = (id == null || id.trim().isEmpty());
        Boolean noName = (name == null || name.trim().isEmpty());

        if (noId && noName) {
            messages.put("success", "Please enter a valid id or name.");
        } else {
        	try {
            	if (noId) {
                    results.add(foodGroupDao.getByName(name));
                } else {
                    results.add(foodGroupDao.getById(GenericServlet.intOrNull(id)));
                }
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + (noId ? name : id));
        }
        req.setAttribute("results", results);

        req.getRequestDispatcher("/ReadFoodGroup.jsp").forward(req, resp);
	}
}
