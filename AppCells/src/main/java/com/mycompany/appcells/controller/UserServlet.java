/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appcells.controller;

import com.mycompany.appcells.business.UserBusiness;
import com.mycompany.appcells.business.impl.UserBusinessImpl;
import com.mycompany.appcells.util.AlertTypes;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sala312
 */
public class UserServlet extends HttpServlet {

    private static final String NO_ACTION_VALID_PATH = "/no_valid_action.jsp";
    private static final String SIGN_IN = "SIGN_IN";

    private static final UserBusiness USER_BUSINESS = new UserBusinessImpl();

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

        String action = request.getParameter("action");
        action = action != null ? action : "";
        
        switch (action) {
            case SIGN_IN:
                this.Signin(request, response);
            default:
                request.getRequestDispatcher(NO_ACTION_VALID_PATH)
                        .forward(request, response);
        }
    }

    private void Signin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String pathToDispatch;
        boolean isValid = USER_BUSINESS.authUser(username, password);
        
        if (isValid) {
            pathToDispatch = "/views/dashboard.jsp";
        } else {
            pathToDispatch = "/messages.jsp";
            session.setAttribute("MSG", "User isn't valid");
            session.setAttribute("TYPE", AlertTypes.WARNING);
            session.setAttribute("ROUTE", "/");
        }

        request.getRequestDispatcher(pathToDispatch).forward(request, response);
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
