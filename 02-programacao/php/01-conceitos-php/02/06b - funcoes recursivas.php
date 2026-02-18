<?php

// Nota: Chamadas recursivas a funções/métodos acima de 100-200 
// níveis podem exaurir a pilha e causar o término do script. 
// Especificamente, recursão infinita é considerada um erro de programação.

function recursion($a)
{
    if ($a < 20) {
        echo "$a\n";
        recursion($a + 1);
    }
}
?>