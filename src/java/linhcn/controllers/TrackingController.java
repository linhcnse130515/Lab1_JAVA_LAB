/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhcn.cakes.CakeDAO;
import linhcn.cakes.CakeDTO;
import linhcn.order.OrderDAO;
import linhcn.order.OrderDTO;
import linhcn.users.UserDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "TrackingController", urlPatterns = {"/TrackingController"})
public class TrackingController extends HttpServlet {

    private final static String SUCCESS = "tracking.jsp";

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
        try {
            int searchOrder = Integer.parseInt(request.getParameter("searchOrder"));

            OrderDAO orderDAO = new OrderDAO();
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            OrderDTO orderDTO;
            if (user.getRole().equals("Admin")) {
                orderDTO = orderDAO.searchOrderbyAdmin(searchOrder);
            } else {
                orderDTO = orderDAO.searchOrderbyUser(searchOrder, user.getUserID());
            }

            CakeDAO cakeDAO = new CakeDAO();
            List<CakeDTO> cakes = orderDAO.getCakes(searchOrder);

            List<CakeDTO> list = new ArrayList<>();
            for (CakeDTO cakeDTO : cakes) {
                CakeDTO cake = cakeDAO.getCake(cakeDTO.getCakesId(), cakeDTO.getQuantity());
                list.add(cake);
            }
            request.setAttribute("LIST", list);
            request.setAttribute("ORDER", orderDTO);
        } catch (NumberFormatException | SQLException | NamingException e) {
            log("Exception at TrackingController" + e.toString());
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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
