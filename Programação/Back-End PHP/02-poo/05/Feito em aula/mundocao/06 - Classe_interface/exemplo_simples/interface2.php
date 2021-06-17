<?php
// Interface definition
abstract class Animal {
  public abstract function makeSound();
}

interface IAlimentacao {
    public function habitoAlimentar();
}

// Class definitions
class Cat extends Animal implements IAlimentacao {
  public function makeSound() {
    echo " Meow ";
  }

  public function habitoAlimentar()
  {
   echo "Sou Onivoro";   
  }
}

class Dog extends Animal implements IAlimentacao {
    public function makeSound() {
      echo " Au ";
    }
  
    public function habitoAlimentar()
    {
     echo "Sou Onivoro";   
    }
}

class Leao extends Animal implements IAlimentacao {
  public function makeSound() {
    echo " MEAU! ";
  }

  public function habitoAlimentar()
  {
     echo "Sou carnivoro";   
  }

}

// Create a list of animals
$cat = new Cat();
$dog = new Dog();
$leao = new Leao();

echo "Eu faço " . $cat->makeSound() . PHP_EOL;
echo "E sou " . $cat->habitoAlimentar() . PHP_EOL;
echo "Eu faço " . $dog->makeSound() . PHP_EOL;
echo "E sou " . $dog->habitoAlimentar() . PHP_EOL;
echo "Eu faço " . $leao->makeSound() . PHP_EOL;
echo "E sou " . $leao->habitoAlimentar() . PHP_EOL;

?>