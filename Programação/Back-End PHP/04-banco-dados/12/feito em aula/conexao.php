<?php

    // Variáveis de acesso ao banco
    $servidor_db = "localhost";
    $nome_db = "banknine";
    $usuario_db = "root";
    $senha_db = "";
    
    $con = mysqli_connect($servidor_db, $usuario_db, $senha_db, $nome_db, 3303);

    if($con) {
        echo "Conexão feita com sucesso";        
    }
    else {
        echo "Erro ao realizar a conexao com o banco de dados " . mysqli_connect_error(); 
    }

?>