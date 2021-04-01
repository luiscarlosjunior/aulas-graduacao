<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exibir cookies</title>
    </head>
    <body>
        <h1>Exibindo informações armazenadas</h1>
        
        <%
        
         // Declaro um objeto do tipo (classe) Cookie e é inicializado como null
         Cookie cookie = null;
         
         // Declaro uma coleçao do tipo (classe) Cookie e é inicializado como null
         Cookie[] cookies = null;
         
         // Obtem todos os cookies associados a este domínio
         cookies = request.getCookies();
         
         if(cookies != null) {
             
             out.println("<h2> Foram encontrados valores </h2>");
             
             for (int i = 0; i < cookies.length; i++) {
                 cookie = cookies[i];
                 
                 // Excluindo um cookie
                 if (cookie.getName().equals("pNome")){
                     cookie.setMaxAge(0);
                     // Adicionar "atualizar" o cookie
                     response.addCookie(cookie);
                     out.print("Cookie apagado: " + cookie.getName( ) + "<br/>");
                 }
                 
                 out.print("Nome: " + cookie.getName() + ", ");
                 out.print("Valor: " + cookie.getValue() + "<br>");
             }
             
             /*
             for (int i = 0; i < cookies.length; i++) {
                 out.print("Nome: " + cookies[i].getName() + ", ");
                 out.print("Valor: " + cookies[i].getValue() + "<br>");
             }
             
             for(Cookie c : cookies) {
                 out.print("Nome: " + c.getName() + ", ");
                 out.print("Valor: " + c.getValue() + "<br>");
             }
             */
             
         } else {
             out.println("<h2> Não foram encontrados valores! </h2>");
         }

        %>
        
    </body>
</html>
