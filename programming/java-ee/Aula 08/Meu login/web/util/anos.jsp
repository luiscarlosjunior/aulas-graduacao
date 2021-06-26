<%-- 
    Document   : anos
    Created on : 12/05/2020, 21:38:52
    Author     : luisc
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar.*" %>
<select name="ano">
    
    <%
        int anoMax, anoMin;
        // Definindo um objeto calendario
        Calendar ca = Calendar.getInstance();
        
        anoMax = ca.get(Calendar.YEAR);
        anoMin = (anoMax - 150);
                
        for(int i = anoMax; i >= anoMin; i--) { %>
            <option value="<%=i%>"> <%=i%> </option>
    <%}%>
</select>
