<?php

$idadeList = [21, 23, 19, 25, 30, 41, 18];

//list($idadeVinicius, $idadeJoao, $idadeMaria) = $idadeList;

$idadeList[] = 20;

/*
for ($i=0; $i < count($idadeList); $i++) { 
    echo ($idadeList[$i]) . PHP_EOL;
}
*/

foreach($idadeList as $idade){
    echo $idade . PHP_EOL;
}

foreach ($idadeList as $idade) {
    echo $idade . PHP_EOL;
}
