#include <stdio.h>
#include <stdlib.h>

int main()
{

    printf("\n----Tabuada do 10----\n");

    int tabuada = 0;
    printf("\n----Usando for----\n");
    for(tabuada = 1;tabuada <= 10; tabuada++) {
        printf("10 * %d = %d\n",tabuada, tabuada * 10);
    }

    printf("\n----Usando while----\n");
    tabuada = 0;
    while(tabuada <= 10)
    {
        printf("10 * %d = %d\n",tabuada, tabuada * 10);
        tabuada++;
    }

    printf("\n----Usando do-while----\n");
    tabuada = 0;
    do
    {
        printf("10 * %d = %d\n",tabuada, tabuada * 10);
        tabuada++;
    } while(tabuada <= 10);    
    return 0;
}
