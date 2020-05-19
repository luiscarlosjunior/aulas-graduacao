<%-- 
    Document   : exibindousuario
    Created on : 18/05/2020, 11:01:09
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="excecao.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset=UTF-8">
        <title>Teste de Dados e Erro em JSP</title>
    </head>
    <body>
        <h3>Cadastro de Item com Tratamento - Com tratamento de Erro</h3>
        <%
            // Recebendo dados com método getParameter 
            // através do objeto implícito request:
            String nome = request.getParameter("nome");
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");
        %>
        <%-- Apresentação dos dados via HTML --%>
        <pre>
           ---------- DADOS OBTIDOS DO BROWSER ---------------------
           Nome:        <%=nome%>
           Usuário:     <%=usuario%>
           Senha:       <%=senha%>
        </pre>
    </body>
</html>
