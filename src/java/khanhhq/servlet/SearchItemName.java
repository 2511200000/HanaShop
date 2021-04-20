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
import khanhhq.daos.TblItemDAO;
import khanhhq.dtos.TblItemDTO;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Administrator
 */
public class SearchItemName extends HttpServlet {

    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SearchItemName.class.getName());
    private final String SEARCH = "search.jsp";
 
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
        String searhValue = request.getParameter("txtSearchValue");
        String categoryID = request.getParameter("cboFood");
        String priceMin = request.getParameter("cboPriceMin");
        float priceFloatMin = Float.parseFloat(priceMin);
        String priceMax = request.getParameter("cboPriceMax");
        float priceFloatMax = Float.parseFloat(priceMax);
        String indexString = request.getParameter("txtIndex");
        boolean status = true;
        TblItemDAO dao = new TblItemDAO();
        String url = SEARCH;
        try {
            HttpSession session = request.getSession();
            List<TblItemDTO> login = (List<TblItemDTO>) session.getAttribute("DISPLAYADMIN");
            /* TODO output your page here. You may use following sample code. */
            if (!searhValue.isEmpty() || !categoryID.isEmpty() || priceFloatMin != 0 && priceFloatMax != 0) {
                if (login != null) {
                    int count = dao.countAllITemsToSearchAdmin(searhValue, categoryID, priceFloatMin, priceFloatMax);
                    if (indexString == null) {
                        indexString = "1";
                    }
                    int index = Integer.parseInt(indexString);
                    int pageSize = 2;
                    int endPage = 0;
                    endPage = count / pageSize;
                    if (count % pageSize != 0) {
                        endPage++;
                    }

                     dao.searchLastname(searhValue, categoryID, priceFloatMin, priceFloatMax, index);
                    List<TblItemDTO> resultSearchName = dao.getAccountList();
                    if (resultSearchName != null) {
                        request.setAttribute("SEARCHNAMEADMIN", resultSearchName);
                        request.setAttribute("ENDPAGE", endPage);
                        request.setAttribute("SEARCHVALUE", searhValue);
                        request.setAttribute("INDEX", index);
                        request.setAttribute("PRICEMIN", priceMin);
                        request.setAttribute("PRICEMAX", priceMax);
                        request.setAttribute("CATEGORYID", categoryID);

                    }
                } else {
                    int count = dao.countAllITemsToSearch(searhValue, categoryID, priceFloatMin, priceFloatMax, status);
                    if (indexString == null) {
                        indexString = "1";
                    }
                    int index = Integer.parseInt(indexString);
                    int pageSize = 2;
                    int endPage = 0;
                    endPage = count / pageSize;
                    if (count % pageSize != 0) {
                        endPage++;
                    }

                     dao.searchLastnameStatus(searhValue, categoryID, priceFloatMin, priceFloatMax, status, index);
                    List<TblItemDTO> resultSearchName = dao.getAccountList();
                    if (resultSearchName != null) {
                        request.setAttribute("SEARCHNAME", resultSearchName);
                        request.setAttribute("ENDPAGE", endPage);
                        request.setAttribute("SEARCHVALUE", searhValue);
                        request.setAttribute("INDEX", index);
                        request.setAttribute("PRICEMIN", priceMin);
                        request.setAttribute("PRICEMAX", priceMax);
                        request.setAttribute("STATUS", status);
                        request.setAttribute("CATEGORYID", categoryID);

                    }
                }
            } else {
                url = SEARCH;

            }

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
