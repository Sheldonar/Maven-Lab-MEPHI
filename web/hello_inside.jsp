<%--
  Created by IntelliJ IDEA.
  User: georg
  Date: 27.09.2019
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello_inside.jsp</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    if (cookies == null)
        response.sendRedirect("http://localhost:8080/LAB2_war_exploded/");
%>
<h3> Congratulations, you are able to calculate two numbers! </h3>
</body>
</html>
