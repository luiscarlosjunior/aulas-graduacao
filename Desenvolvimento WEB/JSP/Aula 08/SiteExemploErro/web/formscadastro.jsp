<%-- 
    Document   : formscadastro
    Created on : 18/05/2020, 10:46:37
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="tratamentoerror/excecao.jsp" autoFlush="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .flex-box {
               display: flex;
               align-items: center;
               justify-content: center;
             }
             input[type=text], input[type=password], p {
                 border-radius: 1px;
                 box-shadow: 1px 1px 1px #333333;
                 width: 100%;
             }
             
             input[type=submit]:hover, input[type=reset]:hover {
                 background: #EDEDED;
             }
             
        </style>
    </head>
       <body>
        <h3>Cadastro de Item com Tratamento - Exemplo</h3>
        <div id="frm">
            <div class="flex-box">
                <form name="frmItens" action="exibindousuari.jsp" method="post">
                    <h2>Cadastro usuário</h2>    
                    <hr>
                    <label for="nome">Nome:</label><br>
                    <input type="text" id="nome" name="nome"><br><br>
                    <label for="user">Usuário:</label><br>
                    <input type="text" id="user" name="usuario"><br><br>
                    <label for="senha">Senha:</label><br>
                    <input type="password" id="senha" name="senha"><br><br>
                    <center>
                        <input type="submit" value="Incluir" name="btnincluir"> 
                        <input type="reset" value="Limpar">
                    </center>
                </form>
            </div>                
        </div>
    </body>
</html>
