<?

function foo ($arg_1, $arg_2, /* ..., */ $arg_n)
{
    echo "Exemplo de função.\n";
    return $valor_retornado;
}

function adicionarDois($valor)
{
    return $valor + 2;
}

function multiplicarPorDois($valor)
{
    return $valor * 2;
}

$resultado = adicionarDois(0) + multiplicarPorDois(0);
echo $resultado;

exit();
