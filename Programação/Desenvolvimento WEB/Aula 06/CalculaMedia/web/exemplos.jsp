<%-- 
    Document   : exemplos
    Created on : 28/04/2020, 10:25:40
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
        <h1>Sintaxe dos elementos JSP</h1>
        <h2>Todos são interpretados no servidor</h2>
        <ul style='font-size: 30px'>
            <li>Diretivas: <% out.print("< %@ ... % >"); %> </li>
            <li>Declarações: <% out.print("< %! ... % >"); %> </li>
            <li>Expressões: <% out.print("< %= ... % >"); %> </li>
            <li>Scriptlets: <% out.print("< % ... % >"); %> </li>
            <li>Comentários: <% out.print("< %-- ... --% >"); %> </li>
        </ul>
    </body>
</html>
