<%-- 
    Document   : criandoCookie
    Created on : 25/05/2020, 18:31:20
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   // Cria alguns cookies para o nome e o sobrenome.      
   Cookie primeiro_nome = new Cookie("pNome", request.getParameter("primeiroNome"));
   Cookie ultimo_nome = new Cookie("uNome", request.getParameter("ultimoNome"));
   
   // Definir data de validade após 24 horas para os dois cookies.
   primeiro_nome.setMaxAge(60*60*24); 
   ultimo_nome.setMaxAge(60*60*24); 
   
   // Adiciona os dois cookies no cabeçalho da resposta.
   response.addCookie(primeiro_nome);
   response.addCookie(ultimo_nome);
%>

<html>
   <head>
      <title>Configurando Cookies</title>
   </head>
   
   <body>
      <center>
         <h1>Criando Cookies</h1>
      </center>
      <ul>
         <li><p><b>Primeio nome:</b>
            <%= request.getParameter("primeiroNome")%>
         </p></li>
         <li><p><b>Último nome:</b>
            <%= request.getParameter("ultimoNome")%>
         </p></li>
      </ul>
   
   </body>
</html>
