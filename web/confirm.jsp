<%-- 
    Document   : confirm
    Created on : Jan 23, 2021, 3:08:49 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/main.css">
        <title>Confirm information</title>
    </head>
    <body>
        <div class="backgoundCreateItem">
        </div>
        <div class="createPage">
            <div class="createForm">
                <form action="DispatchServlet">
                    Customname: <input id="txtTextfile" type="text" name="txtCustomer" value="" /><br>
                    Address: <input id="txtTextfile" type="text" name="txtAddress" value="" /><br>
                    Phonenumber: <input id="txtTextfile" type="text" name="txtPhonenumber" value="" /><br>
                    <div class="buttonCreate"> 
                        <input id="buttonLogin" type="submit" value="CheckOut" name="btAction" />
                        
                        <script
                            src="https://www.paypal.com/sdk/js?client-id=Ab0sIAJdAAlM6MOUnwzS_zLNlKoFVl7B-wUNK0cuG9srZQqoiM8vVt-fwZBB9iKekqyPPfxLJPJOge1N"> // Required. Replace SB_CLIENT_ID with your sandbox client ID.
                        </script>
                        <script src="js/paypal.js" ></script>
                        <div id="paypal-button-container">
                        </div>
                        
                        <input type="hidden" name="txtItemID" value="${param.txtItemID}" />
                        <input type="hidden" name="quantity" value="${param.quantity}" />
                        <input type="hidden" name="price" value="${param.price}" />
                        <input type="hidden" name="total" value="${param.total}" />
                    </div>
                </form>
            </div>
        </div>

        <!--                        <form action="ConfirmItemServlet" method="POST">                 
                                   
        
                                </form>-->
    </body>
</html>
