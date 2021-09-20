/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhcn.cakes.CakeDAO;
import linhcn.cakes.CakeDTO;
import linhcn.cart.Cart;
import linhcn.order.OrderDAO;
import linhcn.users.UserDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

    private final String SUCCESS = "cart.jsp";
    private final String ADD_INFO = "addInformation.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            UserDTO user = ((UserDTO) session.getAttribute("USER"));
            String userName = request.getParameter("txtName");
            Float total = (Float) session.getAttribute("TOTAL");
            if (user == null && userName == null) {
                url = ADD_INFO;
            } else {
                String userID = "khach";
                String phoneNumber = "";
                String address;
                String name;
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    if (user == null) {
                        phoneNumber = request.getParameter("txtPhone");
                        name = userName;
                    } else {
                        userID = user.getUserID();
                        name = user.getFullName();
                    }
                    address = request.getParameter("txtAddress");

                    long millis = System.currentTimeMillis();
                    Date orderDate = new java.sql.Date(millis);
                    String paymentMethod = "Cash payment upon delivery";
                    OrderDAO orderDao = new OrderDAO();
                    int orderId = orderDao.checkoutOrder(total, orderDate, paymentMethod, address, name, phoneNumber, userID);
                    CakeDAO cakeDao = new CakeDAO();
                    Map<Integer, CakeDTO> map = cart.getCart();
                    for (Map.Entry<Integer, CakeDTO> entry : map.entrySet()) {
                        CakeDTO cake = entry.getValue();
                        int currentQuantity = cakeDao.getQuantity(cake.getCakesId());
                        if (currentQuantity >= cake.getQuantity()) {
                            orderDao.insertOrderDetails(cake.getQuantity(), orderId, cake.getCakesId());
                            cakeDao.updateQuantity(orderId, currentQuantity - cake.getQuantity());
                        }
                    }
                    request.setAttribute("MESSAGEORDER", "Order successful,See you again!");
                    session.setAttribute("CART", null);
                    session.setAttribute("TOTAL", null);
                    request.setAttribute("ORDERID", orderId);
                }
            }
        } catch (SQLException | NamingException e) {
            log("Exception at CheckOutController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            //response.sendRedirect(SUCCESS);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
