/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhcn.cakes.CakeDAO;
import linhcn.cakes.CakeDTO;
import linhcn.categories.CategoryDTO;
import linhcn.status.StatusDTO;
import linhcn.users.UserDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "UpdateCakeController", urlPatterns = {"/UpdateCakeController"})
public class UpdateCakeController extends HttpServlet {

    private static final String SUCCESS = "AdminController";

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
            int id = Integer.parseInt(request.getParameter("txtId"));
            String name = request.getParameter("txtName");
            String description = request.getParameter("txtDescription");
            Float price = Float.parseFloat(request.getParameter("txtPrice"));
            Date createDate = Date.valueOf(request.getParameter("txtCreateDate"));
            String c = request.getParameter("txtExpirationDate");
            Date expirationDate = Date.valueOf(request.getParameter("txtExpirationDate"));
            String image = request.getParameter("txtImage");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            int categoryId = Integer.parseInt(request.getParameter("rsCategory"));
            CategoryDTO category = new CategoryDTO(categoryId);
            int statusId = Integer.parseInt(request.getParameter("rsStatus"));
            StatusDTO status = new StatusDTO(statusId);

            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            CakeDAO dao = new CakeDAO();
            CakeDTO dto = new CakeDTO(id, name, description, price, createDate, expirationDate, image, quantity, category, status);
            boolean isUpdated = dao.update(dto, user);
            if (isUpdated) {
                request.setAttribute("MESSAGE", "Update error");
            }
        } catch (SQLException ex) {
            log("Exception at UpdateCakeController  " + ex.getMessage());
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
