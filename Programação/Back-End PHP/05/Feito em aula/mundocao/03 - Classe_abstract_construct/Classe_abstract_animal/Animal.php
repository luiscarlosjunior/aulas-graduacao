<?php 

    abstract class Animal {
        // Atributos
        private string $especie;
        private int $numeroPatas;

        protected function setEspecie(string $especie)
        {
            $this->especie = $especie;
        }

        protected function getEspecie() : string
        {
            return $this->especie;
        }

        protected function setNumeroPatas(int $numeroPatas)
        {
            $this->numeroPatas = $numeroPatas;
        }

        protected function getNumeroPatas() : int
        {
            return $this->numeroPatas;
        }
    }

?>