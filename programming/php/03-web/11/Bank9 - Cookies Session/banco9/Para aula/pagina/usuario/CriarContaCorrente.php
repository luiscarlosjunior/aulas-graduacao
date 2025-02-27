<?php

require_once __DIR__ . '/src/Modelo/Correntista/Titular.php';
require_once __DIR__ . '/src/Modelo/Correntista/CPF.php';
require_once __DIR__ . '/src/Modelo/Conta/Conta.php';
require_once __DIR__ . '/src/Modelo/Conta/ContaCorrente.php';
require_once 'rodape.php';

use Modelo\Conta\ContaCorrente;
use Modelo\Correntista\{Titular, CPF};

$nome = $_POST['nome'];
$cpf = $_POST['cpf'];
$deposita = $_POST['deposita'];

if(strlen($nome) === 0 || strlen($cpf) === 0)
{
    exit();
}

    $cliente = new Titular(new CPF($cpf), $nome);
    $contaCorrente = new ContaCorrente();

?>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Página inicial - B-9</title>
		<link rel = "shortcut icon" type = "imagem/x-icon" href = "img/ico.png"/>
		<link rel="stylesheet" href="css/reset.css">
		<link rel="stylesheet" href="css/style.css">
		<link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
	</head>
	<body>
		<header>
			<div class="caixa">
				<h1><img src="img/logobanknine.png" height="250px" width="400px" alt="Logo Banco Uninove"></h1>
				<nav>
					<ul>
						<li><a href="index.php">Home</a></li>
						<li><a href="inclusaotitular.php">Abrir Conta</a></li>
						<li><a href="contato.html">Contato</a></li>
					</ul>
				</nav>
			</div>
		</header>

		<main>
			<h1>Bem-vindo, <?= $cliente->recuperaNome(); ?> ao Bank9</h1>
		</main>

		<footer>
			<p class="copyright">&copy; <?php echo direitos(); ?></p>
		</footer>
	</body>
</html>