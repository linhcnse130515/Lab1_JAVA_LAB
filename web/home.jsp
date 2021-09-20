<%-- 
    Document   : home
    Created on : Sep 8, 2021, 9:52:19 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME PAGE</title>
    </head>
    <link rel="stylesheet" href="CSS/Header.css" />
    <link rel="stylesheet" href="CSS/Article.css" />
    <link rel="stylesheet" href="CSS/Footer.css" />
    <body>
        <header>
        
        </header>
        <nav>
            <div class="navi">
                <a href="home.jsp" a>HOME</a>
                <a href="products.jsp">PRODUCTS</a>
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
        <article>  
            <div class="articleUp">
                <h1>
                    WELCOME
                </h1>
            </div>
           
        </article>      
    </body>
</html>
