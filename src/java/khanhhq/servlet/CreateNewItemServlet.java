/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import khanhhq.daos.TblItemDAO;
import khanhhq.dtos.TblCreateItemError;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class CreateNewItemServlet extends HttpServlet {

    private final String CREATEERR_ERR = "createItem.jsp";
    private final Logger log = Logger.getLogger(CreateNewItemServlet.class.getName());

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
        String itemID = request.getParameter("txtItemID");
         String itemname = request.getParameter("txtItemname");
        String description = request.getParameter("txtdescription");
        String price = request.getParameter("txtPrice");

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
 
        String category = request.getParameter("cboFood");
        String quantity = request.getParameter("txtquantity");

        String image = request.getParameter("txtImage");
        TblCreateItemError err = new TblCreateItemError();

        String url = CREATEERR_ERR;
        try {
            float priceFloat = Float.parseFloat(price);
            int quantityInt = Integer.parseInt(quantity);
            boolean foundErr = false;
            if (itemID.isEmpty() || itemname.isEmpty() || description.isEmpty() || price.isEmpty() || quantity.isEmpty() || category.isEmpty() || image.isEmpty()) {
                foundErr = true;
                err.setErrBlank("Must fill all information");
            }
            if (priceFloat < 0) {
                foundErr = true;
                err.setPriceErr("Price lớn hơn không");
            }
            if (quantityInt < 0) {
                foundErr = true;
                err.setQuantityErr("Quantity lớn hơn không");
            }
            if (foundErr) {
                request.setAttribute("CREATEERR", err);
            } else {
                TblItemDAO dao = new TblItemDAO();
                boolean result = dao.createItem(itemID, itemname, image, description, priceFloat, category, true, quantityInt, date);
                if (result) {
                    url = "LoadDataServlet";
                }
            }
            /* TODO output your page here. You may use following sample code. */
        } catch (NumberFormatException e) {
            String errMSG = e.getMessage();
            log("CreateNewItemServlet_SQL " + errMSG);
            if (errMSG.contains("input")) {
                err.setErrNumberFormat("Ko nhập chữ tại price hoặc quantity");
                request.setAttribute("CREATEERR", err);
            }
            BasicConfigurator.configure();
            log.error("NumberFormatException");
        } catch (SQLException e) {
            String errMSG = e.getMessage();
            log("CreateNewItemServlet_SQL " + errMSG);
            if (errMSG.contains("duplicate")) {
                err.setItemIDisExist(itemID + " Is exist");
                request.setAttribute("CREATEERR", err);
            }
            BasicConfigurator.configure();
            log.error("SQLException");
        } catch (NamingException e) {
            BasicConfigurator.configure();
            log.error("NamingException");
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
