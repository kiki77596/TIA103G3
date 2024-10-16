package com;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/CruiseBookingServlet")
public class CruiseBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        StringBuilder jsonBuilder = new StringBuilder();
        String line;

        try (BufferedReader br = req.getReader()) {
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = jsonBuilder.toString();
        Gson gson = new Gson();
        com.cruiseReservation.model.RouteVO route = gson.fromJson(json, com.cruiseReservation.model.RouteVO.class);

        com.cruiseReservation.model.RouteDAO routeDAO = new com.cruiseReservation.model.RouteDAO();
        routeDAO.insert(route);
    }
}
