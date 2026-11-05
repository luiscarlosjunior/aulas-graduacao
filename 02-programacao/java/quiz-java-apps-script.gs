/**
 * ============================================================================
 *  QUIZ JAVA — 50 questões em 5 seções
 *    1) Básico do Java (variáveis, tipos de dados, controle de fluxo)
 *    2) Classes
 *    3) Classes abstratas
 *    4) Pilares da Orientação a Objetos
 *    5) Interfaces
 * ============================================================================
 *
 *  COMO USAR:
 *   1. Acesse https://script.google.com  →  Novo projeto.
 *   2. Apague o conteúdo padrão e cole ESTE arquivo inteiro.
 *   3. Execute a função `criarFormularioJava`.
 *      (Na primeira vez o Google pede autorização — aceite com sua conta.)
 *   4. Em "Execução" / "Logs" (Ctrl+Enter) aparecem os links do formulário
 *      (Editar e Responder).
 *
 *  CARACTERÍSTICAS:
 *   • 50 questões (10 por seção), cada uma com CONTEXTO em forma de diálogo
 *     (professor-aluno, aluno-aluno, sênior-júnior, gerente-cliente...).
 *   • 5 alternativas, apenas 1 correta.
 *   • Posição da correta CONTROLADA: 10 de cada letra A–E, nunca duas iguais
 *     seguidas → sem "vários A em sequência".
 *   • 1 ponto por questão (modo quiz) → total de 50 pontos.
 *   • Cada questão leva feedback com a EXPLICAÇÃO da resposta.
 * ============================================================================
 */

function criarFormularioJava() {
  var form = FormApp.create('Quiz Java — Básico, Classes, Abstratas, Pilares da OO e Interfaces');

  form.setIsQuiz(true)
      .setDescription(
        'Prova dividida em 5 seções. Responda pensando em SITUAÇÕES REAIS de projeto — ' +
        'cada questão traz um diálogo de contexto. São 50 questões (1 ponto cada), ' +
        'uma só alternativa correta. ☕')
      .setProgressBar(true)
      .setAllowResponseEdits(false)
      .setCollectEmail(true);

  // Total de perguntas (todas as seções somadas).
  var total = SECOES.reduce(function (acc, s) { return acc + s.perguntas.length; }, 0);

  // --------------------------------------------------------------------------
  // Posições CONTROLADAS da alternativa correta (0 = A ... 4 = E).
  // Fórmula (floor(i/5) + (i%5)) % 5 → distribuição balanceada (10 de cada em
  // 50 questões) e SEM duas posições iguais consecutivas.
  // --------------------------------------------------------------------------
  var posicoes = [];
  for (var k = 0; k < total; k++) {
    posicoes.push((Math.floor(k / 5) + (k % 5)) % 5);
  }

  var idx = 0;
  SECOES.forEach(function (secao, s) {
    // Primeira seção fica na página 1 (cabeçalho); as demais abrem nova página.
    if (s === 0) {
      form.addSectionHeaderItem().setTitle(secao.titulo).setHelpText(secao.descricao);
    } else {
      form.addPageBreakItem().setTitle(secao.titulo).setHelpText(secao.descricao);
    }

    secao.perguntas.forEach(function (q) {
      if (q.distratores.length !== 4) {
        throw new Error('A questão "' + q.enunciado.slice(0, 30) +
                        '..." precisa ter exatamente 4 distratores.');
      }

      var item = form.addMultipleChoiceItem();

      // Insere a alternativa correta na posição controlada, entre os distratores.
      var opcoes = q.distratores.slice();
      opcoes.splice(posicoes[idx], 0, q.correta);
      idx++;

      var choices = opcoes.map(function (texto) {
        return item.createChoice(texto, texto === q.correta);
      });

      item.setTitle(q.enunciado)
          .setChoices(choices)
          .setPoints(1)
          .setRequired(true);

      if (q.explicacao) {
        var fb = FormApp.createFeedback().setText(q.explicacao).build();
        item.setFeedbackForCorrect(fb);
        item.setFeedbackForIncorrect(fb);
      }
    });
  });

  Logger.log('✅ Formulário criado com ' + total + ' questões (' + SECOES.length + ' seções).');
  Logger.log('✏️  Editar:    ' + form.getEditUrl());
  Logger.log('📨 Responder:  ' + form.getPublishedUrl());
}

/* ===========================================================================
 *  BANCO DE QUESTÕES (por seção)
 *   - enunciado:   contexto (diálogo) + a pergunta
 *   - correta:     a ÚNICA alternativa correta
 *   - distratores: 4 alternativas erradas (mas plausíveis)
 *   - explicacao:  feedback mostrado após responder
 * =========================================================================== */
var SECOES = [

  /* ========================================================================
   * SEÇÃO 1 — BÁSICO DO JAVA (variáveis, tipos de dados, controle de fluxo)
   * ====================================================================== */
  {
    titulo: 'Seção 1 — Básico do Java',
    descricao: 'Variáveis, tipos de dados e controle de fluxo.',
    perguntas: [
      {
        enunciado:
          '🧑‍💼 Júnior → sênior:\n“Qual destes é um tipo PRIMITIVO em Java, e não um tipo por referência?”',
        correta: 'int',
        distratores: ['String', 'Integer', 'ArrayList', 'Scanner'],
        explicacao:
          'Primitivos (int, double, boolean, char...) guardam o valor direto. String, Integer, ' +
          'ArrayList e Scanner são tipos por referência (objetos).'
      },
      {
        enunciado:
          '👩‍🏫 Professora → turma:\n“Quero uma variável cujo valor NÃO pode mudar depois de atribuído. Como declaro?”',
        correta: 'final int MAX = 10;',
        distratores: ['const int MAX = 10;', 'static int MAX = 10;', 'var MAX = 10; // imutável', 'readonly int MAX = 10;'],
        explicacao:
          'Em Java, constantes usam a palavra-chave final. const e readonly não existem em Java; ' +
          'static apenas torna o campo de classe, sem impedir alteração.'
      },
      {
        enunciado:
          '👥 Aluno A → Aluno B:\n“Rodei System.out.println(5 / 2) e imprimiu 2, não 2.5. Por quê?”',
        correta: 'Porque 5 e 2 são int: a divisão entre inteiros em Java descarta a parte decimal.',
        distratores: [
          'Porque o Java arredonda todo resultado para baixo sempre.',
          'Porque faltou importar a biblioteca de matemática.',
          'Porque println só mostra números inteiros.',
          'Porque 5/2 deveria dar erro de compilação.'
        ],
        explicacao:
          'int / int = int (divisão inteira). Para obter 2.5 use um double: 5.0 / 2 ou (double) 5 / 2.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“Você escreveu char letra = \"A\"; e deu erro. Qual a regra?”',
        correta: 'Aspas duplas formam String; char usa aspas simples: char letra = \'A\';',
        distratores: [
          'char não existe em Java, use byte.',
          'Tanto faz aspas simples ou duplas para char.',
          'char só aceita números, nunca letras.',
          'O erro é porque faltou ponto e vírgula.'
        ],
        explicacao:
          "'A' é um char (um caractere). \"A\" é uma String (texto). São tipos diferentes."
      },
      {
        enunciado:
          '🧑‍💼 Júnior → sênior (vindo do C):\n“Em C eu fazia if (x) com x inteiro. Em Java deu erro de compilação. Por quê?”',
        correta: 'Porque a condição do if em Java precisa ser um boolean; um int não é convertido para boolean.',
        distratores: [
          'Porque o Java não tem if, só switch.',
          'Porque faltou escrever if (x == true).',
          'Porque int não pode aparecer dentro de parênteses.',
          'Porque o Java exige chaves {} obrigatórias no if.'
        ],
        explicacao:
          'Diferente do C, Java é estrito: a condição deve ser boolean. Use if (x != 0), por exemplo.'
      },
      {
        enunciado:
          '👥 Aluno → aluno:\n“No meu switch, caí no case 1 e ele executou também o case 2 e o 3! O que houve?”',
        correta: 'Faltou break: sem ele o switch faz fall-through e segue executando os próximos cases.',
        distratores: [
          'O switch sempre executa todos os cases, é normal.',
          'Faltou um else no final do switch.',
          'case 1 chama case 2 automaticamente por herança.',
          'É um bug da JVM, basta recompilar.'
        ],
        explicacao:
          'Em Java o switch “cai” para o próximo case até encontrar um break (ou o fim). Coloque break ' +
          'em cada case para evitar o fall-through.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“Para percorrer todos os elementos de uma List<Musica> sem usar índice, qual a forma mais limpa?”',
        correta: 'for (Musica m : lista) { ... }  // for-each',
        distratores: [
          'for (int i = 0; i <= lista.length; i++) { ... }',
          'foreach (Musica m in lista) { ... }',
          'loop (Musica m : lista) { ... }',
          'while (lista) { ... }'
        ],
        explicacao:
          'O for-each (for (Tipo x : colecao)) percorre a coleção sem índice. Observação: lista usa ' +
          '.size() (não .length, que é de arrays) e o índice vai até size()-1.'
      },
      {
        enunciado:
          '👩‍🏫 Professora:\n“int para double o Java converte sozinho, mas double para int exige algo. O quê?”',
        correta: 'Um cast explícito: int x = (int) 3.9; (e o valor é truncado para 3).',
        distratores: [
          'Nada: o Java converte double para int automaticamente.',
          'A função parseInt(double).',
          'Declarar o int como final.',
          'Importar java.lang.Convert.'
        ],
        explicacao:
          'int→double é widening (automático). double→int é narrowing: pode perder dados, então exige ' +
          'cast explícito (int) e trunca a parte decimal.'
      },
      {
        enunciado:
          '🧑‍💼 Júnior → sênior:\n“Comparei duas Strings iguais com == e deu false. Como comparo o CONTEÚDO?”',
        correta: 'Com equals: s1.equals(s2) compara o conteúdo; == compara referências (endereços).',
        distratores: [
          'Com ===, como em JavaScript.',
          'Com compareTo, que devolve true/false.',
          'Com s1 = s2, que testa igualdade.',
          '== já compara conteúdo; o false indica outro bug.'
        ],
        explicacao:
          'Para objetos, == compara se são o MESMO objeto na memória. Para comparar valor de String, ' +
          'use equals (ou equalsIgnoreCase).'
      },
      {
        enunciado:
          '👥 Aluno → aluno:\n“Declarei int soma dentro do for e tentei imprimir soma depois do for — não compila. Por quê?”',
        correta: 'Por causa do escopo: uma variável declarada dentro do bloco só existe dentro dele.',
        distratores: [
          'Porque variáveis de for são sempre static.',
          'Porque faltou inicializar soma com 0.',
          'Porque int não pode ser usado em laços.',
          'Porque o for apaga todas as variáveis ao terminar a JVM.'
        ],
        explicacao:
          'O escopo de uma variável é o bloco { } onde ela foi declarada. Para usá-la após o for, ' +
          'declare-a antes do for.'
      }
    ]
  },

  /* ========================================================================
   * SEÇÃO 2 — CLASSES
   * ====================================================================== */
  {
    titulo: 'Seção 2 — Classes',
    descricao: 'Classes, objetos, construtores, encapsulamento e membros.',
    perguntas: [
      {
        enunciado:
          '👩‍🏫 Professora → turma:\n“Qual a diferença entre uma CLASSE e um OBJETO?”',
        correta: 'A classe é o molde (o tipo); o objeto é uma instância concreta criada a partir dela.',
        distratores: [
          'São a mesma coisa, só muda o nome.',
          'A classe é a instância; o objeto é o molde.',
          'Classe é usada em tempo de execução; objeto só em compilação.',
          'Objeto é um método especial da classe.'
        ],
        explicacao:
          'Classe = planta/molde (ex.: Ouvinte). Objeto = instância concreta (ex.: a ana, criada com new Ouvinte(...)).'
      },
      {
        enunciado:
          '🧑‍💼 Júnior → sênior:\n“Para que serve o construtor de uma classe?”',
        correta: 'Para inicializar o objeto no momento da criação (atribuir estado inicial).',
        distratores: [
          'Para destruir o objeto quando não é mais usado.',
          'Para declarar os atributos da classe.',
          'Para herdar de outra classe automaticamente.',
          'Para compilar a classe mais rápido.'
        ],
        explicacao:
          'O construtor roda ao fazer new e serve para deixar o objeto num estado inicial válido ' +
          '(ex.: definir nome, e-mail, saldo inicial).'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“Explique o que exatamente acontece ao executar new ContaBancaria(\"123\", \"Ana\", 50);”',
        correta: 'O Java aloca memória para o objeto e chama o construtor para inicializá-lo, devolvendo uma referência.',
        distratores: [
          'Apenas declara uma variável, sem criar nada.',
          'Chama um método estático chamado ContaBancaria.',
          'Cria uma cópia da classe inteira em disco.',
          'Executa o método main da classe.'
        ],
        explicacao:
          'new aloca o objeto no heap, executa o construtor com os argumentos e retorna a referência ' +
          'para a variável.'
      },
      {
        enunciado:
          '👥 Aluno → aluno:\n“No construtor escrevi this.nome = nome;. Por que esse this?”',
        correta: 'Para distinguir o atributo (this.nome) do parâmetro de mesmo nome (nome).',
        distratores: [
          'this cria um novo objeto a cada linha.',
          'this é obrigatório em toda atribuição em Java.',
          'this chama o construtor da superclasse.',
          'this transforma o atributo em estático.'
        ],
        explicacao:
          'Quando parâmetro e atributo têm o mesmo nome, this.nome refere-se ao atributo do objeto; ' +
          'nome (sem this) é o parâmetro.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior (revisando a ContaBancaria do Melodia):\n“Por que o atributo saldo é private em vez de public?”',
        correta: 'Para encapsular: ninguém altera o saldo direto; mudanças passam por métodos que validam a regra.',
        distratores: [
          'Porque private deixa o acesso mais rápido.',
          'Porque atributos public não existem em Java.',
          'Porque private permite herança múltipla.',
          'Por puro estilo; não muda nada na prática.'
        ],
        explicacao:
          'private protege o invariante (ex.: “saldo nunca negativo”). O acesso controlado por métodos ' +
          '(depositar/sacar) é a essência do encapsulamento.'
      },
      {
        enunciado:
          '👥 Aluno A → Aluno B:\n“Minha classe tem dois métodos somar: um com (int,int) e outro com (double,double). Isso é...?”',
        correta: 'Sobrecarga (overloading): mesmo nome, listas de parâmetros diferentes.',
        distratores: [
          'Sobrescrita (overriding).',
          'Herança múltipla.',
          'Um erro de compilação (nomes duplicados).',
          'Polimorfismo de interface.'
        ],
        explicacao:
          'Overloading é ter vários métodos de mesmo nome com parâmetros diferentes. Overriding ' +
          '(sobrescrita) é redefinir, na subclasse, um método herdado.'
      },
      {
        enunciado:
          '🧑‍💼 Júnior → sênior:\n“Quero um contador que seja compartilhado por TODAS as instâncias da classe, não um por objeto. Uso o quê?”',
        correta: 'Um atributo static (de classe), compartilhado por todas as instâncias.',
        distratores: [
          'Um atributo final, que é compartilhado.',
          'Um atributo private comum.',
          'Um parâmetro do construtor.',
          'Uma variável local do método.'
        ],
        explicacao:
          'static pertence à CLASSE, não a cada objeto — por isso o mesmo valor é visto por todas as ' +
          'instâncias (ideal para um contador global).'
      },
      {
        enunciado:
          '👩‍🏫 Professora:\n“Chamo Math.max(3, 7) sem nunca escrever new Math(). Como isso é possível?”',
        correta: 'Porque max é um método static: pertence à classe e é chamado sem instanciar.',
        distratores: [
          'Porque o Java cria um objeto Math escondido.',
          'Porque Math é uma variável global.',
          'Porque métodos sem static também dispensam new.',
          'Porque Math é uma palavra reservada.'
        ],
        explicacao:
          'Métodos static são chamados pela classe (Math.max), sem precisar de objeto. Métodos de ' +
          'instância exigem um objeto criado com new.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior (debugando):\n“Deu NullPointerException na linha conta.sacar(10). Qual a causa mais provável?”',
        correta: 'A variável conta está null: aponta para nenhum objeto, e tentamos chamar um método nela.',
        distratores: [
          'O valor 10 é grande demais para int.',
          'O método sacar não existe na classe.',
          'Faltou declarar conta como static.',
          'A conta tem saldo insuficiente.'
        ],
        explicacao:
          'NullPointerException ocorre ao acessar um membro de uma referência null. Garanta que conta ' +
          'foi inicializada (new ...) antes de usá-la.'
      },
      {
        enunciado:
          '👥 Aluno → aluno:\n“Quando dou System.out.println(minhaConta) aparece algo como ContaBancaria@7a81. Como mostrar algo legível?”',
        correta: 'Sobrescrevendo o método toString() na classe para devolver uma String descritiva.',
        distratores: [
          'Trocando println por print.',
          'Declarando a classe como public.',
          'Usando um atributo static chamado texto.',
          'Isso é impossível em Java.'
        ],
        explicacao:
          'Por padrão toString() mostra Classe@hash. Sobrescreva-o (@Override public String toString()) ' +
          'para uma representação textual útil do objeto.'
      }
    ]
  },

  /* ========================================================================
   * SEÇÃO 3 — CLASSES ABSTRATAS
   * ====================================================================== */
  {
    titulo: 'Seção 3 — Classes Abstratas',
    descricao: 'Classes e métodos abstratos, quando e por que usá-los.',
    perguntas: [
      {
        enunciado:
          '🧑‍💼 Júnior → sênior:\n“Tentei new Usuario(...) e o compilador não deixou. Usuario é abstract. Por quê?”',
        correta: 'Classes abstratas não podem ser instanciadas diretamente; só por meio de subclasses concretas.',
        distratores: [
          'Porque faltou importar a classe Usuario.',
          'Porque o construtor estava private.',
          'Porque toda classe abstrata é também final.',
          'Porque new só funciona com interfaces.'
        ],
        explicacao:
          'abstract significa “incompleta/base”: você instancia uma subclasse concreta (ex.: new ' +
          'Ouvinte(...)), nunca a abstrata em si.'
      },
      {
        enunciado:
          '👩‍🏫 Professora → turma:\n“O que caracteriza um método abstrato?”',
        correta: 'É declarado sem corpo (sem { }) e obriga as subclasses concretas a implementá-lo.',
        distratores: [
          'É um método que não pode ser chamado nunca.',
          'É um método static sem retorno.',
          'É um método com corpo que não pode ser sobrescrito.',
          'É um método privado da superclasse.'
        ],
        explicacao:
          'abstract void tipoDePerfil(); não tem corpo. Cada subclasse concreta é obrigada a fornecer ' +
          'a implementação — base do polimorfismo.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“Diferente de uma interface (clássica), o que uma classe abstrata PODE ter?”',
        correta: 'Pode ter estado (atributos de instância), construtor e métodos concretos, além dos abstratos.',
        distratores: [
          'Só pode ter constantes, como a interface.',
          'Nada além de métodos abstratos.',
          'Pode ser instanciada normalmente com new.',
          'Pode herdar de várias classes ao mesmo tempo.'
        ],
        explicacao:
          'A classe abstrata combina abstração com implementação: carrega atributos, construtor e ' +
          'métodos concretos compartilhados pelas subclasses.'
      },
      {
        enunciado:
          '🧑‍💼 Júnior → sênior:\n“Quando devo escolher uma CLASSE ABSTRATA em vez de uma interface?”',
        correta: 'Quando há relação “É-UM” com estado e código comum para compartilhar entre as subclasses.',
        distratores: [
          'Quando preciso de herança múltipla de tipos.',
          'Sempre: classe abstrata é melhor que interface.',
          'Quando não quero nenhum método em comum.',
          'Quando a classe não terá subclasses.'
        ],
        explicacao:
          'Classe abstrata = “É-UM” + estado/código comum. Interface = “PODE-FAZER” (contrato) e ' +
          'permite múltipla implementação.'
      },
      {
        enunciado:
          '👥 Aluno → aluno:\n“Estendi uma classe abstrata mas não implementei um dos métodos abstratos. O que o compilador exige?”',
        correta: 'Ou implemento todos os métodos abstratos, ou declaro a própria subclasse como abstract também.',
        distratores: [
          'Nada: métodos abstratos são opcionais.',
          'Preciso apagar o método abstrato da superclasse.',
          'Devo marcar a subclasse como final.',
          'Basta chamar super() no construtor.'
        ],
        explicacao:
          'Uma subclasse concreta precisa implementar TODOS os métodos abstratos herdados; se não ' +
          'implementar algum, ela mesma tem de ser abstract.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“Classe abstrata pode ter construtor? Se sim, para quê, já que não posso instanciá-la?”',
        correta: 'Pode: o construtor é chamado via super(...) pela subclasse para inicializar o estado herdado.',
        distratores: [
          'Não pode ter construtor, pois não é instanciável.',
          'Pode, mas o construtor nunca é executado.',
          'Pode, e serve para instanciar a abstrata diretamente.',
          'Só pode ter construtor se for também final.'
        ],
        explicacao:
          'Mesmo sem new direto, o construtor da abstrata roda quando a subclasse é criada (via super), ' +
          'inicializando os atributos comuns.'
      },
      {
        enunciado:
          '👩‍🏫 Professora:\n“Por que não existe uma classe abstract final?”',
        correta: 'Porque abstract exige ser estendida e final proíbe herança — são contraditórios.',
        distratores: [
          'Porque final só vale para atributos.',
          'Porque abstract e final significam a mesma coisa.',
          'Porque toda classe final já é abstrata.',
          'Porque o Java não tem a palavra final.'
        ],
        explicacao:
          'abstract = “precisa ser completada por subclasses”; final = “não pode ter subclasses”. ' +
          'Juntas não fazem sentido, e o compilador recusa.'
      },
      {
        enunciado:
          '🧑‍💼 Júnior → sênior:\n“Marquei um atributo da classe abstrata como protected. O que isso permite?”',
        correta: 'Que as subclasses (e o mesmo pacote) acessem o atributo diretamente, mas não o resto do mundo.',
        distratores: [
          'Que qualquer classe acesse o atributo.',
          'Que só a própria classe acesse (igual a private).',
          'Que o atributo vire constante.',
          'Que o atributo seja compartilhado como static.'
        ],
        explicacao:
          'protected é o meio-termo: visível para subclasses e para o pacote, útil para expor estado ' +
          'herdado sem torná-lo público a todos.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“Na classe abstrata escrevi um método concreto processar() que chama etapas abstratas implementadas pelas filhas. Que padrão é esse?”',
        correta: 'Template Method: a superclasse fixa o esqueleto do algoritmo e delega os passos variáveis às subclasses.',
        distratores: [
          'Singleton.',
          'Factory puro.',
          'Observer.',
          'Sobrecarga de operadores.'
        ],
        explicacao:
          'Template Method define o fluxo (muitas vezes em um método final) e deixa partes específicas ' +
          'como métodos abstratos preenchidos pelas subclasses.'
      },
      {
        enunciado:
          '👥 Aluno A → Aluno B:\n“Qual a diferença essencial entre uma classe ABSTRATA e uma classe CONCRETA?”',
        correta: 'A concreta pode ser instanciada e implementa tudo; a abstrata não pode e pode deixar métodos sem implementação.',
        distratores: [
          'A abstrata é mais rápida em tempo de execução.',
          'A concreta não pode ter métodos.',
          'A abstrata não pode ter atributos.',
          'Não há diferença prática entre elas.'
        ],
        explicacao:
          'Concreta = completa e instanciável (new). Abstrata = base incompleta: não instancia e pode ' +
          'ter métodos abstratos a serem completados.'
      }
    ]
  },

  /* ========================================================================
   * SEÇÃO 4 — PILARES DA ORIENTAÇÃO A OBJETOS
   * ====================================================================== */
  {
    titulo: 'Seção 4 — Pilares da Orientação a Objetos',
    descricao: 'Abstração, encapsulamento, herança e polimorfismo.',
    perguntas: [
      {
        enunciado:
          '👩‍🏫 Professora → turma:\n“Quais são os quatro pilares da Orientação a Objetos?”',
        correta: 'Abstração, encapsulamento, herança e polimorfismo.',
        distratores: [
          'Variáveis, métodos, classes e objetos.',
          'Herança, recursão, iteração e polimorfismo.',
          'Compilação, execução, depuração e teste.',
          'Abstração, concorrência, herança e serialização.'
        ],
        explicacao:
          'Os quatro pilares são abstração, encapsulamento, herança e polimorfismo.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior (ContaBancaria do Melodia):\n“saldo é private e só muda por depositar/sacar, que validam. Que pilar é esse?”',
        correta: 'Encapsulamento.',
        distratores: ['Herança.', 'Polimorfismo.', 'Abstração especulativa.', 'Sobrecarga.'],
        explicacao:
          'Encapsulamento = esconder o estado (private) e expor acesso controlado por métodos que ' +
          'protegem os invariantes.'
      },
      {
        enunciado:
          '👥 Aluno → aluno:\n“Ouvinte e Artista reaproveitam nome e e-mail escrevendo extends Usuario. Que pilar está em jogo?”',
        correta: 'Herança (relação É-UM: Ouvinte É-UM Usuario).',
        distratores: [
          'Encapsulamento.',
          'Composição.',
          'Polimorfismo de sobrecarga.',
          'Abstração de dados.'
        ],
        explicacao:
          'Herança permite que uma subclasse reutilize atributos e comportamentos do pai, modelando ' +
          'a relação “É-UM”.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“A mesma chamada reproduzir() se comporta diferente em Musica e em Podcast. Que pilar é esse?”',
        correta: 'Polimorfismo.',
        distratores: [
          'Encapsulamento.',
          'Herança múltipla.',
          'Sobrecarga de construtor.',
          'Abstração estrutural.'
        ],
        explicacao:
          'Polimorfismo: uma mesma mensagem (reproduzir()) produz comportamentos diferentes conforme ' +
          'o tipo real do objeto.'
      },
      {
        enunciado:
          '👩‍🏫 Professora:\n“Modelo o Ouvinte guardando só nome, e-mail e assinatura — ignoro altura, cor dos olhos etc. Que pilar aplico?”',
        correta: 'Abstração (foco no essencial ao problema, ignorando o irrelevante).',
        distratores: [
          'Encapsulamento.',
          'Polimorfismo.',
          'Herança.',
          'Serialização.'
        ],
        explicacao:
          'Abstração é recortar a realidade: manter o que importa ao sistema e descartar o resto.'
      },
      {
        enunciado:
          '🧑‍💼 Júnior → sênior:\n“Coloquei @Override no método da subclasse para redefinir o do pai. Isso habilita o quê em tempo de execução?”',
        correta: 'Polimorfismo: a JVM chama a versão da subclasse mesmo por uma referência do tipo pai.',
        distratores: [
          'Sobrecarga, resolvida em tempo de compilação.',
          'Encapsulamento do método.',
          'Herança múltipla de classes.',
          'Nada; @Override é só decorativo.'
        ],
        explicacao:
          'Sobrescrita (@Override) é a base do polimorfismo em tempo de execução: a chamada resolve ' +
          'para a implementação do tipo real do objeto.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“Se saldo fosse public, alguém faria conta.saldo = -999 sem passar por validação. Que pilar estaríamos violando?”',
        correta: 'Encapsulamento — o estado ficaria exposto e os invariantes, desprotegidos.',
        distratores: [
          'Herança.',
          'Polimorfismo.',
          'Abstração.',
          'Nenhum; atributo public é boa prática.'
        ],
        explicacao:
          'Expor o atributo quebra o encapsulamento: qualquer um altera o estado sem passar pelas ' +
          'regras (ex.: impedir saldo negativo).'
      },
      {
        enunciado:
          '👥 Aluno A → Aluno B:\n“Escrevi Usuario u = new Ouvinte(...). Posso?”',
        correta: 'Pode: é upcasting — uma referência do tipo pai (Usuario) apontando para um objeto filho (Ouvinte).',
        distratores: [
          'Não: os tipos são incompatíveis.',
          'Pode, mas só com cast explícito (Usuario).',
          'Pode, mas o objeto vira do tipo Usuario de verdade.',
          'Só funciona se Usuario for final.'
        ],
        explicacao:
          'Upcasting é implícito e seguro: o objeto continua sendo um Ouvinte; a referência Usuario ' +
          'apenas enxerga a “parte Usuario”. É o que viabiliza o polimorfismo.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“Um Carro ‘tem um’ Motor, não ‘é um’ Motor. Herança ou composição?”',
        correta: 'Composição: Carro TEM-UM Motor (um atributo), pois não há relação “É-UM”.',
        distratores: [
          'Herança: Carro extends Motor.',
          'Polimorfismo de interface obrigatório.',
          'Tanto faz, dá no mesmo.',
          'Associação estática via static.'
        ],
        explicacao:
          'Use herança para “É-UM” e composição para “TEM-UM”. Forçar herança onde a relação é ' +
          '“TEM-UM” gera hierarquias erradas.'
      },
      {
        enunciado:
          '👩‍🏫 Professora:\n“Uma List<FonteDeAudio> guarda músicas e podcasts, e eu chamo reproduzir() em cada um sem saber o tipo exato. Qual pilar torna isso possível?”',
        correta: 'Polimorfismo: tratar objetos de tipos diferentes de forma uniforme pelo tipo comum.',
        distratores: [
          'Encapsulamento.',
          'Herança múltipla de classes.',
          'Abstração especulativa.',
          'Sobrecarga de métodos.'
        ],
        explicacao:
          'O polimorfismo permite que o mesmo código (for + reproduzir()) funcione para qualquer ' +
          'FonteDeAudio, cada uma reproduzindo do seu jeito.'
      }
    ]
  },

  /* ========================================================================
   * SEÇÃO 5 — INTERFACES
   * ====================================================================== */
  {
    titulo: 'Seção 5 — Interfaces',
    descricao: 'Contratos, múltipla implementação, default methods e desacoplamento.',
    perguntas: [
      {
        enunciado:
          '👩‍🏫 Professora → turma:\n“Em uma frase: o que uma interface representa?”',
        correta: 'Um contrato: diz O QUE uma classe deve fazer, sem dizer COMO fazer.',
        distratores: [
          'Uma classe pronta que já implementa tudo.',
          'Um objeto que guarda estado compartilhado.',
          'Um tipo de laço de repetição.',
          'Uma tela/interface gráfica do sistema.'
        ],
        explicacao:
          'Interface é um contrato de capacidades (métodos a cumprir). O “como” fica em cada classe ' +
          'que a implementa.'
      },
      {
        enunciado:
          '🧑‍💼 Júnior → sênior:\n“Para uma classe cumprir uma interface, uso extends ou implements?”',
        correta: 'implements (classes implementam interfaces; extends é para herdar de outra classe).',
        distratores: [
          'extends, igual à herança de classe.',
          'inherits.',
          'uses.',
          'Tanto faz, os dois funcionam.'
        ],
        explicacao:
          'class X implements Contrato. Uma interface pode, sim, extends outra interface, mas a ' +
          'CLASSE usa implements.'
      },
      {
        enunciado:
          '👥 Aluno → aluno:\n“Java não deixa herdar de duas classes. E implementar duas interfaces, pode?”',
        correta: 'Pode: uma classe implementa várias interfaces ao mesmo tempo (múltipla implementação).',
        distratores: [
          'Não: também é limitado a uma interface.',
          'Só com um plugin do compilador.',
          'Pode, mas as interfaces precisam ser idênticas.',
          'Pode, desde que a classe seja abstrata.'
        ],
        explicacao:
          'class Cartao implements MeioDePagamento, Reembolsavel { ... }. Herança de classe é simples; ' +
          'de interface é múltipla.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“Numa interface clássica, declarei void enviar(); sem corpo. Qual a visibilidade e natureza desse método?”',
        correta: 'public e abstract implicitamente — mesmo sem escrever essas palavras.',
        distratores: [
          'private e final.',
          'protected e static.',
          'package-private e concreto.',
          'public, mas com corpo obrigatório.'
        ],
        explicacao:
          'Métodos de interface (sem default/static) são public abstract por padrão — o contrato é ' +
          'sempre público.'
      },
      {
        enunciado:
          '👩‍🏫 Professora:\n“A partir do Java 8, uma interface pode ter método com corpo. Como se chama?”',
        correta: 'default method: um método com implementação padrão dentro da interface.',
        distratores: [
          'Método abstrato com corpo.',
          'Construtor de interface.',
          'Método protected.',
          'Método final de instância.'
        ],
        explicacao:
          'default void planar() { ... } permite adicionar comportamento a uma interface sem quebrar ' +
          'as classes que já a implementavam.'
      },
      {
        enunciado:
          '🧑‍💼 Júnior → sênior:\n“Posso guardar um atributo de instância mutável (ex.: int contador) numa interface?”',
        correta: 'Não: interface não tem estado de instância; só constantes (public static final).',
        distratores: [
          'Sim, como em qualquer classe.',
          'Sim, desde que seja private.',
          'Sim, se a interface for pública.',
          'Sim, mas apenas do tipo String.'
        ],
        explicacao:
          'Campos em interface são constantes (public static final). Se você precisa de estado ' +
          'mutável compartilhado, use uma classe abstrata.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior (troca de gateway):\n“A Assinatura depende de MeioDePagamento (interface), não de Cartao direto. Que vantagem isso traz?”',
        correta: 'Desacoplamento: trocar/adicionar formas de pagamento não exige mudar a Assinatura.',
        distratores: [
          'O código fica mais lento, porém organizado.',
          'Passa a ser impossível testar a Assinatura.',
          'A Assinatura precisa conhecer cada classe de pagamento.',
          'Nenhuma; é só estética.'
        ],
        explicacao:
          'Programar contra a interface (contrato) permite trocar a implementação (Cartao, Pix, PayPal) ' +
          'sem tocar em quem a usa — e facilita testes com dublês.'
      },
      {
        enunciado:
          '👥 Aluno A → Aluno B:\n“Como decido entre herança (classe) e interface?”',
        correta: 'Pergunto “É-UM?” → herança; “PODE-FAZER?” (capacidade) → interface.',
        distratores: [
          'Uso sempre herança; interface é obsoleta.',
          'Uso interface só quando a classe é final.',
          'Escolho pela que tiver menos linhas.',
          'Interface quando há estado; herança quando não há.'
        ],
        explicacao:
          'Herança modela identidade (“É-UM”); interface modela capacidade (“PODE-FAZER”), permitindo ' +
          'que classes não relacionadas compartilhem um comportamento.'
      },
      {
        enunciado:
          '🧑‍🏫 Sênior → júnior:\n“Escrevi MeioDePagamento m = new Pix();. Isso é válido? Por quê?”',
        correta: 'Sim: uma referência do tipo da interface pode apontar para qualquer classe que a implemente.',
        distratores: [
          'Não: não se pode usar interface como tipo de variável.',
          'Só com cast: (MeioDePagamento) new Pix().',
          'Só se Pix for abstrata.',
          'Não: interface não permite polimorfismo.'
        ],
        explicacao:
          'Usar a interface como tipo da referência é o que viabiliza o polimorfismo: m.cobrar(...) ' +
          'chama a implementação de Pix.'
      },
      {
        enunciado:
          '👩‍🏫 Professora:\n“Email, SMS e Push não têm estado nem código em comum, mas todos ‘enviam’. E preciso que uma classe tenha várias capacidades. Classe abstrata ou interface?”',
        correta: 'Interface: não há estado/código comum e é preciso múltipla implementação de capacidades.',
        distratores: [
          'Classe abstrata, sempre que houver mais de um tipo.',
          'Classe concreta com muitos ifs.',
          'Herança múltipla de classes.',
          'Nenhuma; basta copiar o código em cada classe.'
        ],
        explicacao:
          'Sem estado/código comum e precisando de múltiplas capacidades, a interface é a escolha: ' +
          'define o contrato “enviar” e permite implementar vários contratos.'
      }
    ]
  }

];
