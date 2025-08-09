<%-- 
    Document   : validarimc
    Created on : 19/05/2020, 16:14:07
    Author     : luisc
--%>

<%@page import="util.Imc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    float peso = Float.parseFloat(request.getParameter("peso"));
    float altura = Float.parseFloat(request.getParameter("altura"));       
    
    Imc imc = new Imc();
    
    imc.setPeso(peso);
    imc.setAltura(altura);
%>
    <h1> <%= imc.retornarIMC() %> </h1>
    
