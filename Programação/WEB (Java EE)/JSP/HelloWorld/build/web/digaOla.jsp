<%-- 
    Document   : digaOla
    Created on : 28/04/2020, 20:19:19
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
        <h1>Olá!</h1>
        <%= 
          // out.print("Meu nome é " + request.getParameter("nome"));
          "Meu nome é " + request.getParameter("nome")
        %>        
    </body>
</html>
