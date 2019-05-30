/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jmunoz.battleship.controller;

import co.com.jmunoz.battleship.business.GameBusiness;
import java.io.IOException;
import java.io.PrintWriter;
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
public class GameServlet extends HttpServlet {

    private final GameBusiness GAME_BUSINESS = new GameBusiness();

    private static final String GAME_PATH = "/views/game.jsp";

    private static final String SHOW_GAME_BOARD = "SHOW_GAME_BOARD";
    private static final String PLAY = "PLAY";
    private static final String RESET = "RESET";

    private static final String NO_ACTION_VALID_PATH = "/no_valid_action.jsp";

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
            case SHOW_GAME_BOARD:
                this.getGameBoard(request, response);
                break;

            case PLAY:
                this.play(request, response);
                break;

            case RESET:
                this.reset(request, response);
                break;

            default:
                request.getRequestDispatcher(NO_ACTION_VALID_PATH)
                        .forward(request, response);
                break;
        }

    }

    private void getGameBoard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        int[][] gameBoard = GAME_BUSINESS.getGameBoard();
        session.setAttribute("GAME_BOARD", gameBoard);

        request.getRequestDispatcher(GAME_PATH).forward(request, response);
    }

    private void play(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String xStr = request.getParameter("x");
        String yStr = request.getParameter("y");

        int x = Integer.parseInt(xStr);
        int y = Integer.parseInt(yStr);
        boolean didItHit = GAME_BUSINESS.play(x, y);
        boolean isOver = GAME_BUSINESS.checkIfOver();

        int[][] gameBoard = GAME_BUSINESS.getGameBoard();
        session.setAttribute("GAME_BOARD", gameBoard);

        String msg = "You fail, You really suck!! ";
        String msgType = "danger";
        if (didItHit) {
            msg = "Hurra!!! you hit it!";
            msgType = "success";
        }

        if (isOver) {
            msg = "Hurra!! you just won!!";
            msgType = "warning";
        }

        session.setAttribute("MSG_TYPE", msgType);
        session.setAttribute("MSG", msg);

        request.getRequestDispatcher(GAME_PATH).forward(request, response);
    }

    private void reset(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        GAME_BUSINESS.restart();

        session.setAttribute("MSG", "Here we go again!! Play!");
        request.getRequestDispatcher(GAME_PATH).forward(request, response);
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
