<%-- 
    Document   : calcularMedia
    Created on : 28/04/2020, 20:35:03
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calcula a média</title>
    </head>
    <body>
        <h1>O resultado</h1>
        <%!
            // Faz o cálculo da média
            private float calcMed(float n1, float n2, float n3) 
            {    
                float resultado;
                resultado = (n1 + n2 + n3) / 3;
                return resultado;
            }
        %>
         <% 
            // Exemplo de três variáveis
            // Declarei e inicializei as variáveis
            float valor1 = Float.parseFloat(request.getParameter("nota1"));
            float valor2 = Float.parseFloat(request.getParameter("nota2"));
            float valor3 = Float.parseFloat(request.getParameter("nota3"));
         %>
         <h3> A média é de: <%= calcMed(valor1, valor2, valor3) %> </h3>
         
    </body>
</html>
