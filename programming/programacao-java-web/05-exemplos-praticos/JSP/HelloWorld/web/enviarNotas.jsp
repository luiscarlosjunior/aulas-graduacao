<%-- 
    Document   : enviarNotas
    Created on : 28/04/2020, 20:36:01
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enviar nota</title>
        <style>
            p {
              color:blue;
              font-size: 26px;
            }
        </style>
        
    </head>
    <body>
        <h1>Enviar nota</h1>
        
        <form action="calcularMedia.jsp">
            <p> Nota 1: <input type="text" id="valor1" name="nota1"> </p>
            <p> Nota 2: <input type="text" id="valor2" name="nota2"> </p>
            <p> Nota 3: <input type="text" id="valor3" name="nota3"> </p>
            <input type="submit" id="btnEnviar">
        </form>
        
    </body>
</html>
