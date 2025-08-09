<%-- 
    Document   : erropadrao
    Created on : 19/05/2020, 20:20:47
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teste de dados e erro em JSP</title>
        <style>
            #erro {color:red; }
            body {text-align: center}
        </style>
    </head>
    <body>
        <h2>Problemas encontrados!</h2>
        <hr>
        <div id="erro">
            <h3>Ocorreu uma ação inesperada no sistema</h3>
            <h4>Entre em contato com o suporte técnico</h4>
        </div>
        <hr>
        <form id="voltar" >
            <input type="button" onclick="history.go(-1)" value="voltar" />
        </form>
    </body>
</html>

