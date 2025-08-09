<%-- 
    Document   : meses
    Created on : 12/05/2020, 19:53:09
    Author     : luisc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
    "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
%>
<select name="mes">
    
    <%
        for(int i = 0; i < meses.length; i++) {
    %>
            <option value="<%=i+1%>"> <%=meses[i]%> </option>
    <%
        }
    %>
</select>
