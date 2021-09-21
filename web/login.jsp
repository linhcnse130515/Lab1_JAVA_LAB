<%-- 
    Document   : login
    Created on : Sep 8, 2021, 9:09:41 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN PAGE</title>
    </head>
    <link rel="stylesheet" href="CSS/Header.css" />
    <link rel="stylesheet" href="CSS/Footer.css" />
    <body>
        <header>
            
        </header>
        <nav>
            <div class="navi">
                <a href="home.jsp" a>HOME</a>
                <c:url var="search" value="MainController">
                    <c:param name="btnAction" value="Search"></c:param>
                </c:url>
                <a href="${search}">PRODUCTS</a>
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
        <div class="login"> 
            <form action="MainController" method="POST">
                User ID :<input type="text" name="txtUserId"></br>
                Password :<input type="password" name="txtPassword"></br>
                <input type="submit" name="btnAction" value="Login">
                <input type="reset" name="reset">
            </form>
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/YellowMoonShop/MainController?btnAction=LoginGmail&response_type=code
               &client_id=976519729204-b8sdfs891eeu4c18beajv7912qffd99p.apps.googleusercontent.com&approval_prompt=force">Login With Google</a></br>
            ${requestScope.MESSAGE}
        </div>  
    </body>
</html>
