<?php
     $nome = "Luis Carlos"; // htmlspecialchars(strip_tags($_POST['nome']));
     $texto = "Olá, tudo bem?"; // htmlspecialchars(strip_tags($_POST['texto']));
     $email = "carlos.santos1912@gmail.com"; // htmlspecialchars(strip_tags($_POST['email']));
     $refresh = "<meta http-equiv=\"refresh\" content=\"1; url=form.html\" />";

     /*if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
          echo "<script type="text/javascript">alert("E-mail inválido!.")</script>';
          exit ($refresh);
     }
     elseif (!filter_var($email, FILTER_SANITIZE_EMAIL)){
          echo "<script type=\text/javascript\">alert(\"E-mail inválido!. Contém caracteres não permitidos.\")</script>";
          exit ($refresh);
     }*/

     $msg = "<strong>Nome:</strong> $nome<br>";
     $msg .= "<strong>E-mail:</strong> $email<br>";
     $msg .= "<strong>Mensagem:</strong> $texto<br>";
     $destinatario = "carlos.santos1912@gmail.com";
     $assunto = "Contato Website";
     $header = "MIME-Version: 1.0\r\n";
     $header .= "Content-Type: text/html; charset=iso-8859-1\r\n";
     $header .= "From: $email\r\n";
     $header .= "X-Priority: 1\r\n";
     //$header .= "cc: alguem2@algumlugar.com.br\r\n";
     //$header .= "Bcc: alguem3@algumlugar.com.br\r\n";
     $header .= "Reply-To: $email\r\n";
     if (mail ($destinatario, $assunto, $msg, $header)){
          echo "E-mail enviado com sucesso!";
          exit ($refresh);
     } else {
          echo "Problema no envio da mensagem. Por favor tente mais tarde.";
          exit ($refresh);
     }
?>