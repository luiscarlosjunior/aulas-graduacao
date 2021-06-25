<?php

    // Importa a classe php
    require_once "conexao.php";

    // Pego os valores que foram enviados
    $nome = $_GET['nome'];
    $usuario = $_GET['usuario'];
    $senha = $_GET['senha'];
    $email = $_GET['email'];

    // Faço o meu insert
    $sql_insert = " 
        INSERT INTO ACESSO (nome, usuario, senha, email) 
            VALUES ('$nome', '$usuario', '$senha', '$email');
        ";

    if(mysqli_query($con, $sql_insert)) {
        // TODO
        echo "Cadastrado com sucesso";
    } else {
        echo "Erro ao registrar dados ".mysqli_error($con);
    }

?>