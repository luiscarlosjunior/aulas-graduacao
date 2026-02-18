
<%@ page session = "false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <title>Lendo Cookies</title>
   </head>
   
   <body>
      <center>
         <h1>Lendo Cookies</h1>
      </center>
      <%
         Cookie cookie = null;
         Cookie[] cookies = null;
         
         // Obtenha uma variedade de cookies associados a este domínio
         cookies = request.getCookies();
         
         // Verificando se há cookies
         if( cookies != null ) {
            out.println("<h2> Nome e valores encontrado</h2>");
            
            for (int i = 0; i < cookies.length; i++) {
               cookie = cookies[i];
               
               if((cookie.getName()).compareTo("pNome") == 0 ) {
                  cookie.setMaxAge(0);
                  response.addCookie(cookie);
                  out.print("Cookie apagado: " + 
                  cookie.getName( ) + "<br/>");
               }
               // Exibe os valore os valores em respectivos cookies
               out.print("Nome : " + cookie.getName( ) + ",  ");
               out.print("Valor: " + cookie.getValue( )+" <br/>");
            }
         } else {
            out.println(
            "<h2>Sem cookie encontrados</h2>");
         }
      %>
   </body>
   
</html>