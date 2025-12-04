<%-- 
    Document   : login.jsp
    Created on : 26/05/2020, 09:12:52
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            if (session.getAttribute("login") == null) {
        %>
        <form name="frmLogin" method="post" action="ServletLogin">
           <table border=1>
                <caption>Login:</caption>
                <tr>
                    <td>Usuário:</td>
                    <td><input type="text" name="login" size="10" /></td>
                </tr>
                <tr>
                    <td>Senha:</td>
                    <td><input type="password" name="senha" size="10" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Entrar" /></td>
                </tr>
           </table>
            <input type="hidden" name="acao" value="login" />
        </form>
        <% }
            // Verificamos se não há erros
            if (request.getParameter("erro") != null) {
        %>
        <div>
            <font color="red"> Login Inválido!!! - Tente novamente.</font>
        </div>
         
        <% }
            // Se não houver erros, podemos pegar o usuário cadastrado na sessão
            if (session.getAttribute("login") != null) {
        %>
        <div>
            <br><br>
           <form name="frmLogout" method="post" action="ServletLogin">
                <input type="hidden" name="acao" value="logout" />
                Usuario Atual: 
                   <% out.print(session.getAttribute("login")); %> !
                <br><br>
                <input type="submit" value="Logout" />
            </form>
        </div>
        <% } %>
    </body>
</html>
