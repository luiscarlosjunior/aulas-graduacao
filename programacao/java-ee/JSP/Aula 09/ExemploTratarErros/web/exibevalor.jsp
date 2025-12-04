<%-- 
    Document   : exibevalor
    Created on : 19/05/2020, 12:57:58
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="trataerror.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seu IMC</title>
        <style>
            .center {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1 class="center">Resultado do seu IMC</h1>
        <%
            float peso = Float.parseFloat(request.getParameter("peso"));
            float altura = Float.parseFloat(request.getParameter("altura"));
            
            float resultado = 0.0f; 
            
            resultado = (peso/(altura*altura));
                
            // Verificamos se o resultado nos retornou NaN (Não definido) 
            // ou isInfinite (Valor infinito)
            if(Float.isNaN(resultado) || Float.isInfinite(resultado) ) {
                throw new Exception();
            }
 
        %>
        <h2 class="center"> <%=resultado%> </h2>
    </body>
</html>
