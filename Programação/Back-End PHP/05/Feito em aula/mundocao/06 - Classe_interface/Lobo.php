<?php

    class Lobo extends Animal implements IAlimentacao{
        
        public function Som() {
            echo "Auuuuuuuuu!" . PHP_EOL;
        }

        public function habitoAlimentar() : void{
            echo " Sou carnivoro " . PHP_EOL;
        }

    }

?>