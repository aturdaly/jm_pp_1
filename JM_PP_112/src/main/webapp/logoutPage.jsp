<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout Page</title>
</head>
<body>
<%
    Long id = (Long)session.getAttribute("id");
    String role = (String)session.getAttribute("role");
%>
<form action="logout" method="post">
    <table border="1">
        <tr>
            <td>Id:</td>
            <td><%=id%></td>
        </tr>
        <tr>
            <td>Role:</td>
            <td><%=role%></td>
        </tr>
    </table>
    <input type="submit" value="Logout"/>
</form>
</body>
</html>
