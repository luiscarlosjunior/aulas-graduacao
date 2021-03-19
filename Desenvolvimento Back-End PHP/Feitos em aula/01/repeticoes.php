<?php 

    $contador = 11;

    while($contador <= 10)
    {
        echo "Nome de vocês - $contador" . PHP_EOL;
        $contador += 1;
    }
    
    for($contador2 = 1; $contador2 < 10; $contador2++)
    {
        echo "Nome de vocês - $contador2" . PHP_EOL;
    }


    $contador3 = 1;
    do
    {
        echo "Nome de vocês - $contador3" . PHP_EOL;
        $contador3++;
    } while($contador3 <= 10);


?>