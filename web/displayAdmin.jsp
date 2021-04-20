<%-- Document : displayAdmin Created on : Jan 15, 2021, 10:41:49 PM Author : Administrator --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/main.css">
        <title>JSP Page</title>
    </head>

    <body>
        <c:set var="result" value="${sessionScope.DISPLAYADMIN}" />  
        <c:if test="${empty result}">
            <div class="Fail"></div>
            <h2> <a href="login.html">Please login first</a></h2>
        </c:if>
        <c:if test="${not empty result}">
            <div class="hearderDisplayAdmin">
                <ul class="nav-listAdmin">
                    <li class="nav-itemAdmin">
                        <c:set var="username" value="${sessionScope.USERNAME}" />
                        <c:if test="${not empty username}">
                            <font color="red">
                            Welcome, ${sessionScope.USERNAME}
                            </font>
                        </c:if>
                    </li>
                    <li class="nav-itemAdmin">
                        <c:set var="username" value="${sessionScope.USERNAME}" />
                        <c:if test="${not empty username}">
                            <font color="red">
                            <b><a href="LoadDataServlet" title="DataAdmin">HomeAdmin</a></b>
                            </font>
                        </c:if>
                    </li>
                </ul>

                <ul class="nav-listAdmin">
                   
                    <li class="nav-itemAdmin">
                        <form action="DispatchServlet">
                            <input type="submit" value="LogOut" name="btAction" />
                        </form>
                    </li>
                    <li class="nav-itemAdmin">
                        <form action="DispatchServlet">
                            <b> <input class="buttonHistory" type="submit" value="History" name="btAction" /></b>
                        </form>
                    </li>

                </ul>
            </div>

            <h1 class="h1Admin">Admin</h1>

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



            <div class="createNewItem">
                <b> <a href="createItem.jsp">Create New Item</a></b>

            </div>
        </c:if>




        <c:if test="${not empty result}">
            <div class="displayAdmin">

                <table border="1" class="tableAdmin">
                    <thead>
                        <tr>
                            <th class="columnAdmin">No.</th>
                            <th class="columnAdmin">item ID</th>
                            <th class="columnAdmin">item name</th>
                            <th class="columnAdmin">image</th>
                            <th class="columnAdmin">description</th>
                            <th class="columnAdmin">price</th>
                            <th class="columnAdmin">create Date</th>
                            <th class="columnAdmin">category</th>
                            <th class="columnAdmin">quantity</th>
                            <th class="columnAdmin">status</th>
                            <th class="columnAdmin">Delete</th>
                            <th class="columnAdmin">Update</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet">
                            <tr>
                                <td class="rowAdmin">${counter.count}
                                    .</td>
                                <td class="rowAdmin">
                                    ${dto.itemID}
                                    <input type="hidden" name="txtItemID" value="${dto.itemID}" />
                                </td>
                                <td class="rowAdmin">
                                    <input type="text" name="txtItemname" value="${dto.itemname}" />
                                </td>
                                <td class="rowAdmin">
                                    <img src="images/${dto.image}" with="80px" height="100px">
                                    <input type="file" name="txtImage" value="${dto.image}" />
                                    <input type="hidden" name="txtImageSuccess" value="${dto.image}" />
                                </td>
                                <td class="rowAdmin">
                                    <input type="text" name="txtdescription" value="${dto.description}" />
                                </td>
                                <td class="rowAdmin">
                                    <input type="text" name="txtPrice" value="${dto.price}" />
                                </td>
                                <td class="rowAdmin">
                                    ${dto.createDate}
                                </td>
                                <td class="rowAdmin">
                                    <select name="cboFood">
                                        <c:forEach var="cbo" items="${sessionScope.CBONAME}"
                                                   varStatus="counter">
                                            <option label="${cbo.categoryname}" value="${cbo.category}"
                                                    <c:if test="${dto.category eq cbo.categoryname}">
                                                        selected="selected"
                                                    </c:if>
                                                    ></option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td class="rowAdmin">
                                    <input type="text" name="txtquantity" value="${dto.quantity}" />
                                </td>
                                <td class="rowAdmin">
                                    <c:set var="cbo" value="${sessionScope.STATUS}" />
                                    <select name="cboStatus">
                                        <c:set var="result" value="${param.cboStatus}" />
                                        <c:forEach var="cboName" items="${cbo}" varStatus="counter">

                                            <option label="${cboName.value}" value="${cboName.key}" 
                                                    <c:if test="${dto.status eq cboName.key}">
                                                        selected="selected"
                                                    </c:if>>
                                            </option>
                                        </c:forEach>
                                    </select><br>
                                </td>

                                <td class="rowAdmin">

                                    <input Onclick="return ConfirmDelete();" type="submit" value="Delete" name="btAction" />


                                </td>
                                <td class="rowAdmin">
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <div class="paging">
            <c:forEach begin="1" end="${ENDPAGE}" var="i">
                <a id="${i}" href="LoadDataServlet?txtIndex=${i}">${i}</a> 
            </c:forEach>
        </div>
        <script>
                 document.getElementById('${INDEX}').style.color = "red";
        </script>
        <script>
            function ConfirmDelete() {
                var x = confirm("Are you sure you want to delete?");
                if (x)
                    return true;
                else
                    return false;
            }
        </script>

    </body>

</html>