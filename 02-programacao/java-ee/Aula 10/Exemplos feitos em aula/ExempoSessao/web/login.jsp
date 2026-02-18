<%-- 
    Document   : login
    Created on : 02/06/2020, 21:10:57
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Informe o seu acesso</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <style>
            /*https://developer.mozilla.org/en-US/docs/Web/CSS/justify-content*/
             /*Forma simpels de centralizar algum componente no meio da tela*/
            .flex-box {
               display: flex;
               align-items: center;
               justify-content: center;
             }

            /*
            .centraliza {
                position: absolute;
                left: 40%;
                top: 30%
            }
            */
            
            label {
                font-size: 22px;  
            }
            
            /*
                Modificamos dois determinados tipos de inputs para deixar a 
                aparência mais agradável.
                Border-radius: arredondadar as pontas;
                box-shadow: Cria sombra por volta do elemento
            */
            input[type=text], input[type=password]{   
                border-radius:4px;
                box-shadow: 2px 2px 4px #333333;    
                background: #cccccc; 
                border:1px solid #000000;
                width:100%;
                height: 30px;
                font-size: 18px;
            }
            
            /*
                Altera a area de texto internamento
            */
            textarea{
                border: 1px solid #AAFF00;
                background:#cccccc;
                border-radius:4px;
                box-shadow: 5px 5px 5px #333333; 
            }
            
            /*
             O uso do hover é para modificar alguma coisa quando o mouse passa por ela
            aqui estamos somente modificando o background
            */
            input[type=text]:hover, input[type=password]:hover, textarea:hover{ 
                     background: #ffffff; border:1px solid #990000;
            }
            
            input[type=submit]:hover {
                background:#556600;
            }
            
            input[type=submit]{
                width: 100px;
                height: 40px;
                background:#006699;
                color:#ffffff;
                font-weight: bold;
                border-radius:10px;
            }
        </style>
    </head>
    <body>
        
        <%
            if(session.getAttribute("login") == null) {
        %>
        
        <div class="flex-box">
            <form action="ADO">
                <h2>Entre com a sua conta</h2>
                <label for="user">Usuário:</label><br>
                <input type="text" id="user" name="login"><br><br>
                <label for="senha">Senha:</label><br>
                <input type="password" id="senha" name="senha">
                <br><br>
                <center>
                    <input style="margin-right: 10px;" type="submit" 
                           name="acao" value="login" >
                    <!-- Botão cancelar não está disponivel-->
                </center>
            </form>
        <%
         } 
            // Verificar se possui erros
            if(request.getParameter("erro") != null) {
        %>
           <div>
               <font color="red" style="font-size: 18px" > Login Inválido </font>
           </div>
        <%
         }
          //Se não houver erros, podemos pegar o usuário
         if(session.getAttribute("login") != null) {
        %>
            <div>
                <br>
                <form method="POST" action="ServletLogin">
                    <% out.print(session.getAttribute("login")); %>
                    <input type="submit" name="acao" value="logout">
                </form>
                
            </div>
        <%
        }
        %>
        </div>
        
    </body>
</html>
