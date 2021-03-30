<?php
  
  class Cachorro 
  {
    // Atributos
    public $corPelo = "Preto";
    public $corOlhos = "Cinza";

    // Comportamento
    function Latir()
    {
      echo("Au au!!!" . PHP_EOL);
    }
  }

  // Estamos instânciando o objeto
  $poddle = new Cachorro();
  $pinscher = new Cachorro();

  // Estamos chamando o método latir
  $poddle->Latir();
  $pinscher->Latir();