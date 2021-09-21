/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhcn.categories.CategoryDAO;
import linhcn.categories.CategoryDTO;
import linhcn.users.UserDTO;

/**
 *
 * @author nguye
 */
public class MainController extends HttpServlet {

    private static final String LOG_IN = "LoginController";
    private static final String LOG_OUT = "LogoutController";
    private static final String LOG_IN_GMAIL = "LoginGmailController";
    private static final String ADMIN = "AdminController";
    private static final String SEARCH_NAME = "SearchController";
    private static final String ADD_CART = "AddCartController";
    private static final String REMOVE = "RemoveCartController";
    private static final String UPDATE = "UpdateCartController";
    private static final String CHECK_OUT = "CheckOutController";
    private static final String UPDATE_CAKE = "UpdateCakeController";
    private static final String NEW_CAKE = "NewCakeController";
    private static final String ADD_INFO = "AddInfoController";
    private static final String TRACKING = "TrackingController";

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
        String success = "";
        try {
            String btn = request.getParameter("btnAction");
            CategoryDAO dao = new CategoryDAO();
            HttpSession session = request.getSession();
            List<CategoryDTO> categories = dao.getGategories();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            session.setAttribute("CATEGORIES", categories);
            if (btn == null) {
                success = "home.jsp";
            } else if (btn.equals("Login")) {
                success = LOG_IN;
            } else if (btn.equals("Logout")) {
                success = LOG_OUT;
            } else if (btn.equals("LoginGmail")) {
                success = LOG_IN_GMAIL;
            } else if (btn.equals("Admin")) {
                success = ADMIN;
            } else if (btn.equals("Search")) {
                success = SEARCH_NAME;
            } else if (btn.equals("Add to Cart")) {
                success = ADD_CART;
            } else if (btn.equals("Remove")) {
                success = REMOVE;
            } else if (btn.equals("Update")) {
                success = UPDATE;
            } else if (btn.equals("Add Information")) {
                success = ADD_INFO;
            } else if ((user == null || user.getRole().equals("User")) && btn.equals("Check Out")) {
                success = CHECK_OUT;
            } else if (user != null) {
                if (btn.equals("Tracking")) {
                    success = TRACKING;
                } else if (user.getRole().equals("Admin")) {
                    if (btn.equals("New Cake")) {
                        success = NEW_CAKE;
                    } else if (btn.equals("Update Cake")) {
                        success = UPDATE_CAKE;
                    }
                }

            }
        } catch (SQLException e) {
            log("Exception at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(success).forward(request, response);
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
