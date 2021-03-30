<%-- 
    Document   : index
    Created on : 19/05/2020, 12:57:36
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css"/>
        <title>Cálculo IMC</title>
    </head>
    <body>
            <h1 style="text-align: center;">Faça seu IMC</h1>
            <div class="flex-box">
                <form action="util/validarimc.jsp" method="POST">
                    <label for="peso">Informe seu peso</label><br>
                    <input type="text" id="peso" name="peso" ><br>
                    <label for="altura">Informe sua altura</label><br>
                    <input type="text" id="altura" name="altura" ><br>
                    <center>
                        <input style="margin-right: 20px; margin-top: 20px;" 
                               type="submit" value="Entrar" id="btnEnviar" name="btnEnviar" >
                    </center>    
                </form>
                <br>
            </div>
    </body>
</html>
