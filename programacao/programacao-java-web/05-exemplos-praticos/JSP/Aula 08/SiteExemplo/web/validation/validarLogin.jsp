<%-- 
    Document   : validarLogin
    Created on : 11/05/2020, 10:31:15
    Author     : luisc
--%>

<%@page import="exemplo.util.VerificarLogin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="exemplo.util.VerificarLogin.*"%>
<%
    // Recebeo o usuário e a senha
    String usuario = request.getParameter("usuario"); 
    String senha = request.getParameter("senha"); 
    
    // Cria uma instância da classe VerificarLogin
    VerificarLogin userLicense = new VerificarLogin();

    // Passa por parâmetro as informações nescessárias
    userLicense.setUsuario(usuario);
    userLicense.setSenha(senha);
    
    // Chama o método que está fazendo a validação
    if (userLicense.permissao()) {
        response.sendRedirect("../telainicial.jsp?user=" + userLicense.getUsuario());
    }
    else {
        response.sendRedirect("../index.jsp");
    }
%>
