<?php

    // Inicializando sessão
    session_cache_expire(2);
    session_start();

    // Criando uma sessão chamado nome
    $_SESSION['nome'] = "Nome qualquer";
    echo "<h1> Olá, " . $_SESSION['nome'] .  " seja bem-vindo </h1>";
    // Apagando a sessão
    unset($_SESSION['nome']);

    // Apagando todas as sessões
    //session_destroy();

    echo "<h1> Olá, " . $_SESSION['nome'] .  " seja bem-vindo </h1>";
?>
