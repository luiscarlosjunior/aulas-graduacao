#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <float.h>
#include <stdbool.h>

#define PI 3.14159265358979323846

// Responsável por controlar o fluxo da aplicação
int main()
{
    float altura = 1.85;
    int idade = 27;
    char nome[] = "Roberto Souza";
    bool estaCodando = true;

    printf("Minha altura é de: %.2f\n", altura);
    printf("Minha idade é: %d\n", idade);
    printf("Meu nome é: %s\n", nome);
    printf("Estou codando: %d\n", estaCodando);
    
    return 0;
}