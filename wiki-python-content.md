# Programação Python

**Bem-vindo ao guia completo de Python do repositório aulas-graduação!**

Este wiki organiza todo o conteúdo Python disponível no repositório, desde conceitos fundamentais até aplicações avançadas em ciência de dados e métodos numéricos.

## 📚 Visão Geral

O repositório contém materiais Python organizados em quatro principais áreas:

- 🎯 **[Programação Fundamental](#-programação-fundamental)** - Conceitos básicos e POO
- 📊 **[Ciência de Dados](#-ciência-de-dados)** - Análise de dados com Pandas e visualização
- 🔢 **[Métodos Numéricos](#-métodos-numéricos)** - Computação científica e matemática
- 📈 **[Análise Estatística](#-análise-estatística)** - Estatística descritiva e inferencial

---

## 🎯 Programação Fundamental

### Conceitos de Programação Orientada a Objetos (POO)

**Localização:** `programming/python/poo-example/`

Esta seção demonstra os quatro pilares fundamentais da POO através de um exemplo prático de sistema bancário:

#### 📖 Conceitos Demonstrados

1. **Herança (Inheritance)**
   - `ContaPoupanca` herda de `Conta`
   - Reutilização de código e especialização

2. **Encapsulamento (Encapsulation)**
   - Atributos privados com getters/setters
   - Controle de acesso aos dados

3. **Associação (Association)**
   - `Cliente` possui `ContaPoupanca`
   - Relacionamento "tem um" (has-a)

4. **Polimorfismo (Polymorphism)**
   - Métodos com comportamentos específicos

#### 🗂️ Arquivos Disponíveis

| Arquivo | Descrição |
|---------|-----------|
| [`conta.py`](programming/python/poo-example/conta.py) | Classe base Conta |
| [`conta_poupanca.py`](programming/python/poo-example/conta_poupanca.py) | Classe ContaPoupanca (herança) |
| [`cliente.py`](programming/python/poo-example/cliente.py) | Classe Cliente (associação) |
| [`main.py`](programming/python/poo-example/main.py) | Demonstração completa |
| [`teste_poo.py`](programming/python/poo-example/teste_poo.py) | Testes de validação |

#### 🚀 Como Executar

```bash
cd programming/python/poo-example/
python3 main.py        # Demonstração completa
python3 teste_poo.py   # Execução dos testes
```

#### 📋 Exemplo de Uso

```python
# Criando um cliente
cliente = Cliente()
cliente.nome = "João"
cliente.cpf = "123.456.789-00"

# Criando conta poupança (associação)
conta = cliente.criar_conta_poupanca()
conta.set_agencia("0001")
conta.set_conta("123456")

# Realizando operações (herança)
conta.depositar(1000.0)
saldo = cliente.ver_saldo(1234)  # senha correta
lucro = conta.ver_lucro()  # método específico de ContaPoupanca
```

---

## 📊 Ciência de Dados

### Introdução ao Python para Ciência de Dados

**Localização:** `data-science/python/`

#### 📓 Notebooks Disponíveis

| Notebook | Descrição | Tópicos Abordados |
|----------|-----------|-------------------|
| [`101_Introdução_Python-mn.ipynb`](data-science/python/101_Introdução_Python-mn.ipynb) | Introdução ao Python | Sintaxe básica, tipos de dados, estruturas de controle |
| [`Aula_de_Pandas.ipynb`](data-science/python/Aula_de_Pandas.ipynb) | Tutorial de Pandas | DataFrames, manipulação de dados, análise exploratória |
| [`Request_aula_(Pré_processamento).ipynb`](data-science/python/Request_aula_(Pré_processamento).ipynb) | Pré-processamento de dados | Limpeza, transformação, preparação de dados |
| [`Atividade_II_1_ponto_CD_Correção.ipynb`](data-science/python/Atividade_II_1_ponto_CD_Correção.ipynb) | Exercícios corrigidos | Atividades práticas com soluções |

#### 🛠️ Ferramentas e Bibliotecas

- **Pandas** - Manipulação e análise de dados
- **NumPy** - Computação numérica
- **Matplotlib** - Visualização de dados
- **Jupyter Notebooks** - Ambiente interativo

#### 📖 Tópicos Fundamentais

1. **Sintaxe Python**
   - Variáveis e tipos de dados
   - Estruturas de controle (if, for, while)
   - Funções e módulos

2. **Manipulação de Dados com Pandas**
   - Criação e manipulação de DataFrames
   - Leitura de arquivos (CSV, Excel, JSON)
   - Filtragem e agregação de dados

3. **Pré-processamento**
   - Limpeza de dados
   - Tratamento de valores ausentes
   - Normalização e padronização

---

## 🔢 Métodos Numéricos

### Computação Científica com Python

**Localização:** `metodos-numericos/`

#### 📓 Notebooks Disponíveis

| Notebook | Descrição | Conceitos |
|----------|-----------|-----------|
| [`01 - Introdução_Python-mn.ipynb`](metodos-numericos/01%20-%20Introdução_Python-mn.ipynb) | Fundamentos para métodos numéricos | Sintaxe, bibliotecas científicas |
| [`02 - Noções_de_erros.ipynb`](metodos-numericos/02%20-%20Noções_de_erros.ipynb) | Análise de erros numéricos | Erros de arredondamento, truncamento |
| [`03 - MMQ.ipynb`](metodos-numericos/03%20-%20MMQ.ipynb) | Método dos Mínimos Quadrados | Regressão linear, ajuste de curvas |

#### 🧮 Tópicos Abordados

1. **Fundamentos Numéricos**
   - Representação de números em computadores
   - Precisão e arredondamento
   - Estabilidade numérica

2. **Análise de Erros**
   - Tipos de erros (absoluto, relativo)
   - Propagação de erros
   - Condicionamento de problemas

3. **Métodos de Ajuste**
   - Método dos Mínimos Quadrados (MMQ)
   - Regressão linear e não-linear
   - Análise de resíduos

#### 🔧 Bibliotecas Utilizadas

- **NumPy** - Arrays e operações matemáticas
- **SciPy** - Métodos numéricos especializados
- **Matplotlib** - Visualização de resultados
- **SymPy** - Matemática simbólica

---

## 📈 Análise Estatística

### Estatística Descritiva e Análise de Dados

**Localização:** `data-science/analise-dados/`

#### 📁 Estrutura de Conteúdo

```
data-science/analise-dados/
├── Análise com python/          # Notebooks de estatística
├── exercicio python/            # Exercícios práticos
└── datasets/                    # Conjuntos de dados
```

#### 📓 Cursos de Estatística

| Notebook | Foco | Conteúdo |
|----------|------|----------|
| [`Curso_de_Estatística_Parte_1.ipynb`](data-science/analise-dados/Análise%20com%20python/Curso_de_Estatística_Parte_1.ipynb) | Estatística básica | Conceitos fundamentais |
| [`Curso_de_Estatística_Parte_2.ipynb`](data-science/analise-dados/Análise%20com%20python/Curso_de_Estatística_Parte_2.ipynb) | Distribuições | Probabilidade e distribuições |
| [`Curso_de_Estatística_Parte_3.ipynb`](data-science/analise-dados/Análise%20com%20python/Curso_de_Estatística_Parte_3.ipynb) | Inferência | Testes de hipóteses |
| [`Curso_de_Estatística_Parte_4.ipynb`](data-science/analise-dados/Análise%20com%20python/Curso_de_Estatística_Parte_4.ipynb) | Aplicações | Casos práticos |

#### 📊 Tópicos Específicos

| Assunto | Notebook | Conceitos |
|---------|----------|-----------|
| **Frequência** | [`1°_análise_de_dados_(Frequencia).ipynb`](data-science/analise-dados/Análise%20com%20python/1°_análise_de_dados_(Frequencia).ipynb) | Distribuições de frequência, histogramas |
| **Tendência Central** | [`Aula 07 - Medidas de tendência central.ipynb`](data-science/analise-dados/Análise%20com%20python/Aula%2007%20-%20Medidas%20de%20tendência%20central.ipynb) | Média, mediana, moda |
| **Dispersão** | [`Aula_08_Medidas_de_dispersão.ipynb`](data-science/analise-dados/Análise%20com%20python/Aula_08_Medidas_de_dispersão.ipynb) | Variância, desvio padrão, amplitude |
| **Simetria e Curtose** | [`Aula_10_Medidas_de_simetria_e_curtose.ipynb`](data-science/analise-dados/Análise%20com%20python/Aula_10_Medidas_de_simetria_e_curtose.ipynb) | Assimetria, achatamento |
| **Visualização** | [`Aula_10_Histograma_Curtose_BoxPlot.ipynb`](data-science/analise-dados/Análise%20com%20python/Aula_10_Histograma_Curtose_BoxPlot.ipynb) | Gráficos estatísticos |

#### 📐 Exercícios Práticos

**Localização:** `data-science/analise-dados/exercicio python/`

- **Scripts Python** - Exemplos básicos e gráficos
- **Jupyter Notebooks** - Exercícios interativos
- **Datasets** - Dados para análise prática

---

## 🚀 Como Começar

### 1. **Iniciante em Python**
👉 Comece com: [`101_Introdução_Python-mn.ipynb`](data-science/python/101_Introdução_Python-mn.ipynb)

### 2. **Programação Orientada a Objetos**
👉 Explore: [`programming/python/poo-example/`](programming/python/poo-example/)

### 3. **Ciência de Dados**
👉 Inicie com: [`Aula_de_Pandas.ipynb`](data-science/python/Aula_de_Pandas.ipynb)

### 4. **Métodos Numéricos**
👉 Comece por: [`01 - Introdução_Python-mn.ipynb`](metodos-numericos/01%20-%20Introdução_Python-mn.ipynb)

### 5. **Análise Estatística**
👉 Inicie com: [`Curso_de_Estatística_Parte_1.ipynb`](data-science/analise-dados/Análise%20com%20python/Curso_de_Estatística_Parte_1.ipynb)

---

## 🛠️ Configuração do Ambiente

### Requisitos Básicos

```bash
# Python 3.6 ou superior
python3 --version

# Instalação de dependências essenciais
pip3 install jupyter numpy pandas matplotlib scipy
```

### Para Jupyter Notebooks

```bash
# Instalação do Jupyter
pip3 install jupyter notebook

# Execução
jupyter notebook
```

### Para Scripts Python

```bash
# Navegue até o diretório desejado
cd programming/python/poo-example/

# Execute o script
python3 main.py
```

---

## 📚 Recursos Adicionais

### Documentação e Referências

- **[Python Official Documentation](https://docs.python.org/3/)**
- **[Pandas Documentation](https://pandas.pydata.org/docs/)**
- **[NumPy Documentation](https://numpy.org/doc/)**
- **[Matplotlib Documentation](https://matplotlib.org/stable/)**

### Ferramentas Recomendadas

- **[Google Colab](https://colab.research.google.com/)** - Notebooks online gratuitos
- **[Anaconda](https://www.anaconda.com/)** - Distribuição Python para ciência de dados
- **[VS Code](https://code.visualstudio.com/)** - Editor com suporte Python

---

## 🤝 Como Contribuir

Interessado em contribuir com mais conteúdo Python? Veja nosso guia:

- **[Como contribuir](https://github.com/luiscarlosjunior/aulas-graduacao/wiki/Como-contribuir)**
- **[Guia de estilo](contributing.md)**

---

## 📞 Suporte

Encontrou algum problema ou tem sugestões? 

- **Abra uma [issue](https://github.com/luiscarlosjunior/aulas-graduacao/issues)**
- **Consulte a [documentação](README.md)**

---

**💡 Dica:** Este repositório é uma excelente fonte para aprender Python do básico ao avançado, com exemplos práticos e aplicações reais em ciência de dados e computação científica!