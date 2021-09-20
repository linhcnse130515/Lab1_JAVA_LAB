<%-- 
    Document   : products
    Created on : Sep 9, 2021, 9:06:17 PM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PRODUCT PAGE</title>
    </head>
    <link rel="stylesheet" href="CSS/Header.css" />
    <link rel="stylesheet" href="CSS/ArticleBooks.css" />
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
        <div class="rooms">
            <h1>Welcome : ${sessionScope.USER.fullName}</h1> 
            <form action="MainController">
                Search by name: <input type="search" name="searchName" value="${requestScope.SEARCHNAME}"/>
                <select name="rsCategory">
                    <c:forEach items="${sessionScope.CATEGORIES}" var="category">
                        <option value="${category.name}" 
                                <c:if test="${param.rsCategory eq category.name}">selected</c:if>>
                            ${category.name}
                        </option>
                    </c:forEach>
                </select>
                Money from: <input type="number" name="from" value="${requestScope.FROMMONEY}"/>
                to: <input type="number" name="to" value="${requestScope.TOMONEY}"/>
                <input type="submit" name="btnAction" value="Search"/>
            </form>
                
            ${requestScope.MESSAGE} 
            ${requestScope.MESSAGECAKE}
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
                            Remain: ${list.quantity} </br> 

                            Amount<input type="number" min="1" value="1" name="txtAmount"/>
                            <input type="hidden" name="searchName" value="${requestScope.SEARCHNAME}"/>
                            <input type="hidden" name="rsCategory" value="${requestScope.SEARCHCATEGORY}"/>
                            <input type="hidden" name="from" value="${requestScope.FROMMONEY}"/>
                            <input type="hidden" name="to" value="${requestScope.TOMONEY}"/>
                            <input type="hidden" name="CakeString" value="${list.cakesId}-${list.name}-${list.price}-${list.quantity}"/>
                            <input type="submit" name="btnAction" value="Add to Cart"/>

                        </div>
                    </form>
                </div>
            </c:forEach>     
        </div> 
            <c:set var="pages" value="${requestScope.PAGES}" />
            <nav style="margin-top: 20px; background-color: white;">
            <c:forEach var="i" begin="1" end="${pages}">
                <li <c:if test="${i == page}">active</c:if>">
                    <c:url var="urlRewritingPaging" value="MainController">
                        <c:param name="searchName" value="${param.searchName}"/>
                        <c:param name="rsCategory" value="${param.rsCategory}"/>
                        <c:param name="from" value="${param.from}"/>
                        <c:param name="to" value="${param.to}"/>
                        <c:param name="btnAction" value="Search"/>
                        <c:param name="page" value="${i}"/>
                    </c:url>
                    <a href="${urlRewritingPaging}" class="page-link">${i}</a></li>
                </li>
            </c:forEach> 
            </nav>
    </body>
</html>
