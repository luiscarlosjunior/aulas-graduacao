<?php

    class Gato extends Animal implements IAlimentacao {
        protected string $nome;
                
        public function Som()
        {
            echo "Miiiaaauuuuuu!" . PHP_EOL;
        }

        public function habitoAlimentar() : void {
            echo " Sou onivoro " . PHP_EOL;
        }

    }

?>