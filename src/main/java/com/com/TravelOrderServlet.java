package com;

import com.TravelOrderDAO;
import com.cruiseReservation.model.CouponTypeVO;
import com.cruiseReservation.model.MemberDataVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SuppressWarnings("unused")
@WebServlet("/TravelOrderServlet")
public class TravelOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private TravelOrderDAO travelOrderDAO;
    private com.cruiseReservation.model.MemberDataVO memberDataDAO;
    private ShipsScheduleDAO shipsScheduleDAO;
    private CouponTypeDAO couponTypeDAO;
    
    @Override
    public void init() throws ServletException {
        travelOrderDAO = new TravelOrderDAO();
        memberDataDAO = new com.cruiseReservation.model.MemberDataVO();
        shipsScheduleDAO = new ShipsScheduleDAO();
        couponTypeDAO = new CouponTypeDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) {
            action = "list";
        }
        
        try {
            switch(action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertTravelOrder(request, response);
                    break;
                case "delete":
                    deleteTravelOrder(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateTravelOrder(request, response);
                    break;
                default:
                    listTravelOrders(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    // 列出所有旅遊訂單
    private void listTravelOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TravelOrderVO> listTravelOrders = travelOrderDAO.getAllTravelOrders();
        request.setAttribute("listTravelOrders", listTravelOrders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/travelOrders.jsp");
        dispatcher.forward(request, response);
    }
    
    // 顯示新增旅遊訂單表單
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MemberDataVO> listMembers = memberDataDAO.getAllMembers();
        List<ShipsScheduleVO> listShips = shipsScheduleDAO.getAllShipsSchedules();
        List<CouponTypeVO> listCoupons = couponTypeDAO.getAllCoupons();
        
        request.setAttribute("listMembers", listMembers);
        request.setAttribute("listShips", listShips);
        request.setAttribute("listCoupons", listCoupons);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/travelOrderForm.jsp");
        dispatcher.forward(request, response);
    }
    
    // 插入新的旅遊訂單
    private void insertTravelOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int memberId = Integer.parseInt(request.getParameter("membId"));
            int shipId = Integer.parseInt(request.getParameter("shipId"));
            String couponIdStr = request.getParameter("coupId");
            Integer couponId = couponIdStr != null && !couponIdStr.isEmpty() ? Integer.parseInt(couponIdStr) : null;
            String orderStatus = request.getParameter("travOrdeStatus");
            int roomAmount = Integer.parseInt(request.getParameter("roomAmount"));
            double totalAmount = Double.parseDouble(request.getParameter("travOrdeAmount"));
            
            // 解析出發時間
            String shippingTimeStr = request.getParameter("shipShippingTime");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime shippingTime = LocalDateTime.parse(shippingTimeStr, formatter);
            
            // 獲取相關對象
            MemberDataVO member = memberDataDAO.findById(memberId);
            ShipsScheduleVO ship = shipsScheduleDAO.findById(shipId);
            CouponTypeVO coupon = couponId != null ? couponTypeDAO.findById(couponId) : null;
            
            // 創建新的旅遊訂單
            TravelOrderVO newOrder = new TravelOrderVO();
            newOrder.setMemberData(member);
            newOrder.setShipsSchedule(ship);
            newOrder.setCouponType(coupon);
            newOrder.setTravOrdeStatus(orderStatus);
            newOrder.setRoomAmount(roomAmount);
            newOrder.setTravOrdeAmount(totalAmount);
            
            travelOrderDAO.insert(newOrder);
            response.sendRedirect("TravelOrderServlet?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            // 在實際應用中，您應該將錯誤訊息傳遞給 JSP 並顯示給用戶
            request.setAttribute("errorMsg", "無法新增訂單，請檢查輸入資料。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/travelOrderForm.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // 刪除旅遊訂單
    private void deleteTravelOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("id"));
            travelOrderDAO.delete(orderId);
            response.sendRedirect("TravelOrderServlet?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            // 在實際應用中，您應該將錯誤訊息傳遞給 JSP 並顯示給用戶
            request.setAttribute("errorMsg", "無法刪除訂單，請稍後再試。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/travelOrders.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // 顯示編輯旅遊訂單表單
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("id"));
            TravelOrderVO existingOrder = travelOrderDAO.findById(orderId);
            
            if(existingOrder == null) {
                request.setAttribute("errorMsg", "找不到指定的訂單。");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/travelOrders.jsp");
                dispatcher.forward(request, response);
                return;
            }
            
            List<MemberDataVO> listMembers = memberDataDAO.getAllMembers();
            List<ShipsScheduleVO> listShips = shipsScheduleDAO.getAllShipsSchedules();
            List<CouponTypeVO> listCoupons = couponTypeDAO.getAllCoupons();
            
            request.setAttribute("travelOrder", existingOrder);
            request.setAttribute("listMembers", listMembers);
            request.setAttribute("listShips", listShips);
            request.setAttribute("listCoupons", listCoupons);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/travelOrderForm.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "無法編輯訂單，請稍後再試。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/travelOrders.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // 更新旅遊訂單
    private void updateTravelOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("travOrdeId"));
            int memberId = Integer.parseInt(request.getParameter("membId"));
            int shipId = Integer.parseInt(request.getParameter("shipId"));
            String couponIdStr = request.getParameter("coupId");
            Integer couponId = couponIdStr != null && !couponIdStr.isEmpty() ? Integer.parseInt(couponIdStr) : null;
            String orderStatus = request.getParameter("travOrdeStatus");
            int roomAmount = Integer.parseInt(request.getParameter("roomAmount"));
            double totalAmount = Double.parseDouble(request.getParameter("travOrdeAmount"));
            
            // 解析出發時間
            String shippingTimeStr = request.getParameter("shipShippingTime");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime shippingTime = LocalDateTime.parse(shippingTimeStr, formatter);
            
            // 獲取相關對象
            MemberDataVO member = memberDataDAO.findById(memberId);
            ShipsScheduleVO ship = shipsScheduleDAO.findById(shipId);
            CouponTypeVO coupon = couponId != null ? couponTypeDAO.findById(couponId) : null;
            
            // 獲取現有訂單並更新
            TravelOrderVO existingOrder = travelOrderDAO.findById(orderId);
            if(existingOrder != null) {
                existingOrder.setMemberData(member);
                existingOrder.setShipsSchedule(ship);
                existingOrder.setCouponType(coupon);
                existingOrder.setTravOrdeStatus(orderStatus);
                existingOrder.setRoomAmount(roomAmount);
                existingOrder.setTravOrdeAmount(totalAmount);
                
                travelOrderDAO.update(existingOrder);
                response.sendRedirect("TravelOrderServlet?action=list");
            } else {
                request.setAttribute("errorMsg", "找不到指定的訂單。");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/travelOrders.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 在實際應用中，您應該將錯誤訊息傳遞給 JSP 並顯示給用戶
            request.setAttribute("errorMsg", "無法更新訂單，請檢查輸入資料。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/emp/travelOrderForm.jsp");
            dispatcher.forward(request, response);
        }
    }
}
