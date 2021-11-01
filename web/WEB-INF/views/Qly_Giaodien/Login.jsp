<%-- 
    Document   : Login
    Created on : Jan 15, 2020, 3:27:17 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
        <base href="${pageContext.servletContext.contextPath}/">
        <title>login.htm</title>
    </head>
    <body>
        <h1>LOGIN!</h1>
        <form action="login_form.htm" method="post">
            ${message}
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="user" value="" required></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="pwd" value="" required></td>
                </tr>
                <tr>
                    <td><button>Login</button></td>
                    <td><input type="reset" name="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
