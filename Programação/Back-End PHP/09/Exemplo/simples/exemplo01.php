<html>
     <head>
     <title> Formulários com PHP</title>
     <style>
          .error {color: #FF0000;}
     </style>
    <?php 
        // Definir variável para pegar os dados
        $nome = $idade = ""; 

        if($_SERVER["REQUEST_METHOD"] == "POST")
        {
            $nome = $_POST["nome"];
            $idade = $_POST["idade"];
        }
    ?>

     </head>
     <body>

        <form method="POST">
            <h2>Informe seu nome e sobrenome</h2>
            <input type="text" name="nome" placeholder="Informe seu nome"><br>
            <input type="number" name="idade" placeholder="Informe sua idade"><br>

            <input type="submit" value="Enviar dados">
        </form>
        
        <?php 
        
        echo "<h2> Meu nome é: " . $nome . "</h2>";
        echo "<h2> Minha idade é: " . $idade . "</h2>"
        
        ?>

    </body>
</html>