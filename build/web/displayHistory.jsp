<%-- Document : displayHistory Created on : Jan 22, 2021, 7:25:49 AM Author : Administrator --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/main.css">
        <title>History</title>
    </head>

    <body>

        <c:set var="result" value="${sessionScope.DISPLAYADMIN}" />  

        <c:if test="${not empty result}">
            <div class="backgoundCreateItem"></div>
            <div class="displayAdmin">
                <c:set var="history" value="${requestScope.HISTORY}" />
                <c:if test="${not empty history}">
                    <table border="1"  class="tableAdmin">
                        <thead>
                            <tr>
                                <th class="columnAdmin">No.</th>
                                <th class="columnAdmin">itemID</th>
                                <th class="columnAdmin">userID</th>
                                <th class="columnAdmin">date</th>
                                <th class="columnAdmin">action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${history}" varStatus="counter">
                                <tr>
                                    <td class="rowAdmin">${counter.count}
                                    </td>
                                    <td class="rowAdmin">${dto.itemID}</td>
                                    <td class="rowAdmin">${dto.userID}</td>
                                    <td class="rowAdmin">${dto.date}</td>
                                    <td class="rowAdmin">${dto.action}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <h2>
                        <a href="LoadDataServlet">Click here back to HomeAdmin</a>
                    </h2>   
                </c:if>
            </div>
            <c:if test="${empty history}">
                <h2>
                    No record
                </h2>
                <h2>
                    <a href="LoadDataServlet">Click here back to HomeAdmin</a>
                </h2>   
            </c:if>
        </c:if>


        <c:set var="result" value="${sessionScope.DISPLAY}" />
        <c:if test="${not empty result}">
            <form action="DispatchServlet">

                <div class="textSearch">
                    Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />

                    <input type="date" name="txtDate" value="${param.txtDate}" />
                </div>


                <div class="buttonSearchAdmin"> 
                    <input class="searchButton" type="submit" value="SearchHistory" name="btAction" />
                </div>
            </form>
            <c:set var="history" value="${sessionScope.HISTORYSHOPPNG}" />
            <c:if test="${not empty history}">
                <div class="backgoundCreateItem"></div>
                <div class="displayAdmin"></div>
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
                            <c:forEach var="dto" items="${history}" varStatus="counter">
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
                <h2>
                    <a href="PrintDataServlet">Click here back to Shopping</a>
                </h2>   
            </c:if>
            <c:if test="${empty history}">
                <div class="backgoundCreateItem"></div>
                <h2>
                    No record
                </h2>
                <h2>
                    <a href="PrintDataServlet">Click here back to Shopping</a>
                </h2>   
            </c:if>
        </c:if>
    </body>

</html>