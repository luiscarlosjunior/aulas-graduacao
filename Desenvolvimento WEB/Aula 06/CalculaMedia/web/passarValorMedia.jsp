<%-- 
    Document   : passarValorMedia
    Created on : 28/04/2020, 17:58:44
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notas</title>
        <style> 
            p {
                color: blue;
                font-size: 26px;
            }
        </style>
    </head>
    <body>
        <h1>Inseria as notas!</h1>
        <form action="calculoMédia.jsp">
            <table>
                <th>
                    Exemplo
                </th>
                <td><p>Valor 1: <input type="text" id='not1' name='nota1'></p></td>
                <td><p>Valor 2: <input type="text" id='not2' name='nota2'></p></td>
                <td><p>Valor 3: <input type="text" id='not3' name='nota3'></p></td>
                <input type='submit' value='Enviar'/>
            </table>
        </form>
    </body>
</html>
