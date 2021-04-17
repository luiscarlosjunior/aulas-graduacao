<?php
// Interface definition: Contrato
interface Animal {
  public function makeSound();
}

// Class definitions
class Cat implements Animal {
  public function makeSound() {
    echo " Meow ";
  }
}

class Dog implements Animal {
  public function makeSound() {
    echo " Bark ";
  }
}

class Mouse implements Animal {
  public function makeSound() {
    echo " Squeak ";
  }
}

// Create a list of animals
$cat = new Cat();
$dog = new Dog();
$mouse = new Mouse();

$animals = array($cat, $dog, $mouse);
//$animals = [$cat, $dog, $mouse];

/*$cat->makeSound();
$dog->makeSound();
$mouse->makeSound();*/

// Tell the animals to make a sound
foreach($animals as $animal) {
  $animal->makeSound();
}
?>