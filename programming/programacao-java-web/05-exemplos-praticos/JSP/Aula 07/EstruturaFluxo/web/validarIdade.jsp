<%-- 
    Document   : validarIdade
    Created on : 05/05/2020, 09:34:52
    Author     : luisc
--%>

<%@page import="com.sun.xml.internal.ws.util.HandlerAnnotationProcessor"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            final int idadeMinima = 18;
            int dia = Integer.parseInt(request.getParameter("dia")); 
            int mes = Integer.parseInt(request.getParameter("mes")); 
            int ano = Integer.parseInt(request.getParameter("ano")); 
                       
            // Crio uma instancia para pegar a data
            Calendar calendar = Calendar.getInstance();
            int mesAtual = calendar.get(Calendar.MONTH);
            int anoAtual = calendar.get(Calendar.YEAR);
            int diaAtual = calendar.get(Calendar.DAY_OF_MONTH);
            
            // Pego a diferença de idade
            int diferenca = anoAtual - ano;
            
            // Verifico se a idade é maior que 18
            if(diferenca > idadeMinima) {
                 response.sendRedirect("Inicial.html");
            }
            else if (diferenca == idadeMinima) {
                if(mes < mesAtual) {
                     response.sendRedirect("Inicial.html");
                } else if (mes == mesAtual && dia < diaAtual) {
                     response.sendRedirect("Inicial.html");
                } else {
                     response.sendRedirect("Inicial.html");
                }       
            }
            else
            {
                 response.sendRedirect("index.html");
            }
            
        %>
        
    </body>
</html>
