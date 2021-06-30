<?php

$idade = 16;
$quantidadePessoas = 1;

//qualquer coisa != 0 = true

if($idade >= 18) 
{
    echo "Você pode entrar no parque";
}       
else if(($idade >= 16)  && ($quantidadePessoas >= 2))
{
    echo "Você pode entrar no parque";
} 
else
{
    echo "Você não pode entrar no parque";
}

echo PHP_EOL;
echo "Adeus!";
