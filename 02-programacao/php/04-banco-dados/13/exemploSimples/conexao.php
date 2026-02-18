<?php

    // Declarando as variáveis de acesso
    $nome_bd = "usuario";
    $usuario_bd = "root";
    $senha_bd = "";
    $nome_servidor = "localhost";

    // String de conexão (Não é necessário informar a porta, só informa caso seja alterada manualamente)
    //$con = mysqli_connect($nome_servidor, $usuario_bd, $senha_bd, $nome_bd, 3303);

    $con = mysqli_connect($nome_servidor, $usuario_bd, $senha_bd, $nome_bd);

    // Verificar se houver conexão
    if($con) {
        echo "Conexão feita com sucesso";
    } else {
        echo "Erro na conexão ".mysqli_connect_error();
    }

?>