<%-- 
    Document   : inicial
    Created on : 12/05/2020, 20:22:16
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Exemplo de Site</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- Acrescenta um icone no endereço -->
        <link rel="shortcut icon" type="image/x-icon" href="ico/favicon.ico">
        
        <link href="css/style.css" rel="stylesheet" type="text/css">
        
        <style>
            /* Fixa o elemento no canto esquerdo */
            .canto {
                position: fixed;
                right: 10px;
            }
        </style>
        
    </head>
    <body>
        
        <div>
            <nav id="menu">
                <ul>
                    <li><a href="#">Página inicial</a></li>
                    <li><a href="#">Sobre</a></li>
                    <li><a href="#">Membros</a></li>
                    <li class="canto" style="text-align:center;">
                        <a href="login.html">Login</a>
                    </li>
                </ul>
             </nav>
        </div>
        
        <hr>
        <%
            String usuario = request.getParameter("user") == null ? "": request.getParameter("user");
            if(usuario.length() < 1) {
                usuario = "Visitante";
            }
        %>
        <h1 class="texto-centralizado">Seja bem-vindo, <% out.print(usuario);%> </h1>
    </body>
</html>
