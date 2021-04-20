<%-- 
    Document   : checkOut
    Created on : Jan 23, 2021, 3:42:08 PM
    Author     : Administrator
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/main.css">
        <title>checkOut</title>
    </head>
    <body>
        <div class="imageHanaShop">                   
        </div>
        <c:set var="cart" value="${sessionScope.DISPLAYORDER}" />
        <c:if test="${not empty cart}">
            <div class="displayAdmin">
                <table border="1" class="tableAdmin">
                    <thead>
                        <tr>
                            <th  class="columnAdmin">No.</th>
                            <th  class="columnAdmin">customerID</th>
                            <th  class="columnAdmin">dateOrder</th>
                            <th  class="columnAdmin">totalAll</th>
                        </tr>
                    </thead>
                    <tbody>
    
                        <c:forEach var="dto" items="${cart}" varStatus="counter">
                        <form action="DispatchServlet">
                            <tr>
                                <td class="rowAdmin">${counter.count}
                                    .</td>
                                <td class="rowAdmin">${dto.custommerID}</td>
                                <td class="rowAdmin">${dto.dateOrder}</td>
                                <td class="rowAdmin">${dto.totalAll}</td>
                            </tr>
                        </form>
                    </c:forEach>
    
                </tbody>
            </table>
    
            </div>
    </table>
</c:if>
     
    <a href="PrintDataServlet">Back to Home</a>
</body>
</html>
