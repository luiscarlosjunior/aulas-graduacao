<%-- 
    Document   : validarIdade
    Created on : 05/05/2020, 20:58:59
    Author     : luisc
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Validar idade</h1>
        
        <%
            final int idadeMinima = 18;
            
            // Pego os valores informados pelo usuário na página anterior (index)
            int dia =  Integer.parseInt(request.getParameter("dia"));
            int mes =  Integer.parseInt(request.getParameter("mes"));
            int ano =  Integer.parseInt(request.getParameter("ano"));
            
            // Criar um instância da classe calendario
            Calendar calendar = Calendar.getInstance();
            // Pego o dia, mês e ano atuais
            int diaAtual = calendar.get(Calendar.DAY_OF_MONTH + 1);
            int mesAtual = calendar.get(Calendar.MONTH);
            int anoAtual = calendar.get(Calendar.YEAR);
                  
            int diferenca = anoAtual - ano;
            
            // 05/05/2020
            // 04/05/2002
            
            // Verificação da idade
            if(diferenca > idadeMinima) {
                response.sendRedirect("inicio.html");
            } else if (diferenca == idadeMinima) {
                if (mes < mesAtual) {
                    response.sendRedirect("inicio.html");
                } else if (mes == mesAtual) {
                    response.sendRedirect("inicio.html");
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        %>
        
    </body>
</html>
