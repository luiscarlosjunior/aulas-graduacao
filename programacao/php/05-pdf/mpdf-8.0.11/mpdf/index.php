<?php
     $html = '
          <h1>Página de Exemplo</h1>
          <p>Exportanto código HTML para PDF utilizando a função mPDF()</p>
     ' ;
     include("src/mpdf.php");
     $mpdf=new mPDF();
     $mpdf->WriteHTML($html);
     $mpdf->Output();
     exit;
?>