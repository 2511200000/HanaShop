<%-- Document : createItem Created on : Jan 19, 2021, 5:00:28 PM Author : Administrator --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/main.css">
        <title>Create New Item</title>
    </head>

    <body>
        <c:set var="result" value="${sessionScope.DISPLAYADMIN}" />  
        <c:if test="${empty result}">
            <div class="Fail"></div>
            <h2> <a href="login.html">Only admin can create item</a></h2>
        </c:if>
        <c:if test="${not empty result}">
            <div class="backgoundCreateItem">
            </div>
            <div class="createPage">
                <div class="createForm">
                    <h2>Create New Item</h2>
                    <form action="DispatchServlet" method="POST">
                        <c:set var="errors" value="${requestScope.CREATEERR}" />
                        <b> Item ID:</b> <input id="txtTextfile" type="text" name="txtItemID" value="" /><br>

                        <b> Item Name:</b><input id="txtTextfile" type="text" name="txtItemname" value="" /><br>
                        <b> Description:</b> <input id="txtTextfile" type="text" name="txtdescription"
                                                    value="" /><br>
                        <b> Price:</b> <input id="txtTextfile" type="text" name="txtPrice" value="" /><br>
                        <c:if test="${not empty errors.priceErr}">
                            <font color="red">
                            ${errors.priceErr}
                            </font><br>
                        </c:if>
                        <b>createDate:</b> <input id="txtTextfile" type="text" name="txtCreate" value="" disabled=""
                                                  placeholder="Set Current" /><br>
                        <b>Category: </b><select name="cboFood">
                            <option label="Ple choose category" value=""></option>
                            <c:forEach var="cbo" items="${sessionScope.CBONAME}" varStatus="counter">
                                <option label="${cbo.categoryname}" value="${cbo.category}"></option>
                            </c:forEach>
                        </select><br>
                        <b> Quantity:</b> <input id="txtTextfile" type="text" name="txtquantity" value="" /><br>
                        <c:if test="${not empty errors.quantityErr}">
                            <font color="red">
                            ${errors.quantityErr}
                            </font><br>
                        </c:if>
                        <b>Status:</b> <input id="txtTextfile" type="text" name="txtStatus" value="" disabled=""
                                              placeholder="default status is active" /><br>
                        <b> Image:</b> <input id="txtTextfile" type="file" name="txtImage" value="" /><br>
                        <div class="buttonCreate">
                            <input id="buttonLogin" type="submit" value="CreateNewItem" name="btAction" />
                            <input id="buttonLoginReset" type="reset" value="Reset" /><br>
                        </div>
                        <c:if test="${not empty errors.itemIDisExist}">
                            <font color="red">
                            ${errors.itemIDisExist}
                            </font><br>
                        </c:if>
                        <c:if test="${not empty errors.errNumberFormat}">
                            <font color="red">
                            ${errors.errNumberFormat}
                            </font><br>
                        </c:if>
                        <c:if test="${not empty errors.errBlank}">
                            <font color="red">
                            ${errors.errBlank}
                            </font><br>
                        </c:if>

                    </form><br>
                    <a href="LoadDataServlet">Click here back to adminPage</a>
                </div>
            </div>

        </c:if>

    </body>

</html>