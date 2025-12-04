<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Pagina do Prof. Luis</title>
  </head>
  <body>
    <h3>
    </h3>
		<div id="myMenu" align="left" style="border: solid; border-color:darksalmon">
			<a href="#">HOME</a> |
			<a href="#">CONTATO</a> |
			<a href="#">SOBRE</a> |
			<a href="#">MAPA DO SITE</a>
		</div>
		<form action="Principal.php" method="POST">
            <label for="nome">Informe seu nome</label>
            <input type="text" name="nome">
<br>
            <label for="rg">Informe seu RG</label>
            <input type="number" name="rg">
<br>
            <label for="cartao">Informe seu número Cartao</label>
            <input type="password" name="cartao">

            <input type="submit" value="Enviar dados">
        </form>
  </body>
</html>