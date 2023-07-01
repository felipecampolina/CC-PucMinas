#include<stdio.h>

int main()
{


printf("\nIdentificar se o ano e bissexto");
printf("\nDigite um ano: ");
int x;
scanf("%d",&x);

if (x %4 == 0){
    printf("E bissexto");
}

else if ((x%100==0) && (x%400==0))
    printf("E bissexto");

else
    printf("Nao e bissexto");


}