<?php
    spl_autoload_register(function (string $nomeCompletoCaminhoClasse){
        $nomeCompletoCaminhoClasse = str_replace('Uninove\\Banco', 'src', $nomeCompletoCaminhoClasse);
        $nomeCompletoCaminhoClasse = str_replace('\\', DIRECTORY_SEPARATOR, $nomeCompletoCaminhoClasse);
        //$nomeCompletoCaminhoClasse = $nomeCompletoCaminhoClasse . '.php';
        $nomeCompletoCaminhoClasse .= '.php'; 

        if(file_exists($nomeCompletoCaminhoClasse) ) {
            require_once $nomeCompletoCaminhoClasse;
        } else {
            echo 'Não foi possível encontrar o arquivo';
            exit();
        }
    });

?>