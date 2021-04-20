<%-- 
    Document   : searchHistory
    Created on : Apr 15, 2021, 7:42:49 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="CSS/main.css">
        <title>SearchHistory</title>
    </head>
    <body>
         <form action="DispatchServlet">
            <div class="textSearch">
                Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />

                <input type="date" name="txtDate" value="${param.txtDate}" />
            </div>


            <div class="buttonSearchAdmin"> 
                <input class="searchButton" type="submit" value="SearchHistory" name="btAction" />
            </div>
        </form>
            
            
         <c:set var="result" value="${requestScope.SEARCHHISTORY}"/>
         <div class="backgoundCreateItem"></div>
        <c:if test="${not empty result}">
             
                     <div class="displayAdmin">
                        <table border="1"  class="tableAdmin">
                            <thead>
                                <tr>
                                    <th class="columnAdmin">No.</th>
                                    <th class="columnAdmin">orderID</th>
                                    <th class="columnAdmin">userID</th>
                                    <th class="columnAdmin">itemID</th>
                                    <th class="columnAdmin">date</th>
                                    <th class="columnAdmin">quantity</th>
                                    <th class="columnAdmin">totalAll</th>
                                 </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${result}" varStatus="counter">
                                    <tr>
                                        <td class="rowAdmin">${counter.count}
                                            .</td>
                                        <td class="rowAdmin">${dto.orderID}</td>
                                        <td class="rowAdmin">${dto.userID}</td>
                                        <td class="rowAdmin">${dto.itemID}</td>
                                        <td class="rowAdmin">${dto.date}</td>
                                        <td class="rowAdmin">${dto.quantity}</td>
                                        <td class="rowAdmin">${dto.totalAll}</td>
                                     </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

        </c:if>
        <c:if test="${ empty result}">
            <h2>  No record</h2>
        </c:if>
             <div class="Backto">
                <b><a href="displayHistory.jsp">Back to History</a></b>
            </div>
    </body>
</html>
