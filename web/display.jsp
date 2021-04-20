<%-- Document : display Created on : Jan 9, 2021, 3:54:27 PM Author : Administrator --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/main.css">

        <title>MenuHanaShop</title>
    </head>

    <body>
        <c:set var="result" value="${sessionScope.DISPLAY}" />
        <c:if test="${not empty result}">
            <div class="hearderDisplayAdmin">
                <ul class="nav-listAdmin">
                    <li class="nav-itemAdmin">
                        <c:set var="username" value="${sessionScope.USERNAME}" />
                        <c:if test="${not empty username}">
                            <font color="red">
                            Welcome, ${sessionScope.USERNAME}
                            </font><br>

                        </c:if>
                    </li>
                    <li class="nav-itemAdmin">
                        <b> <a href="PrintDataServlet" title="MenuOfHanaShop">Home<i class="far fa-home"></i></a></b>
                    </li>

                </ul>
                <ul class="nav-listAdmin">
                    <c:if test="${empty username}">
                        <li class="nav-itemAdmin">
                            <b><a href="login.html" title="Login">Sign up</a> </b>
                        </li>
                    </c:if>
                    <c:if test="${not empty username}">
                        <li class="nav-itemAdmin">
                            <form action="DispatchServlet">
                                <input type="submit" value="LogOut" name="btAction" />
                            </form>
                        </li>
                        <li class="nav-itemAdmin">
                            <form action="DispatchServlet">
                                <b> <input class="buttonHistory" type="submit" value="HistoryShopping" name="btAction" /></b>
                            </form>
                        </li>
                    </c:if>
                </ul>
            </div>



            <div class="searchAdmin">
                <div class="searchAdminList">
                    <form action="DispatchServlet">
                        <div class="textSearch">
                            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
                        </div>
                        <select name="cboFood">
                            <option label="Ple choose category" value=""></option>
                            <c:forEach var="cbo" items="${sessionScope.CBONAME}" varStatus="counter">
                                <option label="${cbo.categoryname}" value="${cbo.category}"></option>
                            </c:forEach>
                        </select>

                        <select name="cboPriceMin">
                            <option label="Ple choose priceMin" value="1"></option>
                            <c:forEach var="cbo" items="${sessionScope.CBOPRICE}" varStatus="counter">
                                <option label="${cbo.price}" value="${cbo.price}"></option>

                            </c:forEach>
                        </select>

                        <select name="cboPriceMax">
                            <option label="Ple choose priceMax" value="99999"></option>
                            <c:forEach var="cbo" items="${sessionScope.CBOPRICE}" varStatus="counter">
                                <option label="${cbo.price}" value="${cbo.price}"></option>

                            </c:forEach>
                        </select><br>
                        <div class="buttonSearchAdmin">
                            <input class="searchButton" type="submit" value="Search" name="btAction" />
                            <input type="hidden" name="txtIndex" value="1" />
                        </div>
                    </form><br>

                </div>
            </div>
            <c:set var="category" value="${param.cboFood}" />

            <c:set var="priceMin" value="${param.cboPriceMin}" />

            <c:set var="priceMax" value="${param.cboPriceMax}" />

            <c:set var="searchValue" value="${param.txtSearchValue}" />
        </c:if>

        <c:if test="${not empty result}">

            <table border="1" class="displayUser">
                <c:forEach var="item" items="${result}" varStatus="counter">
                    <form action="DispatchServlet">
                        <tbody class="itemBodyUser">
                        <td class="itemUser"> 
                            <img src="images/${item.image}"><br>
                            <div class="detaliItem">
                                <input type="hidden" name="txtItemID" value="${item.itemID}" />
                                <b>  ${item.itemname}<br>
                                    <div class="displayItem">                                 
                                        ${item.category} <br>                                                                
                                        ${item.description}<br>
                                        ${item.quantity}<br>
                                        ${item.price}<br>
                                        ${item.createDate}<br></b>
                                    </div>
                                    <div class="buttonOrder">
                                        <input class="Order" type="submit" name="btAction" value="AddCart" />
                                        <input class="Order" type="submit" value="ViewCart" name="btAction" />
                                    </div>
                            </div>
                        </td>
                        </tbody>
                    </form>
                </c:forEach>
            </table>

        </c:if>
        <div class="paging">
            <c:forEach begin="1" end="${ENDPAGE}" var="i">
                <a id="${i}" href="DispatchServlet?status=${STATUS}&txtIndex=${i}">${i}</a> 

            </c:forEach>
        </div>
        <script>
              document.getElementById('${INDEX}').style.color = "red";
        </script>
    </body>

</html>