<html>
     <head>
     <title> Formulários com PHP</title>
     <style>
          .error {color: #FF0000;}
     </style>
     <?php
          // define variables and set to empty values
          $generoErr = $bandaErr= $infoErr = "";
          $genero = $banda = $info = "";
          if ($_SERVER["REQUEST_METHOD"] == "POST") {
               if (empty($_POST["rdo_genero"])) {
                    $generoErr = "Selecione um gênero musical";
               } else {
                    $genero = $_POST["rdo_genero"];
               }
               if (empty($_POST["txt_banda"])) {
                    $bandaErr = "Digite o nome de sua banda favorita";
               } else {
                    $banda = $_POST["txt_banda"];
               }
               if (empty($_POST["info_banda"])) {
                    $infoErr = "Digite informações sobre sua banda favorita";
               } else {
                    $info = $_POST["info_banda"];
               }
          }
     ?>
     </head>
     <body>
          <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
               <span class="error">* campo requerido</span>
               <p>Selecione um gênero musical:<span class="error">* <?php echo $generoErr;?></span>
                    <br>
                    <input type="radio" name="rdo_genero" value="Classica" /> Clássica<br>
                    <input type="radio" name="rdo_genero" value="MPB" /> MPB<br>
                    <input type="radio" name="rdo_genero" value="Pagode" /> Pagode<br>
                    <input type="radio" name="rdo_genero" value="Rock" /> Rock<br>
                    <input type="radio" name="rdo_genero" value="Samba" /> Samba<br>
                    <input type="radio" name="rdo_genero" value="Sertanejo" /> Sertanejo<br>
               </p>
               <p>Digite sua bnda favorita: <input type="text" name="txt_banda" /><span class="error">*<?php echo $bandaErr;?></span></p>
               <p>Informações sobre sua banda favorita:<br><textarea cols="50" rows="10"name="info_banda" /></textarea><span class="error">*<?php echo $infoErr;?></span></p>
               <p><input type="submit" value="Enviar" /></p>
          </form>
          <?php
               if (($_SERVER["REQUEST_METHOD"] == "POST") && (!empty($_POST["rdo_genero"]) && !empty($_POST["txt_banda"]) && !empty($_POST["info_banda"]))){ ?>
                    <p>Os dados enviados foram:
                              <?php
                                   echo "<h2>Your Input:</h2>";
                                   echo $genero;
                                   echo "<br>";
                                   echo $banda;
                                   echo "<br>";
                                   echo $info;
               }
                         ?>
                    </p>
     </body>
</html>