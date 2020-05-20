<%-- 
    Document   : validarimc
    Created on : 19/05/2020, 21:15:25
    Author     : luisc
--%>

<%@page import="util.Converter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exibir IMC</title>
    </head>
    <body>
        <%
            // Recebo os valores da página calc-imc.jsp
            float peso = Float.parseFloat(request.getParameter("peso"));
            float altura = Float.parseFloat(request.getParameter("altura"));
            
            // Crio um instância do objeto
            Converter converter = new Converter();
            
            // Defino os valores
            converter.setPeso(peso);    
            converter.setAltura(altura);
        %>

        <h1> O seu imc é de <%= converter.retornarIMC() %> </h1>
        <form id="voltar" >
            <input type="button" onclick="history.go(-1)" value="voltar" />
        </form>
    </body>
</html>