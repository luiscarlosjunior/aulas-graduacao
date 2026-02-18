<%-- 
    Document   : util
    Created on : 10/05/2020, 20:40:01
    Author     : luisc
--%>

<%-- 
    Aqui criamos um laço para preencher um select com os meses
--%>

<%-- Aqui importamos uma classe util --%>
    <%
    String mes[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", 
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
   %> <select name="mes"> <%
    for(int i = 0; i < mes.length; i++) {
        %> <option value="<%=i+1%>" selected="selected"><%=mes[i]%></option>
    <%}%> 
    </select>


