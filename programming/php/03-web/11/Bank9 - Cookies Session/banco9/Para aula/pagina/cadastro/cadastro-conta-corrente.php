<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cliente</title>

		<link rel="stylesheet" href="../../css/reset.css">
		<link rel="stylesheet" href="../../css/style.css">
	</head>
	<body>
	<header>
			<div class="caixa">
			<a href="index.php">
				<h1>
					<a href="../../index.php">
						<img src="../../img/logobanknine.png" 
							height="250px" width="400px" 
							alt="Logo Banco Uninove"/> 
					</a>
				</h1>
				</a>
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
			<form action="CriarContaCorrente.php" method="post">
				<p style="font-size: 26px;"><strong>Bem-vindo ao Bank-9. Faça sua conta</strong></p> <br><br>

				<label for="nomesobrenome">Nome e sobrenome</label>
				<input type="text" id="nomesobrenome" name="nome" class="input-padrao" required>

				<label for="email">Email</label>
				<input type="email" id="email" name="email" class="input-padrao" required placeholder="seuemail@dominio.com">

				<label for="cpf">CPF</label>
				<input type="tel" id="cpf" name="cpf" class="input-padrao" required placeholder="XXX.XXX.XXX-XX">

				<label for="deposita">Depositar</label>
				<input type="number" id="depositar" name="deposita" class="input-padrao" required placeholder="R$ 1000.00">

				<label class="checkbox"><input type="checkbox" checked>Gostaria de receber nossas novidades por email?</label>
				<input type="submit" value="Enviar formulário" class="enviar">
			</form>
		</main>

		<footer>
			<p class="copyright">&copy; Copyright Bank-U9 - 2021</p>
		</footer>
	</body>
</html>