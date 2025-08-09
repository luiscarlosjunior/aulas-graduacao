# Conceitos Básicos do PHP

Esta seção contém os conceitos fundamentais da linguagem PHP, organizados de forma progressiva para facilitar o aprendizado.

## 📋 Pré-requisitos

- PHP instalado (veja o [Guia de Instalação](../INSTALACAO.md))
- Editor de texto ou IDE
- Terminal/Prompt de comando

## 🎯 Objetivos de Aprendizado

Ao final desta seção, você será capaz de:

- Entender a sintaxe básica do PHP
- Trabalhar com variáveis e tipos de dados
- Utilizar operadores e estruturas de controle
- Manipular strings e arrays
- Criar programas básicos em PHP

## 📚 Conteúdo

### [01 - Fundamentos](01/)

#### Exemplos Disponíveis:

1. **[01-ola-mundo.php](01/01-ola-mundo.php)** 
   - Primeiro programa em PHP
   - Como usar `echo` para exibir texto
   ```bash
   php 01-ola-mundo.php
   ```

2. **[02-variavel.php](01/02-variavel.php)**
   - Declaração e uso de variáveis
   - Convenções de nomenclatura
   ```bash
   php 02-variavel.php
   ```

3. **[03-tipos.php](01/03-tipos.php)**
   - Tipos de dados: string, int, float, boolean
   - Verificação de tipos com `var_dump()`
   ```bash
   php 03-tipos.php
   ```

4. **[04-operacoes.php](01/04-operacoes.php)**
   - Operadores aritméticos (+, -, *, /, %)
   - Operadores de comparação e lógicos
   ```bash
   php 04-operacoes.php
   ```

5. **[05-strings.php](01/05-strings.php)**
   - Manipulação de strings
   - Concatenação e funções úteis
   ```bash
   php 05-strings.php
   ```

6. **[06-lista.php](01/06%20-%20lista.php)**
   - Trabalhando com arrays
   - Arrays indexados e associativos
   ```bash
   php "06 - lista.php"
   ```

7. **[06-decisoes.php](01/06-decisoes.php)**
   - Estruturas condicionais: if, else, elseif
   - Operador ternário
   ```bash
   php 06-decisoes.php
   ```

8. **[07-repeticao-while.php](01/07-repeticao-while.php)**
   - Laços de repetição com `while`
   - Controle de fluxo com break e continue
   ```bash
   php 07-repeticao-while.php
   ```

9. **[08-repeticao-for.php](01/08-repeticao-for.php)**
   - Laços de repetição com `for`
   - Iteração sobre arrays
   ```bash
   php 08-repeticao-for.php
   ```

### 🎯 Desafios Práticos

Teste seus conhecimentos com estes exercícios:

1. **[Desafio 1 - Números Ímpares](01/Desafio%201%20-%20Numeros%20impares.php)**
   - Imprima números ímpares de 1 a 100
   ```bash
   php "Desafio 1 - Numeros impares.php"
   ```

2. **[Desafio 2 - Tabuada](01/Desafio%202%20-%20Tabuada.php)**
   - Gere a tabuada de um número
   ```bash
   php "Desafio 2 - Tabuada.php"
   ```

3. **[Desafio 3 - IMC](01/Desafio%203%20-%20IMC.php)**
   - Calculadora de Índice de Massa Corporal
   ```bash
   php "Desafio 3 - IMC.php"
   ```

## 🚀 Como Executar

### No Terminal/Prompt

1. Navegue até a pasta dos conceitos:
   ```bash
   cd programming/php/01-conceitos-php/01/
   ```

2. Execute qualquer exemplo:
   ```bash
   php nome-do-arquivo.php
   ```

### Em um Servidor Web

Se você tem XAMPP, MAMP ou outro servidor:

1. Copie os arquivos para a pasta `htdocs`
2. Acesse no navegador: `http://localhost/nome-do-arquivo.php`

## 💡 Dicas de Estudo

1. **Execute todos os exemplos**: Não apenas leia, execute cada arquivo
2. **Modifique os códigos**: Experimente alterar valores e ver o resultado
3. **Use var_dump()**: Para entender o que está acontecendo com suas variáveis
4. **Pratique os desafios**: Eles consolidam o aprendizado

## 🔧 Comandos Úteis

```bash
# Verificar sintaxe sem executar
php -l arquivo.php

# Executar código PHP diretamente
php -r "echo 'Hello World';"

# Executar em modo interativo
php -a

# Ver informações do PHP
php -i
```

## ❓ Conceitos Importantes

### Sintaxe Básica
- Todo código PHP deve estar entre `<?php` e `?>`
- Cada linha termina com `;`
- Variáveis começam com `$`
- PHP é case-sensitive para variáveis

### Tipos de Dados
- **string**: Texto entre aspas
- **int**: Números inteiros
- **float**: Números decimais
- **boolean**: true ou false
- **array**: Lista de valores
- **null**: Valor vazio

### Operadores
- **Aritméticos**: +, -, *, /, %
- **Comparação**: ==, !=, <, >, <=, >=, ===
- **Lógicos**: &&, ||, !
- **Atribuição**: =, +=, -=, *=, /=

## 🐛 Problemas Comuns

### Erro de sintaxe
```
Parse error: syntax error, unexpected...
```
- Verifique se não esqueceu `;` no final das linhas
- Confira se todas as chaves `{}` estão fechadas

### Variável não definida
```
Notice: Undefined variable...
```
- Verifique se declarou a variável antes de usar
- Lembre-se que variáveis começam com `$`

### Arquivo não encontrado
- Verifique se está na pasta correta
- Confirme o nome do arquivo (case-sensitive no Linux/macOS)

## 📖 Próximos Passos

Após dominar estes conceitos, você pode avançar para:

1. **[Programação Orientada a Objetos](../02-poo/)** - Classes, objetos e conceitos OOP
2. **[PHP para Web](../03-web/)** - Desenvolvimento de aplicações web
3. **[Banco de Dados](../04-banco-dados/)** - Integração com bancos de dados

## 📚 Recursos Adicionais

- [Manual do PHP - Tipos](https://www.php.net/manual/pt_BR/language.types.php)
- [Manual do PHP - Operadores](https://www.php.net/manual/pt_BR/language.operators.php)
- [Manual do PHP - Estruturas de Controle](https://www.php.net/manual/pt_BR/language.control-structures.php)

---

💡 **Dica**: Pratique muito! A programação se aprende fazendo. Execute, modifique e experimente com todos os exemplos.