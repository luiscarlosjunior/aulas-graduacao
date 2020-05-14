<%-- 
    Document   : helloJSP
    Created on : 28/04/2020, 09:48:59
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% out.print("Olá, tudo bem?"); %>
        <% 
            String strNome = "Nome recebido: " + request.getParameter("nome");
            out.print(strNome);
        %>
        <%-- Usando expressões --%>
        <%=
            "Nome recebido usando expressões: " + request.getParameter("nome")
         %>
    </body>
</html>
