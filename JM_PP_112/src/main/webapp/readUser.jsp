<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<h2>Users List:</h2>
<table border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Age</td>
        <td>Role</td>
    </tr>

    <%--<jsp:useBean id="allUsers" scope="request" type="java.util.List"/>
    <c:forEach var="user" items="${allUsers}">--%>
        <%ArrayList<User> userList =
            (ArrayList<User>)request.getAttribute("allUsers");
        for(User user:userList){%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getAge()%></td>
        <td><%=user.getRole()%></td>

        <td>
            <a href="/admin/delete?id=<%=user.getId()%>" methods="get">
                <input type="submit" name="id" value="Delete" style="float:left">
            </a>
            <a href="/admin/update?id=<%=user.getId()%>" methods="get">
                <input type="submit" name="id" value="Update" style="float:left">
            </a>
        </td>
    </tr>
        <%}%>
    <%--</c:forEach>--%>

    <br/><br/>
    <div>
        Input New User:
    </div>

    <form action="/admin/add" method="post">
        <table style="width: 25%">
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="text" name="password" /></td>
            </tr>
            <tr>
                <td>Age</td>
                <td><input type="number" name="age" /></td>
            </tr>
            <tr>
                <td>Role</td>
                <td><input type="text" name="role" /></td>
            </tr></table>
        <input type="submit" value="Add" />
    </form>

</body>
</html>