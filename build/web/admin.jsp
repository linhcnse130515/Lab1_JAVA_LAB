<%-- 
    Document   : admin
    Created on : Sep 15, 2021, 9:52:05 PM
    Author     : nguye
--%>
<%@page import="java.time.LocalDate"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN Page</title>
    </head>
    <link rel="stylesheet" href="CSS/Header.css" />
    <link rel="stylesheet" href="CSS/Article.css" />
    <link rel="stylesheet" href="CSS/Footer.css" />
    <body>
        <nav>
            <div class="navi">
                <a href="index.jsp" a>HOME</a>
                <c:url var="search" value="MainController">
                    <c:param name="btnAction" value="Search"></c:param>
                </c:url>
                <a href="${search}">PRODUCTS</a>
                <a href="tracking.jsp" a>TRACKING</a>
                <c:if test="${empty sessionScope.USER}">
                    <a href="login.jsp" a>LOGIN</a>
                    <c:redirect url="login.jsp"/>
                </c:if>
                <c:if test="${sessionScope.ROLE != 'Admin'}">
                    <a href="cart.jsp">Cart</a>
                </c:if>
                <c:if test="${not empty sessionScope.USER}">
                    <c:url var="user" value="MainController">
                        <c:if test="${sessionScope.ROLE == 'Admin'}">
                            <c:param name="btnAction" value="Admin"></c:param>
                        </c:if>
                        <c:if test="${sessionScope.ROLE == 'User'}">
                            <c:param name="btnAction" value="User"></c:param>
                            <c:redirect url="login.jsp"/>
                        </c:if>
                    </c:url>
                    <a href="${user}">${sessionScope.USER.fullName}</a>  
                    <c:url var="logout" value="MainController">
                        <c:param name="btnAction" value="Logout"></c:param>
                    </c:url>
                    <a href="${logout}">Logout</a>
                </c:if> 

            </div>    
        </nav>
        <div class="cart">
            <h1>
                Welcome: ${sessionScope.USER.fullName}
            </h1>
            <form action="MainController">
                ID*: <input type="text" name="txtId" value="" >${requestScope.ERRORSBOOK.getBookIDErr()}<br>
                Name*: <input type="text" name="txtName" value="" >${requestScope.ERRORSBOOK.getBookNameErr()}<br>
                Description : <input type="text" name="txtDescription" value="" ><br>
                Price : <input type="number" name="txtPrice" value="0" >${requestScope.ERRORSBOOK.getBookQuanErr()}<br>
                Create Date : <input type="date" name="txtCreateDate" value=""><br>
                Expiration Date: <input type="date" min="<%= LocalDate.now()%>" name="txtExpirationDate" value="" >${requestScope.ERRORSBOOK.getBookIDErr()}<br>
                Image: <input type="text" name="txtImage" value="" >${requestScope.ERRORSBOOK.getBookNameErr()}<br>
                Quantity : <input type="number" name="txtQuantity" value="" ><br>
                <select name="rsCategory">
                    <c:forEach items="${sessionScope.CATEGORIES}" var="category">
                        <option value="${category.id}" 
                                <c:if test="${param.rsCategory eq category.id}">selected</c:if>>
                            ${category.name}
                        </option>
                    </c:forEach>
                </select><br>
                <input type="submit" name="btnAction" value="New Cake" >
                <input type="reset" value="Reset" ><br>
            </form>
            <center>
                ${requestScope.MESSAGE}
                <c:if test="${requestScope.BOOKLIST != null}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Create Date</th>
                                <th>Expiration Date</th>
                                <th>Image</th>
                                <th>Quantity</th>
                                <th>Category</th>
                                <th>Status</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" varStatus="counter" items="${requestScope.BOOKLIST}">
                            <form action="MainController">  
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${list.cakesId}
                                        <input type="hidden" name="txtId" value="${list.cakesId}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtName" value="${list.name}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtDescription" value="${list.description}" />
                                    </td>
                                    <td>
                                        <input type="number" name="txtPrice" value="${list.price}" />
                                    </td>
                                    <td>
                                        <input type="date" name="txtCreateDate" value="${list.createDate}" />
                                    </td>
                                    <td>
                                        <input type="date" min="<%= LocalDate.now()%>" name="txtExpirationDate" value="${list.expirationDate}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtImage" value="${list.image}" />
                                    </td>
                                    <td>
                                        <input type="number" name="txtQuantity" value="${list.quantity}" />
                                    </td>
                                    <td>
                                        <select name="rsCategory">
                                            <c:forEach items="${sessionScope.CATEGORIES}" var="category">
                                                <option value="${category.id}" 
                                                        <c:if test="${list.category.id eq category.id}">selected</c:if>>
                                                    ${category.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="rsStatus">
                                            <c:forEach items="${requestScope.STATUSLIST}" var="status">
                                                <option value="${status.id}" 
                                                        <c:if test="${list.status.id eq status.id}">selected</c:if>>
                                                    ${status.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="submit" name="btnAction" value="Update Cake"/>
                                    </td>
                                </tr>
                            </form>    
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </center>    
            <c:set var="pages" value="${requestScope.PAGES}" />
            <nav style="margin-top: 20px; background-color: white;">
            <c:forEach var="i" begin="1" end="${pages}">
                <li <c:if test="${i == page}">active</c:if>">
                    <c:url var="urlRewritingPaging" value="MainController">
                        <c:param name="btnAction" value="Admin"/>
                        <c:param name="page" value="${i}"/>
                    </c:url>
                    <a href="${urlRewritingPaging}" class="page-link">${i}</a></li>
                </li>
            </c:forEach> 
            </nav>
        </div>
    </body>
</html>
