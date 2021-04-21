# Tipo de Dado em Java
Tipos de dados são especificados de diferentes tamanhos e valores que podem ser armazenados na variável. Existem dois tipos de dados em java:

1. **Tipo de dado primitivo**: Incluem boolean, char, byte, short, int, long, float, e double.
2. **Tipo de dado não primitivo**: Incluem classes, interfaces e arrays.

## Tipo de dado boolean
O tipo de dado boolean é usado para armazenar somente dois possíveis valores: verdadeiro e falso. Este tipo de dado é usado por sinalizadores simples que localizam condições verdadeira/falsa.

Especifica um bit de informação, mas o "tamanho" não pode ser especificado precisamente.

Exemplo: boolean one = false

## Tipo de dado byte
É um exemplo de um tipo de dado primitivo. É um inteiro de complemento de dois com assinatura de 8 bits. Sua faixa de valor está entre -128 até 127 (inclusive). Valor mínimo é -128 e o valor máximo é 127. Seu valor padrão é 0 (zero).

O tipo de dado byte é usado para salvar em memória grandes matrizes onde a memória salva é muito requisitada. Isso economiza espaço porque um byte é quatro vezes menor que um número inteiro. Também pode ser usado no lugar de tipos de dados "int".

Exemplo: byte a = 10, byte b = -20

## Tipo de dado short
O tipo de dado short é um inteiro de complemento de dois com assinatura de 16 bits. Sua faixa de valor está entre -32.768 até 32.767 (inclusive). Seu valor mínimo é -32.768 e valor máximo é 32.767. Seu valor padrão é 0 (zero).

O tipo de data short também pode ser usado para economizar memória assim como o tipo de dado byte. Um tipo de dado short é duas vezes menor que um inteiro.

Exemplo: short s = 10000, short r = -5000

## Tipo de dado int
O tipo de dado int é um inteiro de complemento de dois com assinatura de 32 bits. Sua faixa de valor está entre -2.147.483.648 (-2³¹) até 2.147.483.647 (2³¹?¹) (inclusive). Seu valor mínimo é -2.147.483.648 e valor máximo é 2.147.483.647. Seu valor padrão é 0 (zero).

O tipo de dado int geralmente é usado como um tipo de dado padrão para valores inteiros sem nenhum problema de memória.

Exemplo: int a  = 100000, int b = -200000

## Tipo de dado long
O tipo de dado long é um inteiro de complemento de dois com assinatura de 64 bits. Sua faixa de valor está entre -9.223.372.036.854.775.808 (-2?³) até 9.223.372.036.854.775.807 (2?³?¹) (inclusive). Seu valor mínimo é -9.223.372.036.854.775.808 e o valor máximo é de 9.223.372.036.854.775.807. O valor padrão é 0 (zero). O tipo de dado long é usado quando você precisa de uma faixa de valores maior do que a fornecida por int.

Exemplo: long a = 100000L, long b = -200000L

## Tipo de dado float
O tipo de dado float é um ponto flutuante IEEE 754 de 32 bits de precisão única. Sua faixa de valor é ilimitada. É recomendado usar um ponto flutuante (em vez de double) se você precisar economizar memória em grandes arrays (matrizes) de número de ponto flutuante. O tipo de dado float nunca deve ser usado para valores precisos, como moeda. Seu valor padrão é 0.0f.

Exemplo: float f1 = 234.5f

## Tipo de dado double
O tipo de dado double é um ponto flutuante IEEE 754 de 64 bits de precisão dupla. Seu valor é ilimitado. O tipo de dado double é geralmente usado para valores decimais assim como float. O tipo de dado double também não deve ser usado para precisar valores, como moeda. Seu valor padrão é 0.0d.

Exemplo: double d1 = 12.3

## Tipo de dado char
O tipo de dado char é um único caractere unicode de 16 bits. Sua faixa de valor está entre '\u0000' (ou 0) até '\uffff' (ou 65.535 inclusive). O tipo de data char é usado para para armazenar caracteres.

Exemplo: char letraA = 'A'

## Por que char usa 2 bytes em java e o que é \u0000?
É porque o java usa o sistema unicode e não o sistema de código ASCII. O \u0000 é o intervalo mais baixo do sistema unicode. Para obter uma explicação detalhada sobre unicode, visite a próxima página.
