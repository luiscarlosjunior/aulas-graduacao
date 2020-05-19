<%-- 
    Document   : calculadora
    Created on : 19/05/2020, 09:35:46
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
        <h1>Calculadora</h1>
        <div class="flex-box">
            <form action="validacao/validarlogin.jsp" method="POST">
                <h2>Entre com um calculo</h2>
                <label for="user">Usuário:</label><br>
                <input type="text" id="user" name="usuario"><br><br>
                <label for="senha">Senha:</label><br>
                <input type="password" id="senha" name="senha">
                <br><br>
                <center>
                    <input style="margin-right: 10px;" type="submit" 
                           value="Entrar" id="btnEnviar" name="btnEnviar" >
                    <!-- Botão cancelar não está disponivel-->
                    <input style="margin-right: 10px;" type="submit" 
                           value="Cadastrar" id="btnCadastro" name="btnCadastro" formaction="formscadastro.jsp" >
                </center>               
            </form>
            
        </div>
    </body>
</html>
