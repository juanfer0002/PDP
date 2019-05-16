/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appcells.controller;

import com.mycompany.appcells.business.CellBusiness;
import com.mycompany.appcells.business.impl.CellBusinessImpl;
import com.mycompany.appcells.model.Cell;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sala312
 */
public class CellServlet extends HttpServlet {

    private static final String DASHBOARD_PATH = "/views/dashboard.jsp";
    private static final String MESSAGES_PATH = "/messages.jsp";
    private static final String NO_ACTION_VALID_PATH = "/no_valid_action.jsp";
    private static final String LIST_CELLS = "LIST_CELLS";
    private static final String RESERVE_CELL = "RESERVE_CELL";
    private static final String UPDATE_CELL = "UPDATE_CELL";
    private static final String FREE_CELL = "FREE_CELL";

    private static final CellBusiness CELL_BUSINESS = new CellBusinessImpl();

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
            case LIST_CELLS:
                this.listCells(request, response);
                break;

            case RESERVE_CELL:
                this.reserveCell(request, response);
                break;

            case UPDATE_CELL:
                this.updateCell(request, response);
                break;

            case FREE_CELL:
                this.freeCell(request, response);
                break;

            default:
                request.getRequestDispatcher(NO_ACTION_VALID_PATH)
                        .forward(request, response);
                break;
        }

    }

    private void listCells(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<Cell> cells = CELL_BUSINESS.getCells();
        session.setAttribute("CELLS", cells);

        request.getRequestDispatcher(DASHBOARD_PATH).forward(request, response);
    }

    private void reserveCell(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cell cell = this.getCellFromRequest(request);
        CELL_BUSINESS.reserveCell(cell);
        this.listCells(request, response);
    }

    private void updateCell(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cell cell = this.getCellFromRequest(request);
        CELL_BUSINESS.updateCell(cell);
        this.listCells(request, response);
    }

    private void freeCell(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        CELL_BUSINESS.freeCell(id);
        this.listCells(request, response);
    }

    private Cell getCellFromRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        String date = request.getParameter("date");
        String photo = request.getParameter("photo");

        return new Cell(id, photo, userId, date, true);
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
