<%-- 
    Document   : util
    Created on : 10/05/2020, 20:40:01
    Author     : luisc
--%>
<%@page import="java.util.*"%>
    <%
    String mes[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", 
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
   %> <select name="mes"> <%
    for(int i = 11; i >= 0; --i) {
        %> <option value="<%=i+1%>" selected="selected"><%=mes[i]%></option>
    <%}%> 
    </select>


