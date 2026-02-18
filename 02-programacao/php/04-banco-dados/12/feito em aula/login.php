<?php
    
    require "conexao.php";

    $usuario = "adm";
    $senha = "123";

    // Fazendo a consulta
    $consulta = "
        SELECT NICKNAME FROM REGISTRO
            WHERE NOME = '$usuario' AND SENHA = '$senha'
    "; // sql_injection

    $resultado = mysqli_query($con, $consulta);

    if(mysqli_num_rows($resultado) > 0) {
        
        $linha = mysqli_fetch_assoc($resultado);

        echo PHP_EOL . "O nome do visitando é " . $linha['NICKNAME'];
    } else {
        echo " Houve erro ou não existe esse login ";
    }

?>
