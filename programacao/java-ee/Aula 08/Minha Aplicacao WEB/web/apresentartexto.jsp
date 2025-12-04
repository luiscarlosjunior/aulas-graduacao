<%-- 
    Document   : apresentartexto
    Created on : 12/05/2020, 13:09:33
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Vamos importar a classe java -->
<%@page import="com.meuapp.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String usuario = request.getParameter("user") == null ? "": request.getParameter("user");
            if(usuario.length() < 1) {
                usuario = "Visitante";
            }
        %>
        <h1>Seja bem-vindo, <% out.print(usuario);%> </h1>
        <h2> Informação de texto </h2>
        <%= retornarTexto.deixeMaiusculo("Um texto qualquer em maiusculo") %>
        <br>
        <%= retornarTexto.deixeMinusculo("Um texto qualquer em minusculo") %>      
    </body>
</html>
