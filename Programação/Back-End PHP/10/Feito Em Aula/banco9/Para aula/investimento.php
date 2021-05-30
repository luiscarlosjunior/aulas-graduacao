<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cliente</title>

		<link rel="stylesheet" href="css/reset.css">
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
	</head>
	<body>
	<header>
			<div class="caixa">
				<h1>
					<a href="index.php">
						<img src="img/logobanknine.png" height="250px" width="400px" alt="Logo Banco Uninove">
					</a>
				</h1>
				<nav>
					<ul>
						<li><a href="index.html">Home</a></li>
						<li><a href="contacorrente.html">Abrir Conta</a></li>
						<li><a href="contato.html">Contato</a></li>
					</ul>
				</nav>
			</div>
		</header>
		<main>
			<form action="exibirConta.html" method="post">
				<p style="font-size: 26px;"><strong>Investir no futuro é pensar em você</strong></p> <br><br>

				<select class="input-padrao">
					<option value="TesouroDireto">Tesouro Direto</option>
					<option value="CDB">CDB</option>
					<option value="lcilca"> LCI e LCA</option>
					<option value="acoes">Ações de empresas</option>
					<option value="fundos-investimento">Fundos de Investimentos</option>
				</select>
				<br/>
				
				<label for="email">Email</label>
				<input type="email" id="email" name="email" class="input-padrao" required placeholder="seuemail@dominio.com">

				<label for="cpf">CPF</label>
				<input type="tel" id="cpf" name="cpf" class="input-padrao" required placeholder="XXX.XXX.XXX-XX">

				<label for="deposita">Quanto quer investir?</label>
				<input type="number" id="depositar" name="deposita" class="input-padrao" required placeholder="R$ 1000.00">

				<label for="investimento">Tempo de investimento</label>
				<select class="form-select" aria-label="Default select example">
					<option selected>Open this select menu</option>
					<option value="1">One</option>
					<option value="2">Two</option>
					<option value="3">Three</option>
				</select>
				
				<label class="checkbox"><input type="checkbox" checked>Gostaria de receber nossas novidades por email?</label>
				<input type="submit" value="Enviar formulário" class="enviar">
			</form>
		</main>

		<footer>
			<p class="copyright">&copy; Copyright Bank-U9 - 2021</p>
		</footer>
	</body>
</html>