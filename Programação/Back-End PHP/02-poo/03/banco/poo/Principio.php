<?php
    
    require 'Cliente.php';

    $cliente1 = 
        new Cliente("123456798", "Hugo Juca", "41417888", "Rua Bela Vista");
    $cliente1->conta = new ContaPoupanca(1, 123, 10);
    
    $cliente1->VisualizarRelatorio();

?>