package com;

import com.cruiseReservation.model.RouteDAO;
import com.cruiseReservation.model.RouteVO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/ShipsScheduleServlet")
public class ShipsScheduleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ShipsScheduleDAO shipsScheduleDAO;
    private RouteDAO routeDAO;

    public void init() {
        shipsScheduleDAO = new ShipsScheduleDAO();
        routeDAO = new RouteDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null)
            action = "list";

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertShipsSchedule(request, response);
                    break;
                case "delete":
                    deleteShipsSchedule(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateShipsSchedule(request, response);
                    break;
                default:
                    listShipsSchedule(request, response);
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

    private void listShipsSchedule(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<ShipsScheduleVO> listShipsSchedules = shipsScheduleDAO.selectAllShipsSchedules();
        request.setAttribute("listShipsSchedules", listShipsSchedules);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/shipsSchedule.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<RouteVO> listRoutes = routeDAO.selectAllRoutes();
        request.setAttribute("listRoutes", listRoutes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/shipsScheduleForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int shipId = Integer.parseInt(request.getParameter("id"));
        ShipsScheduleVO existingShipSchedule = shipsScheduleDAO.selectShipsSchedule(shipId);
        List<RouteVO> listRoutes = routeDAO.selectAllRoutes();
        request.setAttribute("shipsSchedule", existingShipSchedule);
        request.setAttribute("listRoutes", listRoutes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/shipsScheduleForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertShipsSchedule(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int routeId = Integer.parseInt(request.getParameter("routeId"));
        String shipStatus = request.getParameter("shipStatus");
        String shipShippingTimeStr = request.getParameter("shipShippingTime");
        String shipShippingDock = request.getParameter("shipShippingDock");
        int shipRoomsBooked = Integer.parseInt(request.getParameter("shipRoomsBooked"));

        // Parse shipShippingTimeStr to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime shipShippingTime = LocalDateTime.parse(shipShippingTimeStr, formatter);

        // Get RouteVO object
        RouteVO route = routeDAO.selectRoute(routeId);

        ShipsScheduleVO newShipSchedule = new ShipsScheduleVO(route, shipStatus, shipShippingTime, shipShippingDock, shipRoomsBooked);
        shipsScheduleDAO.insertShipsSchedule(newShipSchedule);
        response.sendRedirect("ShipsScheduleServlet?action=list");
    }

    private void updateShipsSchedule(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int shipId = Integer.parseInt(request.getParameter("shipId"));
        int routeId = Integer.parseInt(request.getParameter("routeId"));
        String shipStatus = request.getParameter("shipStatus");
        String shipShippingTimeStr = request.getParameter("shipShippingTime");
        String shipShippingDock = request.getParameter("shipShippingDock");
        int shipRoomsBooked = Integer.parseInt(request.getParameter("shipRoomsBooked"));

        // Parse shipShippingTimeStr to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime shipShippingTime = LocalDateTime.parse(shipShippingTimeStr, formatter);

        // Get RouteVO object
        RouteVO route = routeDAO.selectRoute(routeId);

        ShipsScheduleVO shipSchedule = shipsScheduleDAO.selectShipsSchedule(shipId);
        shipSchedule.setRoute(route);
        shipSchedule.setShipStatus(shipStatus);
        shipSchedule.setShipShippingTime(shipShippingTime);
        shipSchedule.setShipShippingDock(shipShippingDock);
        shipSchedule.setShipRoomsBooked(shipRoomsBooked);

        shipsScheduleDAO.updateShipsSchedule(shipSchedule);
        response.sendRedirect("ShipsScheduleServlet?action=list");
    }

    private void deleteShipsSchedule(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int shipId = Integer.parseInt(request.getParameter("id"));
        shipsScheduleDAO.deleteShipsSchedule(shipId);
        response.sendRedirect("ShipsScheduleServlet?action=list");
    }
}
