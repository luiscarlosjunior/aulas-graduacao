<?php

/*
As funções não precisam ser criadas antes de serem referenciadas, 
exceto quando uma função é condicionalmente definida como mostrado 
nos dois exemplos abaixo. Quando uma função é definida condicionalmente 
como nos dois exemplos abaixo, sua definição precisa ser processada 
antes de ser chamada.
*/

$makefoo = true;

/* Nos nao podemos chamar foo() daqui
   porque ela ainda não existe,
   mas nos podemos chamar bar() */

bar();

if ($makefoo) {
  function foo()
  {
    echo "Eu não existo até que o programa passe por aqui.\n";
  }
}

/* Agora nos podemos chamar foo()
   porque $makefoo foi avaliado como true */

if ($makefoo) foo();

function bar()
{
  echo "Eu existo imediatamente desde o programa começar.\n";
}

?>