/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhcn.cart.Cart;

/**
 *
 * @author nguye
 */
@WebServlet(name = "RemoveCartController", urlPatterns = {"/RemoveCartController"})
public class RemoveCartController extends HttpServlet {

    private final String SUCCESS = "cart.jsp";

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
            int cakeId = Integer.parseInt(request.getParameter("txtCakeId"));
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            cart.delete(cakeId);
            int oldQuantity = Integer.parseInt(request.getParameter("txtOldQuantity"));
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            float total = Float.parseFloat(session.getAttribute("TOTAL").toString());
            total -= oldQuantity * price;
            session.setAttribute("TOTAL", total);
            session.setAttribute("CART", cart);
        } catch (NumberFormatException e) {
            log("Exception at RemoveCartController: " + e.toString());
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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
