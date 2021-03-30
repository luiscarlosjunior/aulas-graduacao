<?php

$contasCorrentes = [
    '123.456.789-10' => [
        'titular' => 'Maria',
        'saldo' => 10000
    ],
    '123.456.689-11' => [
        'titular' => 'Alberto',
        'saldo' => 300
    ],
    '123.256.789-12' => [
        'titular' => 'Vinicius',
        'saldo' => 100
    ]
];

$contasCorrentes['123.258.852-12'] = [
    'titular' => 'Claudia',
    'saldo' => 2000
];

foreach ($contasCorrentes as $cpf => $conta) {
    echo $cpf . " " . $conta['titular'] . PHP_EOL;
}
