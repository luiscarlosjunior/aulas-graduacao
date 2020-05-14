<%-- 
    Document   : calculoMédia
    Created on : 28/04/2020, 17:51:47
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
        <h1>Cálculo da média</h1>
        <%! 
            // Exemplo de Declaração de um método que calcula a média:
            private float calcMedia(float n1, float n2, float n3) {
                float m;
                m = (n1 + n2 + n3) / 3;
                return m;
            }
        %>
        <%
            // Exemplo de três variáveis de classe:
            // O F colocado após os valores, indica que esses valores são Float.
            float n1 = Float.parseFloat(request.getParameter("nota1"));
            float n2 = Float.parseFloat(request.getParameter("nota2"));
            float n3 = Float.parseFloat(request.getParameter("nota3"));
        %>
        
        <h3>A média deu: <%=calcMedia(n1, n2, n3)%></h3>
    </body>
</html>
