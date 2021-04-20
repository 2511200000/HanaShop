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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhhq.daos.TblItemDAO;
import org.apache.log4j.BasicConfigurator;
/**
 *
 * @author Administrator
 */
public class ConFirmInfoServlet extends HttpServlet {
    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ConFirmInfoServlet.class.getName());
    private final String CONFIRM = "confirm.jsp";
    private final String FAIL = "viewCart.jsp";

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
        String[] itemID = request.getParameterValues("txtItemID");

        String[] quantity = request.getParameterValues("quantity");
        
        String[] price = request.getParameterValues("price");

        String[] total = request.getParameterValues("total");

        String totalBill = request.getParameter("totalAll");
        float totalAll = Float.parseFloat(totalBill);
        String url = CONFIRM;
        try {
            TblItemDAO dao = new TblItemDAO();
            HttpSession session = request.getSession();
            
            boolean checkQuantity = false;
            String msg = null;
            for (int i = 0; i < itemID.length; i++) {
                int quantityAll = dao.getQuantityItem(itemID[i]);
                String itemname = dao.getItemName(itemID[i]);
                if (quantityAll < Integer.parseInt(quantity[i])) {
                     msg = "Quantity max  " + itemname + " is " + quantityAll;
                    checkQuantity = true;
                } 
                
                if(checkQuantity){                 
                    request.setAttribute("ERRQUANTITY", msg);
                    url = FAIL;
                }else {                                    
                    session.setAttribute("ITEMID", itemID);
                    session.setAttribute("QUANTITY", quantity);
                    session.setAttribute("PRICE", price);
                    session.setAttribute("TOTAL", total);
                    session.setAttribute("TOTALALL", totalAll);
                    url = CONFIRM;
                }
            }

            /* TODO output your page here. You may use following sample code. */
        } catch (SQLException ex) {
            BasicConfigurator.configure();
            log.error("SQLException");
        } catch (NamingException ex) {
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
