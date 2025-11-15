# 🧮 Métodos Numéricos - Computação Científica

> Material educacional completo de Métodos Numéricos, da graduação à pós-graduação (Mestrado e Doutorado)

Este repositório contém uma coleção abrangente de conteúdos, exemplos práticos e implementações em Python sobre Métodos Numéricos, cobrindo desde conceitos fundamentais até tópicos avançados de pesquisa científica.

[![Python](https://img.shields.io/badge/Python-3.8+-blue.svg)](https://www.python.org/)
[![Jupyter](https://img.shields.io/badge/Jupyter-Notebook-orange.svg)](https://jupyter.org/)
[![NumPy](https://img.shields.io/badge/NumPy-Latest-013243.svg)](https://numpy.org/)

---

## 📚 Índice

- [O que são Métodos Numéricos?](#-o-que-são-métodos-numéricos)
- [Estrutura do Curso](#-estrutura-do-curso)
  - [Nível de Graduação](#nivel-graduação)
  - [Nível de Pós-Graduação](#nivel-pós-graduação)
- [Conteúdo dos Notebooks](#-conteúdo-dos-notebooks)
- [Fundamentos Teóricos](#-fundamentos-teóricos)
- [Pré-requisitos](#-pré-requisitos)
- [Configuração do Ambiente](#-configuração-do-ambiente)
- [Tópicos Avançados](#-tópicos-avançados-pós-graduação)
- [Aplicações Práticas](#-aplicações-práticas)
- [Referências Bibliográficas](#-referências-bibliográficas)
- [Como Usar Este Material](#-como-usar-este-material)

---

## 🎯 O que são Métodos Numéricos?

**Métodos Numéricos** (ou Análise Numérica) é o ramo da matemática computacional que desenvolve, analisa e implementa algoritmos para obter soluções **aproximadas** de problemas matemáticos complexos que, frequentemente, não possuem solução analítica fechada ou cuja solução analítica é impraticável de se calcular.

### Por que estudar Métodos Numéricos?

1. **🔬 Problemas do Mundo Real**: Muitos fenômenos físicos, químicos, biológicos e econômicos são modelados por equações complexas sem solução analítica
2. **💻 Era Digital**: Com o poder computacional moderno, podemos resolver problemas que eram impossíveis há décadas
3. **🎓 Interdisciplinaridade**: Aplicados em Engenharia, Física, Química, Economia, Biologia, Medicina, e outras áreas
4. **🚀 Pesquisa Científica**: Fundamentais para simulações, modelagem e análise de dados experimentais

### Áreas de Aplicação

- **Engenharia**: Análise estrutural, dinâmica de fluidos, transferência de calor
- **Física**: Mecânica quântica, astrofísica, física de partículas
- **Biologia**: Modelagem de populações, dinâmica de proteínas, epidemiologia
- **Economia**: Análise financeira, otimização de portfólios, previsão econômica
- **Medicina**: Processamento de imagens médicas, modelagem de doenças
- **Ciência de Dados**: Machine Learning, análise estatística, Big Data

---

## 📖 Estrutura do Curso

Este material está organizado para cobrir progressivamente desde conceitos básicos até tópicos de pesquisa avançada.

### <a name="nivel-graduação"></a>🎓 Nível de Graduação

#### **Módulo 1: Fundamentos**
- Introdução ao Python para Computação Científica
- Aritmética de ponto flutuante
- Erros de arredondamento e truncamento
- Propagação e análise de erros
- Estabilidade numérica e condicionamento

#### **Módulo 2: Solução de Equações**
- Métodos iterativos (Bissecção, Newton-Raphson, Secante)
- Sistemas de equações lineares
  - Métodos diretos (Eliminação de Gauss, Decomposição LU)
  - Métodos iterativos (Jacobi, Gauss-Seidel)
- Sistemas de equações não-lineares

#### **Módulo 3: Interpolação e Aproximação**
- Interpolação polinomial (Lagrange, Newton)
- Splines cúbicos
- **Método dos Mínimos Quadrados (MMQ)**
- Ajuste de curvas (regressão linear e não-linear)

#### **Módulo 4: Diferenciação e Integração Numérica**
- Diferenças finitas
- Extrapolação de Richardson
- Integração numérica (Trapézios, Simpson)
- Quadratura gaussiana

#### **Módulo 5: Equações Diferenciais Ordinárias (EDO)**
- Método de Euler
- Métodos de Runge-Kutta
- Métodos multipasso
- Problemas de valor inicial e de contorno

### <a name="nivel-pós-graduação"></a>🎓 Nível de Pós-Graduação (Mestrado e Doutorado)

#### **Tópicos Avançados**

##### 1. **Análise Numérica Avançada**
- Teoria da aproximação
- Análise de convergência e estabilidade
- Teoria dos erros (a priori e a posteriori)
- Condicionamento de problemas mal-postos

##### 2. **Álgebra Linear Numérica**
- Decomposições matriciais avançadas (SVD, QR, Cholesky)
- Cálculo de autovalores e autovetores
- Métodos de Krylov (GMRES, BiCGSTAB)
- Precondicionadores

##### 3. **Equações Diferenciais Parciais (EDP)**
- Método das Diferenças Finitas (MDF)
- Método dos Elementos Finitos (MEF)
- Método dos Volumes Finitos
- Métodos espectrais

##### 4. **Otimização Numérica**
- Otimização sem restrições (Gradiente, Newton, Quasi-Newton)
- Otimização com restrições (KKT, programação quadrática)
- Algoritmos genéticos e metaheurísticas
- Otimização convexa

##### 5. **Métodos de Monte Carlo**
- Simulação estocástica
- Monte Carlo Markov Chain (MCMC)
- Integração Monte Carlo
- Métodos quasi-Monte Carlo

##### 6. **Tópicos Especiais**
- Métodos adaptativos e multigrid
- Métodos paralelos e computação distribuída
- Aprendizado de máquina e métodos numéricos
- Problemas inversos e regularização
- Computação de alta performance (HPC)

---

## 📓 Conteúdo dos Notebooks

### 1️⃣ **Introdução ao Python para Métodos Numéricos**
📘 [`01 - Introdução_Python-mn.ipynb`](01%20-%20Introdução_Python-mn.ipynb)

**Conteúdo:**
- Fundamentos da linguagem Python
- Uso do Google Colab para computação científica
- Sintaxe básica e estruturas de dados
- Bibliotecas essenciais: NumPy, Matplotlib, SciPy
- Manipulação de arrays e vetores
- Visualização de dados

**Objetivos de Aprendizagem:**
- Configurar ambiente Python para computação científica
- Dominar operações básicas com NumPy
- Criar visualizações científicas
- Preparar base para implementações numéricas

---

### 2️⃣ **Noções de Erros e Representação Numérica**
📘 [`02 - Noções_de_erros.ipynb`](02%20-%20Noções_de_erros.ipynb)

**Conteúdo:**
- **Representação numérica em computadores**
  - Sistema binário e aritmética computacional
  - Ponto flutuante (padrão IEEE 754)
  - Precisão finita e limitações computacionais

- **Tipos de erros**
  - Erros de arredondamento
  - Erros de truncamento
  - Erros absolutos e relativos
  - Erros de modelagem

- **Propagação de erros**
  - Análise de sensibilidade
  - Estabilidade numérica
  - Condicionamento de problemas

- **Importância prática**
  - Perda de significância
  - Cancelamento catastrófico
  - Técnicas para minimizar erros

**Objetivos de Aprendizagem:**
- Compreender limitações da aritmética computacional
- Identificar e quantificar fontes de erro
- Avaliar estabilidade de algoritmos numéricos
- Desenvolver código numericamente robusto

**Conceitos-Chave:**
- **Precisão vs Exatidão**: Distinguir entre proximidade ao valor verdadeiro
- **Condicionamento**: Sensibilidade da solução a perturbações nos dados
- **Estabilidade**: Comportamento do algoritmo frente a erros de arredondamento

---

### 3️⃣ **Método dos Mínimos Quadrados (MMQ)**
📘 [`03 - MMQ.ipynb`](03%20-%20MMQ.ipynb)

**Conteúdo:**
- **Ajuste de curvas e regressão**
  - Conceitos fundamentais de ajuste
  - Motivação e aplicações práticas
  - Quando usar MMQ

- **Formulação matemática**
  - Minimização da soma dos quadrados dos resíduos
  - Derivação das equações normais
  - Solução analítica para caso linear

- **Regressão Linear**
  - Modelo: y = α₀ + α₁x
  - Cálculo dos coeficientes
  - Coeficiente de determinação (R²)
  - Análise de resíduos

- **Implementação em Python**
  - Implementação passo a passo do algoritmo
  - Uso de NumPy para cálculos matriciais
  - Visualização de dados e curva ajustada
  - Comparação com bibliotecas (scikit-learn, scipy)

- **Extensões**
  - Regressão polinomial
  - Regressão múltipla
  - Ajuste não-linear

**Objetivos de Aprendizagem:**
- Compreender teoria matemática do MMQ
- Implementar MMQ do zero em Python
- Avaliar qualidade do ajuste (R², resíduos)
- Aplicar MMQ em problemas reais

**Aplicações Práticas:**
- Análise de dados experimentais
- Previsão e modelagem
- Filtragem de ruído
- Calibração de instrumentos

---

## 🧠 Fundamentos Teóricos

### Principais Conceitos Matemáticos

#### 1. **Teoria da Convergência**
- Convergência de sequências e séries
- Taxa de convergência (linear, quadrática, superlinear)
- Critérios de parada em métodos iterativos

#### 2. **Análise de Erros**
```
Erro absoluto: |x* - x|
Erro relativo: |x* - x| / |x*|
```
onde x* é o valor exato e x é a aproximação

#### 3. **Condicionamento de Problemas**
O **número de condição** mede a sensibilidade da solução:
- Bem condicionado: pequenas perturbações → pequenas mudanças
- Mal condicionado: pequenas perturbações → grandes mudanças

#### 4. **Estabilidade de Algoritmos**
- **Estável**: erros de arredondamento não crescem descontroladamente
- **Instável**: pequenos erros se amplificam

---

## 🔧 Pré-requisitos

### Conhecimentos Matemáticos

#### **Essenciais (Graduação)**
- ✅ Cálculo Diferencial e Integral
- ✅ Álgebra Linear (matrizes, vetores, sistemas lineares)
- ✅ Equações Diferenciais Ordinárias
- ✅ Noções básicas de programação

#### **Recomendados (Pós-Graduação)**
- 📚 Análise Real
- 📚 Álgebra Linear Avançada
- 📚 Equações Diferenciais Parciais
- 📚 Análise Funcional
- 📚 Teoria da Probabilidade

### Habilidades Computacionais
- **Python 3.8+**: Linguagem de programação principal
- **NumPy**: Computação numérica eficiente
- **SciPy**: Algoritmos científicos
- **Matplotlib**: Visualização de dados
- **Jupyter Notebook**: Ambiente interativo

---

## ⚙️ Configuração do Ambiente

### Opção 1: Google Colab (Recomendado para Iniciantes)
```
1. Acesse: https://colab.research.google.com/
2. Faça upload dos notebooks (.ipynb)
3. Execute diretamente no navegador (sem instalação)
```

**Vantagens:**
- ✅ Sem necessidade de instalação
- ✅ GPU gratuita disponível
- ✅ Compartilhamento fácil
- ✅ Já vem com bibliotecas principais instaladas

### Opção 2: Instalação Local

#### **Passo 1: Instalar Python**
```bash
# Linux/Mac (via Homebrew ou apt-get)
sudo apt-get install python3 python3-pip

# Windows: baixar de python.org
```

#### **Passo 2: Instalar Dependências**
```bash
pip install numpy scipy matplotlib pandas jupyter notebook
```

#### **Passo 3: Instalar Bibliotecas Adicionais (Opcional)**
```bash
pip install scikit-learn sympy numba
```

#### **Passo 4: Executar Jupyter Notebook**
```bash
jupyter notebook
```

### Opção 3: Anaconda (Recomendado para Uso Avançado)
```bash
# Baixar Anaconda: https://www.anaconda.com/

# Criar ambiente virtual
conda create -n metodos-numericos python=3.9
conda activate metodos-numericos

# Instalar pacotes
conda install numpy scipy matplotlib jupyter pandas scikit-learn
```

---

## 🚀 Tópicos Avançados (Pós-Graduação)

### 1. **Métodos Iterativos para Sistemas Lineares Grandes**

Em problemas de grande escala (milhões de variáveis), métodos diretos como eliminação de Gauss tornam-se impraticáveis. Métodos iterativos são essenciais:

#### **Métodos Clássicos**
- **Jacobi**: Convergente para matrizes diagonalmente dominantes
- **Gauss-Seidel**: Versão melhorada do Jacobi
- **SOR (Successive Over-Relaxation)**: Aceleração da convergência

#### **Métodos de Krylov**
- **Gradiente Conjugado (CG)**: Para matrizes simétricas positivas definidas
- **GMRES**: Para matrizes não-simétricas
- **BiCGSTAB**: Alternativa ao GMRES

**Aplicações:** CFD (Dinâmica de Fluidos Computacional), Elementos Finitos, Simulações físicas

---

### 2. **Decomposição em Valores Singulares (SVD)**

Uma das ferramentas mais poderosas em álgebra linear numérica:

```
A = UΣV^T
```

**Aplicações:**
- Compressão de imagens
- Redução de dimensionalidade (PCA)
- Problemas de mínimos quadrados
- Solução de sistemas mal-condicionados
- Análise de dados (recomendação, NLP)

---

### 3. **Método dos Elementos Finitos (MEF)**

Técnica fundamental para resolver EDPs em geometrias complexas:

**Processo:**
1. Discretização do domínio (malha)
2. Formulação variacional (forma fraca)
3. Construção da matriz de rigidez
4. Solução do sistema linear resultante

**Áreas de Aplicação:**
- Análise estrutural (tensões, deformações)
- Transferência de calor
- Eletromagnetismo
- Mecânica dos fluidos

**Softwares:** ANSYS, COMSOL, FEniCS, deal.II

---

### 4. **Problemas Inversos**

Determinar causas a partir de efeitos observados (problema mal-posto):

**Características:**
- Não-unicidade da solução
- Instabilidade (pequenos erros → grandes mudanças)
- Necessidade de regularização

**Técnicas de Regularização:**
- Regularização de Tikhonov
- Truncamento de SVD
- Métodos bayesianos

**Aplicações:**
- Tomografia computadorizada
- Sismologia (estrutura da Terra)
- Deconvolução de imagens
- Identificação de parâmetros

---

### 5. **Computação de Alta Performance (HPC)**

Resolver problemas massivos usando supercomputadores e clusters:

**Paradigmas:**
- **Paralelização**: OpenMP, MPI
- **GPU Computing**: CUDA, OpenCL
- **Computação Distribuída**: Apache Spark, Dask

**Desafios:**
- Balanceamento de carga
- Comunicação entre processos
- Escalabilidade (strong vs weak scaling)
- Eficiência de memória

---

## 💡 Aplicações Práticas

### 🌊 Dinâmica de Fluidos Computacional (CFD)
Simulação de escoamento de fluidos (ar, água) ao redor de objetos:
- Design de aeronaves e carros (aerodinâmica)
- Previsão do tempo
- Simulação de tsunamis

### 🏗️ Engenharia Estrutural
Análise de tensões e deformações em estruturas:
- Pontes e edifícios
- Indústria aeroespacial
- Análise de fadiga

### 💊 Modelagem Biomédica
- Simulação de dinâmica de proteínas (drug design)
- Modelagem do coração (eletrofisiologia cardíaca)
- Epidemiologia (modelos SIR, SEIR)

### 🌍 Ciências da Terra
- Modelagem climática
- Previsão de terremotos
- Exploração de petróleo e gás

### 📈 Finanças Quantitativas
- Precificação de opções (Black-Scholes)
- Gestão de risco
- Otimização de portfólios

### 🤖 Machine Learning e IA
- Redes neurais (backpropagation = métodos de otimização)
- SVD em sistemas de recomendação
- Otimização de hiperparâmetros

---

## 📚 Referências Bibliográficas

### Livros Fundamentais (Graduação)

1. **Burden, R.L. & Faires, J.D.** (2015)  
   *Numerical Analysis* (10th Edition)  
   📖 Excelente para iniciantes, com muitos exemplos

2. **Chapra, S.C. & Canale, R.P.** (2014)  
   *Numerical Methods for Engineers* (7th Edition)  
   🔧 Focado em aplicações de engenharia

3. **Ruggiero, M.A.G. & Lopes, V.L.R.** (1996)  
   *Cálculo Numérico: Aspectos Teóricos e Computacionais*  
   🇧🇷 Clássico em português

4. **Franco, N.B.** (2006)  
   *Cálculo Numérico*  
   🇧🇷 Material didático brasileiro

### Livros Avançados (Pós-Graduação)

5. **Trefethen, L.N. & Bau, D.** (1997)  
   *Numerical Linear Algebra*  
   ⭐ Referência definitiva em álgebra linear numérica

6. **Golub, G.H. & Van Loan, C.F.** (2013)  
   *Matrix Computations* (4th Edition)  
   📚 Bíblia da computação matricial

7. **Quarteroni, A., Sacco, R. & Saleri, F.** (2007)  
   *Numerical Mathematics*  
   🎓 Abordagem moderna e rigorosa

8. **Heath, M.T.** (2018)  
   *Scientific Computing: An Introductory Survey* (3rd Edition)  
   💻 Equilíbrio entre teoria e implementação

9. **Nocedal, J. & Wright, S.J.** (2006)  
   *Numerical Optimization* (2nd Edition)  
   🎯 Referência em otimização numérica

10. **LeVeque, R.J.** (2007)  
    *Finite Difference Methods for Ordinary and Partial Differential Equations*  
    🌊 Excelente para EDPs

### Recursos Online

- **SciPy Lecture Notes**: https://scipy-lectures.org/
- **NumPy User Guide**: https://numpy.org/doc/stable/user/
- **Computational Science Stack Exchange**: https://scicomp.stackexchange.com/
- **Netlib Repository**: http://www.netlib.org/ (códigos numéricos clássicos)

### Artigos Seminais

- Wilkinson, J.H. (1971). "Modern Error Analysis"
- Golub, G.H. & Kahan, W. (1965). "Calculating the Singular Values and Pseudo-Inverse of a Matrix"
- Hestenes, M.R. & Stiefel, E. (1952). "Methods of Conjugate Gradients for Solving Linear Systems"

---

## 🎓 Como Usar Este Material

### Para Estudantes de Graduação

1. **Semanas 1-2**: Introdução ao Python e NumPy
   - Trabalhe o notebook `01 - Introdução_Python-mn.ipynb`
   - Execute todos os exemplos e experimente variações

2. **Semanas 3-4**: Entenda erros e limitações computacionais
   - Estude `02 - Noções_de_erros.ipynb`
   - Faça experimentos com aritmética de ponto flutuante

3. **Semanas 5-6**: Método dos Mínimos Quadrados
   - Trabalhe `03 - MMQ.ipynb`
   - Aplique em datasets reais (ex: dados experimentais)

4. **Projeto Final**: Implemente um método numérico completo
   - Escolha: interpolação, integração ou EDO
   - Documente teoria, implementação e testes

### Para Estudantes de Pós-Graduação

1. **Aprofundamento Teórico**
   - Estude provas de convergência e estabilidade
   - Leia artigos originais sobre métodos específicos

2. **Implementação Avançada**
   - Compare diferentes algoritmos (performance, precisão)
   - Otimize código (vetorização, paralelização)

3. **Pesquisa**
   - Identifique problema aberto em sua área
   - Desenvolva ou adapte métodos numéricos
   - Publique resultados

### Para Professores

- **Material de Apoio**: Use notebooks como base para aulas
- **Exercícios**: Adapte exemplos para criar lista de exercícios
- **Projetos**: Proponha extensões dos tópicos abordados
- **Avaliação**: Use problemas práticos dos notebooks

### Metodologia de Estudo Recomendada

1. **📖 Leia a teoria** no notebook e nas referências
2. **💻 Execute o código** célula por célula
3. **🔬 Experimente** mudando parâmetros e dados
4. **✍️ Implemente** do zero para consolidar
5. **🎯 Aplique** em problemas reais da sua área
6. **🤔 Questione** e busque entender o "porquê"

---

## 🛠️ Exercícios Propostos

### Nível Básico
1. Implemente o método da bissecção para encontrar raízes de f(x) = x³ - 2x - 5
2. Compare precisão de float32 vs float64 em cálculos acumulativos
3. Ajuste uma parábola a um conjunto de dados usando MMQ

### Nível Intermediário
4. Implemente o método de Newton-Raphson com critério de parada adaptativo
5. Resolva um sistema linear 5×5 usando eliminação de Gauss com pivotamento parcial
6. Implemente integração numérica usando regra de Simpson composta

### Nível Avançado
7. Implemente o método de Runge-Kutta de 4ª ordem para resolver EDOs
8. Desenvolva um solver para equação do calor 1D usando diferenças finitas
9. Implemente o método do gradiente conjugado e compare com métodos diretos

### Projetos de Pesquisa (Pós-Graduação)
10. Paralelizar um método iterativo usando MPI ou CUDA
11. Desenvolver precondicionador para acelerar convergência de GMRES
12. Implementar método adaptativo para EDPs

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Este é um material educacional em constante evolução.

### Como Contribuir
- 🐛 Reportar erros ou inconsistências
- 📝 Adicionar novos notebooks com tópicos
- 💡 Sugerir melhorias pedagógicas
- 🌍 Traduzir conteúdo
- 📚 Adicionar referências e recursos

---

## 📞 Suporte e Contato

- **Dúvidas**: Abra uma issue no repositório
- **Discussões**: Use a área de discussions do GitHub
- **Contribuições**: Submeta pull requests

---

## 📄 Licença

Este material é disponibilizado para fins **educacionais**. Sinta-se livre para usar, modificar e compartilhar, citando a fonte original.

---

## 🏆 Agradecimentos

Este material foi desenvolvido com o objetivo de democratizar o acesso ao conhecimento em Métodos Numéricos, contribuindo para a formação de cientistas, engenheiros e pesquisadores.

---

<div align="center">

**🧮 Desenvolvido para formar os cientistas computacionais do futuro**

*Da graduação ao doutorado, uma jornada completa em Métodos Numéricos*

⭐ **Se este material foi útil, considere dar uma estrela no repositório!** ⭐

</div>

---

## 📈 Roadmap

### Em Desenvolvimento
- 🔄 Notebooks sobre interpolação e splines
- 🔄 Métodos para EDOs (Runge-Kutta, multipasso)
- 🔄 Integração numérica (trapézios, Simpson, Gauss)
- 🔄 Decomposições matriciais (LU, QR, SVD)

### Planejado
- 📅 Métodos para EDPs (diferenças finitas, elementos finitos)
- 📅 Otimização numérica
- 📅 Métodos de Monte Carlo
- 📅 Exemplos com problemas reais de pesquisa

---

**Última atualização:** Novembro 2025