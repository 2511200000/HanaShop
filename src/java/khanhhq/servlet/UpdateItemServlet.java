/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhhq.daos.TblHistoryDAO;
import khanhhq.daos.TblItemDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class UpdateItemServlet extends HttpServlet {

    private final String UPDATEERR = "updateErr.html";
    private final Logger log = Logger.getLogger(UpdateItemServlet.class.getName());

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
        String itemname = request.getParameter("txtItemname");
        String image = request.getParameter("txtImage");
        String imageSuccess = request.getParameter("txtImageSuccess");
        String description = request.getParameter("txtdescription");
        String price = request.getParameter("txtPrice");
        String urlRewriting = UPDATEERR;
        String category = request.getParameter("cboFood");
        String cboStatus = request.getParameter("cboStatus");
        String quantity = request.getParameter("txtquantity");
        String id = request.getParameter("txtItemID");
        try {
            float priceFloat = Float.parseFloat(price);
            boolean status = true;
            if (cboStatus.equals("false")) {
                status = false;
            }

            int quantityInt = Integer.parseInt(quantity);

            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            String button = request.getParameter("btAction");
            HttpSession session = request.getSession();
            String userID = (String) session.getAttribute("USERID");
            boolean result;

            TblItemDAO dao = new TblItemDAO();

            TblHistoryDAO daoHisrory = new TblHistoryDAO();
            int idHistory = daoHisrory.printIdHistory();
             if (idHistory == 0) {
                idHistory++;
            } else {
                idHistory++;
            }
            /* TODO output your page here. You may use following sample code. */
            boolean createHistory = daoHisrory.createHistory(idHistory, id, userID, date, button);
            if (createHistory) {
                if (image != "") {
                    result = dao.updateItem(itemname, image, description, priceFloat, category, status, quantityInt, id);
                } else {
                    result = dao.updateItem(itemname, imageSuccess, description, priceFloat, category, status, quantityInt, id);
                }
                if (result) {
                    urlRewriting = "LoadDataServlet";
                }
            }

        } catch (SQLException e) {
            BasicConfigurator.configure();
            log.error("SQLException");
        } catch (NamingException e) {
            BasicConfigurator.configure();
            log.error("NamingException");
        } finally {
            response.sendRedirect(urlRewriting);
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
