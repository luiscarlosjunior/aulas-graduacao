<?php
	require_once 'rodape.php';
?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Página inicial - B-9</title>
		<link rel = "shortcut icon" type = "imagem/x-icon" href = "img/ico.png"/>
		<link rel="stylesheet" href="css/reset.css">
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
	</head>
	<body>
		<header>
			<div>
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">
					<a class="navbar-brand" href="#"><img src="img/logobanknine.png" height="150px" width="250px" alt="Logo Banco Uninove"></a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item">
						<a class="nav-link active" aria-current="page" href="#">Home</a>
						</li>
						<li class="nav-item">
						<a class="nav-link" href="#">Link</a>
						</li>
						<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Dropdown
						</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#">Action</a></li>
							<li><a class="dropdown-item" href="#">Another action</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">Something else here</a></li>
						</ul>
						</li>
						<li class="nav-item">
						<a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
						</li>
					</ul>
					<form class="d-flex">
						<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form>
					</div>
				</div>
				</nav>
			</div>
		</header>

		<main>
			<h1>Bem-vindo ao Bank9</h1>

			<ul class="produtos">
				<li>
					<h2>Conta Corrente</h2>
					<a href="inclusaotitular.php">
						<img src="img/abrirconta.png" height="100%" width="100%">
					</a>
					<p class="servico-banco">Abra sua conta corrente</p>
				</li>
				<li>
					<h2>Investimento</h2>
					<a href="investimento.html">
						<img src="img/investimento.png" height="100%" width="100%">
					</a>
					<p class="servico-banco">Invista em você</p>
				</li>
				<li>
					<h2>Poupança</h2>
					<a href="CriarContaPoupanca.php">
						<img src="img/poupanca.png" height="100%" width="100%"
					</a>
					<p class="servico-banco">Invista em você</p>
				</li>
			</ul>
			<section class="vantagens">
				<h3 class="titulo-principal">Vantagens da Bank9</h3>

				<div class="vantagens-beneficios">
					<ul class="lista-beneficios">
						<li class="itens">Atendimento aos Clientes</li>
						<li class="itens">Soluções Digitais</li>
						<li class="itens">Localização</li>
						<li class="itens">Profissionais Qualificados</li>
						<li class="itens">Pontualidade</li>
						<li class="itens">Burocracia reduzida</li>
					</ul><img src="img/contasalario.png" class="imagem-beneficios">
				</div>
			</section>

			<section class="mapa">
				<h3 class="titulo-principal">Nosso estabelecimento</h3>
				<p>Estamos próximos ao metrô e da Av. Paulista.</p>

				<div class="mapa-conteudo">
					<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3657.121176520404!2d-46.63818459478047!3d-23.564091021861564!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce5bc51374e6ad%3A0xde82450ea5cf3c27!2sUNINOVE%20-%20Campus%20Vergueiro!5e0!3m2!1spt-BR!2sbr!4v1621449504461!5m2!1spt-BR!2sbr" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
				</div>
			</section>
		</main>

		<footer>
			<p class="copyright">&copy; <?php echo direitos(); ?></p>
		</footer>
	</body>
</html>