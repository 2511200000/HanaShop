/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class DispatchServlet extends HttpServlet {

    private final String DATA_CONTROLL = "PrintDataServlet";
     private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String logout_page = "LogOutServlet";
    private final String SEARCH_CONTROLLER = "SearchItemName";
    private final String DELETE_CONTROLLER = "DeleteItemServlet";
    private final String UPDATE_CONTROLLER = "UpdateItemServlet";
    private final String CREATE_CONTROLLER = "CreateNewItemServlet";
    private final String HISTORY_CONTROLLER = "PrintHistoryServlet";
    private final String ADDITEM_CONTROLLER = "AddCartItemServlet";
    private final String VIEWCART_CONTROLLER = "viewCart.jsp";
    private final String REOMVE_CONTROLLER = "RemoveItemServlet";
    private final String CONFIRM_CONTROLLER = "ConFirmInfoServlet";
    private final String CHECKOUT_CONTROLLER = "ConfirmItemServlet";
    private final String HISTORYSHOPPING_CONTROLLER = "PrintHistoryShoppingServlet";
    private final String SEARCHHISTORYSHOPPING_CONTROLLER = "SearchHistoryShoppingServlet";

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
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        String button = request.getParameter("btAction");
         try {
           
            /* TODO output your page here. You may use following sample code. */
            if (button == null) {
                url = DATA_CONTROLL;
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("LogOut")) {
                url = logout_page;
            } else if (button.equals("Search")) {
                url = SEARCH_CONTROLLER;
            } else if (button.equals("Delete")) {                 
                url = DELETE_CONTROLLER;              
            }else if(button.equals("Update")){
                url = UPDATE_CONTROLLER;
            }else if(button.equals("CreateNewItem")){
                url = CREATE_CONTROLLER;
            }else if(button.equals("History")){
                url = HISTORY_CONTROLLER;
            }else if(button.equals("AddCart")){
                url = ADDITEM_CONTROLLER;
            }else if(button.equals("ViewCart")){
                url = VIEWCART_CONTROLLER;
            }else if(button.equals("RemoveSelectedBooks")){
                url = REOMVE_CONTROLLER;
            }else if(button.equals("Confirm")){
                url = CONFIRM_CONTROLLER;
            }else if(button.equals("CheckOut")){
                url = CHECKOUT_CONTROLLER;
            }else if(button.equals("HistoryShopping")){
                url = HISTORYSHOPPING_CONTROLLER;
            }else if(button.equals("SearchHistory")){
                url = SEARCHHISTORYSHOPPING_CONTROLLER;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
