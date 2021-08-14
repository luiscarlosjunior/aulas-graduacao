#include <stdio.h>
#include <stdlib.h>

void dobraValor(int *v1, int *v2);
void exemploPonteiro();
void exemploPonteiroDeclaracao();
void exemploPonteiroAtravesArgumentos();
void exemploPonteiroDePonteiro();

int main()
{
    exemploPonteiro();
    exemploPonteiroDePonteiro();
    exemploPonteiroAtravesArgumentos();
    return 0;
}

void exemploPonteiroAtravesArgumentos()
{
    int v1 = 25, v2 = 30;

    dobraValor(&v1, &v2);

    printf("O dobro do v1 eh: %d\n", v1);
    printf("O dobro do v2 eh: %d\n", v2);

}

void dobraValor(int *v1, int *v2)
{
    /** *v1 contém o valor antigo que será substituido pelo novo*/
    *v1 = *v1 * 2;
    *v2 = *v2 * 2;

    printf("O dobro do v1 eh: %d\n", *v1);
    printf("O dobro do v2 eh: %d\n", *v2);
}

void exemploPonteiroDePonteiro()
{
        // Ponteiros
    int valor;
    int *p = NULL;
    int **q;

    // Inicializando
    valor = 50;

    // Pegando a referência/endereço da var valor
    p = &valor;

    // Pegar o endereço da var p (ponteiro)
    q = &p;

    // Exibir o endereço
    printf("Endereco do valor1: %x\n", &valor);
    printf("Endereco do p: %x\n", p);
    printf("Endereco do q: %x\n", q);

    // Exibir os valores
    printf("Valor do valor1: %d\n", valor);
    printf("Valor do p: %d\n", *p);
    printf("Valor do p: %d\n", **q);

}

void exemploPonteiro()
{
    int *p = NULL;
    printf("Endereco do valor1: %x\n", p);

    // Verifica se ele é nulo
    if(!p){
        printf("Ponteiro está OK!");
    }
}

void exemploPonteiroDeclaracao()
{
    int valor = 50;
    // Declarando uma variável do tipo ponteiro
    int *p;

    // Recupera o endereço da variável
    p = &valor;

    printf("Endereco do valor1: %x\n", &valor);
    printf("Endereco do p: %x\n", p);
    printf("Valor da variavel eh: %d\n", *p);
}
