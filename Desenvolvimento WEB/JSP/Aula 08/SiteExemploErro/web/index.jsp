<%-- 
    Document   : index
    Created on : 12/05/2020, 19:46:59
    Author     : luisc
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
    <head>
        <title>Meu exemplo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <style>
            
            .fixed-box {
                display: flex;
                align-items: center;
                justify-content: center;
            } 
            
            /*
            .centralizar {
                position: absolute;
                left: 50%;
                top: 50%
            }
            */
        </style>
        
    </head>
    <body>
        <div>Minha página</div>
        
        <a href="exemploLaco.jsp"> Exemplo laço </a>
        <div class="fixed-box">
          <form accept-charset="utf-8" action="validacao/validarIdade.jsp">            
            <div>
                
              <%-- Usar javascript --%>
                
              <jsp:include page="util/meses.jsp" />

              <select name="dia">
              <option value="1" selected="selected">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
              <option value="13">13</option>
              <option value="14">14</option>
              <option value="15">15</option>
              <option value="16">16</option>
              <option value="17">17</option>
              <option value="18">18</option>
              <option value="19">19</option>
              <option value="20">20</option>
              <option value="21">21</option>
              <option value="22">22</option>
              <option value="23">23</option>
              <option value="24">24</option>
              <option value="25">25</option>
              <option value="26">26</option>
              <option value="27">27</option>
              <option value="28">28</option>
              <option value="29">29</option>
              <option value="30">30</option>
              <option value="31">31</option>
            </select>

              <jsp:include page="util/anos.jsp" />
            </div>
            <br>
            
            <button class="">ENVIAR</button>
      </form>
      </div>
    </body>
</html>

