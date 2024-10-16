package com;

import com.cruiseReservation.model.RouteDAO;
import com.cruiseReservation.model.RouteVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/RouteServlet")
public class RouteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RouteDAO routeDAO;

    public void init() {
        routeDAO = new RouteDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null)
            action = "list";

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertRoute(request, response);
                    break;
                case "delete":
                    deleteRoute(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateRoute(request, response);
                    break;
                default:
                    listRoute(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listRoute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<RouteVO> listRoute = routeDAO.selectAllRoutes();
        request.setAttribute("listRoutes", listRoute);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/routes.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/addRoute.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int routeId = Integer.parseInt(request.getParameter("id"));
        RouteVO existingRoute = routeDAO.selectRoute(routeId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/editRoute.jsp");
        request.setAttribute("route", existingRoute);
        dispatcher.forward(request, response);
    }

    private void insertRoute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String routeName = request.getParameter("routeName");
        String routeDepiction = request.getParameter("routeDepiction");
        int routeDays = Integer.parseInt(request.getParameter("routeDays"));

        RouteVO newRoute = new RouteVO(routeName, routeDepiction, routeDays);
        routeDAO.insertRoute(newRoute);
        
        
        response.sendRedirect(request.getContextPath() + "/RouteServlet?action=list");
    }

    private void updateRoute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int routeId = Integer.parseInt(request.getParameter("routeId"));
        String routeName = request.getParameter("routeName");
        String routeDepiction = request.getParameter("routeDepiction");
        int routeDays = Integer.parseInt(request.getParameter("routeDays"));

        RouteVO route = new RouteVO(routeId, routeName, routeDepiction, routeDays);
        routeDAO.updateRoute(route);
        response.sendRedirect("RouteServlet?action=list");
    }

    private void deleteRoute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int routeId = Integer.parseInt(request.getParameter("id"));
        routeDAO.deleteRoute(routeId);
        response.sendRedirect("RouteServlet?action=list");
    }
}
