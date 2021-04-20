/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhhq.daos.TblCustomerDAO;
import khanhhq.daos.TblHistoryShoppingDAO;
import khanhhq.daos.TblItemDAO;
import khanhhq.daos.TblOrderDAO;
import khanhhq.daos.TblOrderDetailsDAO;
import khanhhq.dtos.TblOrderDTO;
import org.apache.log4j.BasicConfigurator;
 
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class ConfirmItemServlet extends HttpServlet {

    private final String SEARCH = "displayAdmin.jsp";
    private final Logger log = Logger.getLogger(AddCartItemServlet.class.getName());

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
        String url = SEARCH;

        String customname = request.getParameter("txtCustomer");
         String address = request.getParameter("txtAddress");

        String phonenumber = request.getParameter("txtPhonenumber");

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

         try {

            HttpSession session = request.getSession();
            String[] itemID = (String[]) session.getAttribute("ITEMID");

            String[] quantity = (String[]) session.getAttribute("QUANTITY");

            String[] price = (String[]) session.getAttribute("PRICE");

            Float totalALl = (Float) session.getAttribute("TOTALALL");

             String userID = (String) session.getAttribute("USERID");
            TblOrderDAO daoOrder = new TblOrderDAO();
            TblHistoryShoppingDAO daoHistory = new TblHistoryShoppingDAO();

            TblOrderDetailsDAO daoOrderDetail = new TblOrderDetailsDAO();
            /* TODO output your page here. You may use following sample code. */
            TblCustomerDAO daoCustomer = new TblCustomerDAO();
            
            TblItemDAO daoItem = new TblItemDAO();
             int idCustomer = daoCustomer.printIdCustomer();
            if (idCustomer == 0) {
                idCustomer++;             
            } else {
                idCustomer++;
             }
            if(customname == null){
                  daoCustomer.createCustomer(idCustomer, userID, address, phonenumber);
            }else{
                  daoCustomer.createCustomer(idCustomer, customname, address, phonenumber);
            }
            
            int idOrderID = daoOrder.printIdOrder();
            if (idOrderID == 0) {
                idOrderID++;
                daoOrder.createOrder(idOrderID, idCustomer, date, totalALl);
            } else {
                idOrderID++;
                daoOrder.createOrder(idOrderID, idCustomer, date, totalALl);
            }
          
            for (int i = 0; i < itemID.length; i++) {
                daoOrderDetail.createOrderDetails(idOrderID, itemID[i], Integer.parseInt(quantity[i]), Float.parseFloat(price[i]), Float.parseFloat(price[i]) * Float.parseFloat(quantity[i]));

            }
            
            for (int i = 0; i < itemID.length; i++) {  
                daoHistory.createHistoryShopping(idOrderID, itemID[i], userID, date, Integer.parseInt(quantity[i]), totalALl);
                 int quantityAll = daoItem.getQuantityItem(itemID[i]);
                daoItem.updateQuantity(quantityAll -  Integer.parseInt(quantity[i]), itemID[i]);
            }
             daoOrder.printOrder(idOrderID, idCustomer);
            List<TblOrderDTO> result = daoOrder.getlistOrder();
             
            if (result != null) {
                session.setAttribute("DISPLAYORDER", result);
                url = "checkOut.jsp";
            }
            session.removeAttribute("CUSTCART");
        } catch (SQLException e) {
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
