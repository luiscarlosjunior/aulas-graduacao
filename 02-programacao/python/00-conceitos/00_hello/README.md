# Hello World em Python

Este diretório contém exemplos do programa "Hello World" em Python - o primeiro passo para qualquer programador.

## Arquivos

### `hello_world.py`
Programa básico que demonstra:
- Como executar um programa Python
- Como imprimir mensagens no console
- Como usar argumentos da linha de comando
- Estrutura básica de um programa Python

## Como Executar

```bash
# Navegue até este diretório
cd programming/python/00-conceitos/00_hello

# Execute o programa
python3 hello_world.py

# Execute com argumentos
python3 hello_world.py seu_nome 123
```

## Diferenças entre Python e Java

1. **Sem compilação**: Python não precisa ser compilado, é interpretado diretamente
2. **Menos verboso**: Não precisa de classes obrigatórias para um programa simples
3. **Indentação**: Python usa indentação ao invés de chaves `{}`
4. **Tipagem dinâmica**: Não precisa declarar tipos de variáveis
5. **`if __name__ == "__main__":`**: Padrão Python para definir o ponto de entrada

## Conceitos Principais

- **`print()`**: Função para exibir texto no console
- **`def main()`**: Boa prática para organizar o código
- **`if __name__ == "__main__":`**: Permite que o arquivo seja importado sem executar o código
- **`sys.argv`**: Lista com argumentos da linha de comando
