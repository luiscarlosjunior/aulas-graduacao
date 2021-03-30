<%-- 
    Document   : criandocookie
    Created on : 02/06/2020, 20:06:58
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //Criar dois cookies para o nome e sobrenome
    Cookie primeiro_nome = new Cookie("pNome",request.getParameter("primeiroNome"));
    Cookie ultimo_nome = new Cookie("uNome", request.getParameter("ultimoNome"));
    
    //Determinar o tempo de vida
    primeiro_nome.setMaxAge(60*60*24);
    ultimo_nome.setMaxAge(60*60*24);
    
    //Adicionar os cookies no cabeçalho HTTP
    response.addCookie(primeiro_nome);    
    response.addCookie(ultimo_nome);

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Criando cookies</title>
    </head>
    <body>
        <center>
            <h1>Os nomes são</h1>
            <br>
            <p>Primeiro nome é <%= request.getParameter("primeiroNome") %> </p>
            <br>
            <p>Segundo nome é <%= request.getParameter("ultimoNome") %> </p>
            <a href="exibircookie.jsp">Clique aqui</a>
            
        </center>
           
    </body>
</html>
