<?php

    $idades = [10, 12, 15, 16, 23, 25, 27, 15]; 

    //echo count($idade);

    for($i = 0; $i < count($idade); $i += 2)
    {
        echo "$idade[$i] \t";
    }

    foreach($idades as $idade)
    {
        echo $idade . PHP_EOL;
    }

?>