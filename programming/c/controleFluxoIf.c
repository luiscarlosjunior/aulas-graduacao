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

    // Controle de fluxo simples
    if(idade >= 18) {
        printf("Eh maior de idade");
    }

    printf("\n\n--------------------\n");

    // Controle de fluxo com if-else
    if (idade >= 25 && idade <= 35 ) {
        printf("Eh permitido a entrada");
    }
    else {
        printf("Nao eh permitido a entrada");
    }
   
    printf("\n\n--------------------\n");

    // Controle de fluxo com if-elseif-else
    if (idade >= 25 && idade <= 35 ) {
        printf("Eh permitido a entrada");
    }
    else if (idade >= 18 && idade < 25 ) {
        printf("Espere a sua vez");
    }
    else {
        printf("Nao eh permitido a entrada");
    }
    
    return 0;
}