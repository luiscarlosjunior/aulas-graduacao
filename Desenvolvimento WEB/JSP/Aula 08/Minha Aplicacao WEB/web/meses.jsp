<%-- 
    Document   : meses
    Created on : 12/05/2020, 12:00:58
    Author     : luisc
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%
    String mes[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", 
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
   %> <select name="mes"> <%
    for(int i = 0; i < mes.length; i++) {
        %> <option value="<%=i+1%>" selected="selected"><%=mes[i]%></option>
    <%}%> 
    </select>
