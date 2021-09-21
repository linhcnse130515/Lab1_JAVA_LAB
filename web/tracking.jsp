<%-- 
    Document   : tracking
    Created on : Sep 19, 2021, 8:01:30 PM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tracking Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.USER}">
            <c:redirect url="login.jsp"/>
        </c:if> 
        <nav>
            <div class="navi">
                <a href="home.jsp" a>HOME</a>
                <c:url var="search" value="MainController">
                    <c:param name="btnAction" value="Search"></c:param>
                </c:url>
                <a href="${search}">PRODUCTS</a>
                <a href="tracking.jsp" a>TRACKING</a>
                <c:if test="${empty sessionScope.USER}">
                    <a href="cart.jsp">Cart</a>
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
        <h1>TRACKING ORDER</h1>
        <form action="MainController">
            Order ID: <input type="search" name="searchOrder" value=""/>
            <input type="submit" name="btnAction" value="Tracking"/>
        </form>
        <c:if test="${not empty requestScope.ORDER}">
            Order Id: ${requestScope.ORDER.orderId} <br>
            User Name: ${requestScope.ORDER.userName} <br>
            Order Date: ${requestScope.ORDER.orderDate} <br>
            Shipping Address: ${requestScope.ORDER.shippingAddress} <br>
            Payment Method: ${requestScope.ORDER.paymentMethod} <br>
            Payment Status: ${requestScope.ORDER.paymentStatus} <br>
        </c:if>
        <c:if test="${not empty requestScope.LIST}">
        <c:forEach var="list" items="${requestScope.LIST}">
            <div class="productImg">
                <form action="MainController">
                    <img src="${list.image}" />                     
                    <div class="product">                        
                        <h2> ${list.name} </h2>
                        ${list.description} </br>
                        Price: ${list.price} </br>
                        Create Date: ${list.createDate} </br>
                        Expiration Date: ${list.expirationDate} </br>
                        Category: ${list.category.name} </br>
                        Amount ${list.quantity} </br> 

                    </div>
                </form>
            </div>
        </c:forEach> 
        </c:if>
    </body>
</html>
