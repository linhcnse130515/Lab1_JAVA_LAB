/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import linhcn.cakes.CakeDAO;
import linhcn.cakes.CakeDTO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private final static String SUCCESS = "products.jsp";

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
            String searchName = request.getParameter("searchName");
            String name = searchName == null ? "" : searchName;
            String searchCategory = request.getParameter("rsCategory");
            String category = (searchCategory == null || searchCategory.equals("All")) ? "" : searchCategory;

            String fMoney = request.getParameter("from");
            double fromMoney = (fMoney != null && !"".equals(fMoney)) ? Double.parseDouble(fMoney) : 0;

            String tMoney = request.getParameter("to");
            double toMoney = (tMoney != null && !"".equals(tMoney)) ? Double.parseDouble(tMoney) : Double.MAX_VALUE;

            String txtPage = request.getParameter("page");
            int page = txtPage != null ? Integer.parseInt(txtPage) : 1;
            CakeDAO dao = new CakeDAO();
            int pages = dao.getTotalPageSearch(name, category, fromMoney, toMoney);
            List<CakeDTO> list = dao.searchCakes(name, category, fromMoney, toMoney, page);

            request.setAttribute("SEARCHNAME", searchName);
            request.setAttribute("SEARCHCATEGORY", category);
            request.setAttribute("FROMMONEY", fMoney);
            request.setAttribute("TOMONEY", tMoney);
            request.setAttribute("LIST", list);
            request.setAttribute("PAGES", pages);
        } catch (NumberFormatException | SQLException | NamingException e) {
            log("Exception at SearchController" + e.toString());
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
