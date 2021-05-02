<?php
/*
$a = readline('Enter a value: ');
$b = readline('Enter a value: ');

if($a > $b)
{
    echo $a . $b;
} 
else 
{
    echo "A valor $b é maior que o valor $a";
}    */ 

$a = readline('Entre com o valor: ');
$b = readline('Entre com o valor: ');
$c = readline('Entre com o valor: ');

if($a <= $b && $b <= $c){
    echo "$a, $b e $c";
}
else if($a <= $c && $c <= $b)
{
    echo "$a, $c e $b";
}
else if($b <= $b && $a <= $c)
{
    echo "$b, $a e $c";
}
else if($b <= $c && $c <= $a)
{
    echo "$b, $c e $a";
}
else if($c <= $a && $a <= $b)
{
    echo "$c, $a e $b";
}
else if($c <= $b && $b <= $a)
{
    echo "$c, $b e $a";
}
