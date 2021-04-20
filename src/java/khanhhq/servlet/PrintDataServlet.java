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
import khanhhq.daos.TblCategoryDAO;
import khanhhq.daos.TblItemDAO;
import khanhhq.dtos.TblCategoryDTO;
import khanhhq.dtos.TblItemDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class PrintDataServlet extends HttpServlet {

    private final String DATA = "display.jsp";
    private final Logger log = Logger.getLogger(PrintDataServlet.class.getName());

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
        String txtIndex = request.getParameter("txtIndex");
        boolean status = true;
        String url = null;
        try {

            /* TODO output your page here. You may use following sample code. */
            TblItemDAO dao = new TblItemDAO();
            int count = dao.countAllITems(status);
            if (txtIndex == null) {
                txtIndex = "1";
            }

            int index = Integer.parseInt(txtIndex);
            int pageSize = 4;
            int endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }

            dao.printData(status, index);
            List<TblItemDTO> result = dao.getDataList();
            url = DATA;
            if (result != null) {
                HttpSession session = request.getSession();
                session.setAttribute("DISPLAY", result);

            }

            TblCategoryDAO categoryList = new TblCategoryDAO();
            List<TblCategoryDTO> cate = categoryList.getAllCategory();
            if (cate != null) {
                url = DATA;
                HttpSession session = request.getSession();
                session.setAttribute("CBONAME", cate);

            }

            List<TblItemDTO> listPrice = dao.getAllPrice();
            if (listPrice != null) {
                url = DATA;
                HttpSession session = request.getSession();
                session.setAttribute("CBOPRICE", listPrice);
            }
            request.setAttribute("ENDPAGE", endPage);
            request.setAttribute("INDEX", index);
            request.setAttribute("STATUS", status);
            
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
