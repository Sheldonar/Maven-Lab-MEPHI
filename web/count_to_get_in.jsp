<%@ page import="java.nio.file.Files" %>
<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: georg
  Date: 27.09.2019
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>count_to_get_in.jsp</title>
</head>
<style>
    body{
        background: dimgray;
    }
    H1 {
        font-size: 120%;
        font-family: Arial, Helvetica, sans-serif;
        color: white;
    }
</style>
<body>

<h1> TRY TO COUNT SUM </h1>
<br>

<%--<%
    try {
        int a = (int) request.getAttribute("a");
        int b = (int) request.getAttribute("b");
        out.println(a + " + " + b + " = ?");
    }
    catch (NullPointerException e) {
            response.sendRedirect("http://localhost:8080/LAB2_war_exploded/");
            out.println("  ");
    }
    catch (IOException e1) {
        response.sendRedirect("http://localhost:8080/LAB2_war_exploded/");
        out.println("  ");
    }
%>--%>


${a} + ${b} = ?



<form method="POST">
your answer:
    <input type="number" name="answer" />
    <input type="submit" name="submit" value="Submit"/>

    <input type="hidden" name="hash" value="${hash}" />
</form>

</body>
</html>
