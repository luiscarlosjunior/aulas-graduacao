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
        <h1>Hello World!</h1>
        <%= Util.deixeMaiusculo("Meu nome não é Jonny") %>
        <%= Util.deixeMinusculo("Meu nome não é Jonny") %>
        
        <%= request.getHeader("User-Agent") %>
        <%= request.getLocale()%>
    </body>
</html>
