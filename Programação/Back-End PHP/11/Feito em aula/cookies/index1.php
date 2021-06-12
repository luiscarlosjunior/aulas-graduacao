<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meu site</title>
</head>
<body>
    <?php
        if($_SERVER["REQUEST_METHOD"] == "POST")
        {
            //echo "Recebi o seu requerimento";
            setcookie('timeCoracao', 'Sem time', 3600);
            $timeCoracao = $_POST["time"];
            echo "<h1>O time do seu coração é " . $timeCoracao . "</h1>";
        }
    ?>

    <form action="<?php $_SERVER["PHP_SELF"]?>" method="post">
        <h2> Escolha o seu time de coração </h2>
        <input type="radio" name="time" value="Palmeiras">Palmeiras</input> </br>
        <input type="radio" name="time" value="São Paulo">São Paulo</input> </br>
        <input type="radio" name="time" value="Corinthians">Corinthians</input> </br>
        <input type="radio" name="time" value="Santos">Santos</input> </br>
        <input type="submit" value="Enviar"/>
    </form>
</body>
</html>