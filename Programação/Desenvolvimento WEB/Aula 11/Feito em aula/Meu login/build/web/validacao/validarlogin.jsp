<%-- 
    Document   : validarlogin
    Created on : 12/05/2020, 20:45:16
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.uninove.meulogin.util.VerificarLogin" %>
<%
    // Receber o usuário e a senha
    String usuario = request.getParameter("usuario");
    String senha = request.getParameter("senha");
    
    // Criar instância da classe (objeto)
    // Fazer referência a classe
    VerificarLogin login = new VerificarLogin();
    
    // Passo por parâmetro as informações
    login.setUsuario(usuario);
    login.setSenha(senha);

    boolean permitido = login.permissao();

    if(permitido) {
        response.sendRedirect("../inicial.jsp?user=" + login.getUsuario());
    }
    else
    {
        response.sendRedirect("../inicial.jsp");
    }

%>

