<?php
abstract class ClasseAbstrata
{
    // Força a classe que estende ClasseAbstrata a definir esse método
    abstract protected function pegarValor();
    abstract protected function valorComPrefixo( $prefixo );

    // Método comum
    public function imprimir() {
        print $this->pegarValor();
    }
}

class ClasseConcreta1 extends ClasseAbstrata
{
    protected function pegarValor() {
        return "ClasseConcreta1";
    }

    public function valorComPrefixo( $prefixo ) {
        return "{$prefixo}ClasseConcreta1";
    }
}

class ClasseConcreta2 extends ClasseAbstrata
{
    protected function pegarValor() {
        return "ClasseConcreta2";
    }

    public function valorComPrefixo( $prefixo ) {
        return "{$prefixo}ClasseConcreta2";
    }
}

$classe1 = new ClasseConcreta1;
$classe1->imprimir();
echo $classe1->valorComPrefixo('FOO_') ."\n";

$classe2 = new ClasseConcreta2;
$classe2->imprimir();
echo $classe2->valorComPrefixo('FOO_') ."\n";
?>