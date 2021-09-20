<%-- 
    Document   : cart
    Created on : Sep 14, 2021, 2:09:22 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <div class="navi">
                <a href="index.jsp" a>HOME</a>
                <a href="products.jsp">PRODUCTS</a>
                <a href="tracking.jsp" a>TRACKING</a>
                <c:if test="${empty sessionScope.USER}">
                    <a href="login.jsp" a>LOGIN</a>
                </c:if>
                <c:if test="${not empty sessionScope.USER}">
                    <c:url var="user" value="MainController">
                        <c:if test="${sessionScope.ROLE == 'Admin'}">
                            <c:param name="btnAction" value="Admin"></c:param>
                        </c:if>
                        <c:if test="${sessionScope.ROLE == 'User'}">
                            <c:param name="btnAction" value="User"></c:param>
                        </c:if>
                    </c:url>
                    <a href="${user}">${sessionScope.USER.fullName}</a>  
                    <c:if test="${sessionScope.ROLE == 'User'}">
                        <a href="cart.jsp">Cart</a>
                    </c:if>
                    <c:url var="logout" value="MainController">
                        <c:param name="btnAction" value="Logout"></c:param>
                    </c:url>
                    <a href="${logout}">Logout</a>
                </c:if> 
            </div>    
        </nav>
        <c:set var="listCart" value="${sessionScope.CART}"/>
        <c:if test="${not empty listCart}">
            <form action="MainController">
                <table>
                    <thead>
                        <tr>
                            <th style="width: 52%;">Name</th>
                            <th style="width: 10%;">Amount</th>
                            <th style="width: 8%;">Price</th>
                            <th style="width: 10%;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cake" items="${listCart.cart}">
                        <form action="MainController">
                            <c:set var="cakeValue" value="${cake.value}"/>
                            <tr>
                                <input type="hidden" name="txtCakeId" value="${cakeValue.cakesId}" />
                                <td style="width: 20%;">${cakeValue.name} </td>
                                <td style="width: 10%;">
                                    <input type="number" name="txtQuantity" value="${cakeValue.quantity}" />
                                </td>
                                <td style="width: 10%;">${cakeValue.price * cakeValue.quantity}</td>
                                <td style="width: 10%;">
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <input type="hidden" name="txtName" value="${cakeValue.name}" />
                                                    <input type="hidden" name="txtPrice" value="${cakeValue.price}" />
                                                    <input type="hidden" name="txtOldQuantity" value="${cakeValue.quantity}" />
                                                    <input type="submit" value="Update" name="btnAction" />
                                                </td>
                                                <td>
                                                    <input type="submit" value="Remove" name="btnAction" onclick="return confirm('Are your sure?');"/>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </form>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </c:if>
        <c:if test="${not empty sessionScope.TOTAL}">
            TOTAL: ${sessionScope.TOTAL}<br>
        </c:if>
        <input type="text" name="txtAddress" value="" />
        <c:url var="checkOut" value="MainController">
            <c:param name="txtAddress" value="${param.txtAddress}"></c:param>
            <c:param name="btnAction" value="Check Out"></c:param>
        </c:url>
        <a href="${checkOut}">Check out</a>
        <c:url var="checkOutPaypal" value="MainController">
            <c:param name="txtAddress" value="${param.txtAddress}"></c:param>
            <c:param name="btnAction" value="Check Out Paypal"></c:param>
        </c:url>
        <a href="${checkOutPaypal}">Check out by Paypal</a>
        <c:if test="${not empty requestScope.ORDERID}">
            Your Order Id: ${requestScope.ORDERID}
        </c:if>
    </body>
</html>
