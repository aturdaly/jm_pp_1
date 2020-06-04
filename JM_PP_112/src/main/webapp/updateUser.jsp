<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update User:</title>
</head>
<body>
<%@page import="model.User, service.UserService" %>
<%
    long id = Long.parseLong(request.getParameter("id"));
    User user = UserService.getInstance().getUserById(id);
%>

<form action="/admin/update" method="post">
    <input type="hidden" name="id" value="<%= id%>"/>
    <table>
        <tr>
            <td>Id:</td>
            <td><%= user.getId()%></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="<%= user.getName()%>"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text" name="password" value="<%= user.getPassword()%>"/></td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><input type="number" name="age" value="<%= user.getAge()%>"/></td>
        </tr>
        <tr>
            <td>Role:</td>
            <td><input type="text" name="role" value="<%= user.getRole()%>"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Update"/></td>
        </tr>
    </table>
</form>

</body>
</html>