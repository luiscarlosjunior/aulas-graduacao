<?php

    // Importa a classe php
    require "conexao.php";

    // Pego os valores que foram enviados
    
    $usuario = "admin";//$_GET['usuario'];
    $senha = "123";//$_GET['senha'];

    // Faço o meu insert
    $sql_query = " 
        SELECT NOME FROM REGISTRO 
	        WHERE usuario = '$usuario' AND senha = '$senha';
        ";

    // Fazemos a consulta e pegamos se houve retorna de linhas
    $resultado = mysqli_query($con, $sql_query);

    if(mysqli_num_rows($resultado) > 0) {

        $linha = mysqli_fetch_assoc($resultado);
        
        // Pego a coluna da consulta realizada
        $nome = $linha['NOME'];
        
        // Exibo o nome
        echo $nome;

    } else {
        echo "Erro ao registrar dados ".mysqli_error($con);
    }

?>