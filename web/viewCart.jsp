<%-- Document : viewCart Created on : Jan 21, 2021, 10:15:17 PM Author : Administrator --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/main.css">
        <title>YourCart</title>
    </head>

    <body>
        <div class="imageHanaShop">

        </div>
        <h1>Your Cart</h1>
        <c:set var="cart" value="${sessionScope.CUSTCART}" />
        <c:if test="${not empty cart.items}">
            <div class="displayAdmin">
                <table border="1" class="tableAdmin">
                    <thead>
                        <tr>
                            <th class="columnAdmin">No.</th>
                            <th class="columnAdmin">Itemname</th>
                            <th class="columnAdmin">Quantity</th>
                            <th class="columnAdmin">price</th>
                            <th class="columnAdmin">total</th>
                            <th class="columnAdmin">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="DispatchServlet">
                        <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                            <tr>
                                <td class="rowAdmin">
                                    ${counter.count}
                                </td>
                                <td class="rowAdmin">
                                    ${entry.key.itemname}
                                    <input type="hidden" name="txtItemID" value="${entry.key.itemID}" />
                                </td>
                                <td class="rowAdmin">
                                    <span class="increasequantity" onclick="decrease('${entry.key.itemID}')" style="cursor: pointer;">-</span>
                                    <span  class="quantity" id="quantity${entry.key.itemID}" >${entry.value}</span>
                                    <input type="hidden" name="quantity" value="${entry.value}" id="quan${entry.key.itemID}"/>
                                    <span class="creasequantity" onclick="increase('${entry.key.itemID}')" style="cursor: pointer;">+</span>
                                </td>
                                <td class="rowAdmin">
                                    <span id="price${entry.key.itemID}">${entry.key.price}</span>
                                    <input id="price" type="hidden" name="price" value="${entry.key.price}"/>
                                </td>
                                <td class="rowAdmin">
                                  <span id="total${entry.key.itemID}">${entry.key.price * entry.value}</span>
                                    <input type="hidden" name="total" value="${entry.key.price * entry.value}" id="totalAll${entry.key.itemID}" />
                                </td>
                                <td class="rowAdmin">
                                    <input type="checkbox" name="chkItem" value="${entry.key.itemID}" />
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
              
                              <td colspan="5" class="rowAdmin">
                                <c:set var="tong" value="0"/>
                                <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                                    <c:set var="tong" value="${tong + (entry.key.price * entry.value)}"/>
                                </c:forEach>
                                TotalAll: <span id="totalBill">${tong}</span>
                                <input id="BillAll" type="hidden" name="totalAll" value="${tong}" />
                                
 
                           </td>
                            <td class="rowAdmin"> 
                                <input Onclick="return ConfirmDelete();" type="submit"
                                       value="RemoveSelectedBooks" name="btAction" />
                            </td>
                        </tr>

                        <input type="submit" value="Confirm" name="btAction" />
                      
                           
                            <font color="red">
                           ${requestScope.ERRQUANTITY}
                            </font><br>
                        
                    </form>
                    </tbody>
                </table>
            </div>

        </c:if>
        <c:if test="${empty cart.items}">
            <h2>No cart exists</h2>
            <a href="PrintDataServlet">Add more books to your cart</a>
        </c:if>
        <script>
            function increase(id) {
                var x = document.getElementById("quantity" + id).innerHTML;
                x++;
                document.getElementById("quantity" + id).innerHTML = x;
              
                var y = document.getElementById("price" + id).innerHTML;

                var z = x * y;

                document.getElementById("total" + id).innerHTML = z;
                document.getElementById("quan" + id).value = x;
                document.getElementById("totalAll" + id).value = z;

                var totalBill = document.getElementById("totalBill").innerHTML;
                totalBill = parseFloat(totalBill);
                y = parseFloat(y);
                var totalAll = totalBill + y;
                document.getElementById("totalBill").innerHTML = totalAll;
                document.getElementById("BillAll").value = totalAll;

                console.log(z);
            }
            function decrease(id) {
                var x = document.getElementById("quantity" + id).innerHTML;
                x--;
                if (x < 1) {
                    return;
                }
                document.getElementById("quantity" + id).innerHTML = x;
                var y = document.getElementById("price" + id).innerHTML;

                var z = x * y;

                document.getElementById("total" + id).innerHTML = z;
                document.getElementById("quan" + id).value = x;
                document.getElementById("totalAll" + id).value = z;
                var totalBill = document.getElementById("totalBill").innerHTML;
                totalBill = parseFloat(totalBill);
                y = parseFloat(y);
                var totalAll = totalBill - y;
                document.getElementById("totalBill").innerHTML = totalAll;
                document.getElementById("BillAll").value = totalAll;

                console.log(z);
            }
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