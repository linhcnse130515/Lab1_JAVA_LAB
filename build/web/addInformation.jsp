<%-- 
    Document   : addInfomation
    Created on : Sep 17, 2021, 8:50:53 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Information Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="MainController">
                Phone Number*: <input type="text" name="txtPhone" value="" ><br>
                Name*: <input type="text" name="txtName" value="" ><br>
                Address* : <input type="text" name="txtAddress" value="" ><br>
                <input type="submit" name="btnAction" value="Check Out" >
                <input type="reset" value="Reset" ><br>
            </form>
    </body>
</html>
