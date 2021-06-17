<?php

    class Cachorro extends Animal implements IAlimentacao {
        public string $nome;
                
        public function darEspecie(string $especie){
            $this->especie = $especie;
        }

        public function retornarEspecie(){
            return $this->especie;
        }

        public function Som()
        {
            echo "Au Au!" . PHP_EOL;
        }

        public function habitoAlimentar() : void {
            echo "Sou onivoro" . PHP_EOL;
        }

    }

?>