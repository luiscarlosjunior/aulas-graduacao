<%-- 
    Document   : testeString
    Created on : 10/05/2020, 19:13:19
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="exemplo.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Seja bem-vindo <%= request.getParameter("user") %></h1>
        
        <h2> Informações sobre o servidor </h2>
        <%= Util.deixeMaiusculo("Meu nome não é Jonny") %>
        <%= Util.deixeMinusculo("Meu nome não é Jonny") %>
        <br>
        <%= request.getHeader("User-Agent") %>
        <br>
        <%= request.getLocale()%>
    </body>
</html>
