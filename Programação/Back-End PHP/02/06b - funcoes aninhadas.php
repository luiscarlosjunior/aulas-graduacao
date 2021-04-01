<?php

// Todas as funções e classes no PHP tem escopo global - 
// elas podem ser chamadas fora de uma função mesmo que 
//tenham sido definidas dentro e vice-versa.

// O PHP não suporta sobrecarga de funções, 
// e também não é possível cancelar ou alterar a 
// definição de funções previamente declaradas.

function foo()
{
  function bar()
  {
    echo "Eu não existo até foo() ser chamada.\n";
  }
}

/* Nós não podemos chamar bar() ainda
   porque ela ainda não foi definida. */

foo();

/* Agora nós podemos chamar bar(),
   porque o processamento de foo()
   tornou a primeira acessivel */

bar();

?>