<?php
    require_once 'Pessoa.php';
    require_once 'PessoaFisica.php';
    require_once 'Funcionario.php';

    $func1 = new Funcionario();

    $func1->setNome("Luis Caparroz");
    $func1->setRg("123456789");
    $func1->setCartao("123654");
?>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Pagina do Prof. Luis</title>
  </head>
  <body>
    <h3>
      <a href="https://github.com/luiscarlosjunior/aulas-graduacao/tree/master/Programa%C3%A7%C3%A3o/Back-End%20PHP"> Repositório Github </a>
    </h3>
		<div id="myMenu" align="left" style="border: solid; border-color:darksalmon">
			<a href="#">HOME</a> |
			<a href="#">CONTATO</a> |
			<a href="#">SOBRE</a> |
			<a href="#">MAPA DO SITE</a>
		</div>
		<div id="my_content" align="center" bgcolor="white">
            <h1 style="color:black;font-family:Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif"> Meu nome é <?= $func1->getNome();?> </h1>
            <h2 style=""> Meu RG é <?= $func1->getRg();?> </h2>
		</div>
  </body>
</html>