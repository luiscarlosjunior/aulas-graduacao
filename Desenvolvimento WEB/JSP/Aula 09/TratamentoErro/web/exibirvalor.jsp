<%-- 
    Document   : exibirvalor
    Created on : 19/05/2020, 20:20:28
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="erropadrao.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exibir IMC</title>
    </head>
    <body>
        wrap classes
        <%
            float peso = Float.parseFloat(request.getParameter("peso"));
            float altura = Float.parseFloat(request.getParameter("altura"));
            
            float resultado = 0.0f;
            
            resultado = (peso/(altura*altura));
            
            //Verifica os resultados retornados
            if(Float.isInfinite(resultado) || Float.isNaN(resultado) ) {
                // Lanço uma exceção
                throw new Exception();
            }

        %>      
        <h2>O seu IMC é de <%= resultado%></h2>
        <form id="voltar" >
            <input type="button" onclick="history.go(-1)" value="voltar" />
        </form>
    </body>
</html>
