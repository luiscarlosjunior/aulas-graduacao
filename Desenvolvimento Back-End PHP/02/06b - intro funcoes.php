<?

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
