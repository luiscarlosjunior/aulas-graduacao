<%-- 
    Document   : comment
    Created on : 28/04/2020, 10:32:43
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exemplo de comentários JSP</title>
    </head>
    <body>
        <h1>Comentários JSP</h1>
        <table border='1' bordecolor='black'>
            <tr>
                <th>Exemplos</th>
                <th>Descrição</th>
            </tr>
            <tr>
                <td><% out.print("< !-- ... -- >"); %></td>
                <td><%= "Comentário copiado para a saída"%></td>
            </tr>
            <tr>
                <td><% out.print("< !-- ... -- >"); %></td>
                <td rowspan = "3"><%= "Comentário copiado para o código do servlet gerado"%></td>            
            </tr>
            <tr>
                <td><% out.print("< %!/* ... */% >"); %></td>
            </tr>
            <tr>
                <td><% out.print("< %// ... % >"); %></td>
            </tr>
            <tr>
                <td><% out.print("< %-- ... --% >"); %></td>
                <td><%= "Comentário JSP. Não é copiado nem para a saída, nem para o servlet" %></td>
            </tr>            
        </table>
    </body>
</html>
