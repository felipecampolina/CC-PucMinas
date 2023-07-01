#include<iostream>
const int  TAM = 3;


struct Retangulo{
    float base;
    float lado;
};
void leiaRetangulos(Retangulo retangulo[]){
    float lado;
    float base;

    for(int i= 0 ; i<TAM; i++){
    printf("\nLado %i: ",i+1);
    scanf("%f",&lado);
    retangulo[i].lado = lado;
    printf("\nBase %i: ",i+1);
    scanf("%f",&base);
    retangulo[i].base = base;
    
    }

}
void escrevaRetangulos(Retangulo retangulo[]){
    for(int i = 0 ; i<TAM ; i++){
        printf("\nLado %i: %1.f",i+1,retangulo[i].lado);
        printf("\nBase %i: %1.f",i+1,retangulo[i].base);
    }
}
void leiaRet(Retangulo* retangulo[]){
    float lado;
    float base;


    for(int i= 0 ; i<TAM; i++){
    printf("\nLado %i: ",i+1);
    scanf("%f",&lado);
    retangulo[i]->lado = lado;
    printf("\nBase %i: ",i+1);
    scanf("%f",&base);
    retangulo[i]->base = base;
    
    }

}
void escrevaRet(Retangulo *retangulo[]){
    for(int i = 0 ; i<TAM ; i++){
        printf("\nLado %i: %1.f",i+1,retangulo[i]->lado);
        printf("\nBase %i: %1.f",i+1,retangulo[i]->base);
    }
}
float calculaListaArea(Retangulo *retangulo[]){
    float area;
    for(int i = 0 ; i<TAM ; i++){
        area = retangulo[i]->lado * retangulo[i]->base;
        printf("\nArea ret %i = %.1f",i+1,area);
    }
}
float somaAreaInterativo(Retangulo *retangulo[]){
    float area;
    float soma = 0;
    for(int i = 0 ; i<TAM ; i++){
        area = retangulo[i]->lado * retangulo[i]->base;
        printf("\nArea ret %i = %.1f",i+1,area);
        soma += area;
        if(i==TAM-1)printf("\nA soma de todas areas = %f",soma);
    }
}
float somaAreaRecursivo(Retangulo *retangulo[],int i,float soma){
    float area ;

    if(i!=-1){
        area = area = retangulo[i]->lado * retangulo[i]->base;
        soma += area;
        if(i==0)printf("Area total: %f",soma);
        somaAreaRecursivo(retangulo,i-1,soma);
    }

}
int main(){
    Retangulo retangulo[TAM];
    Retangulo* retangulo1[TAM];



    for(int i = 0; i<TAM; i++){
    retangulo1[i] = new Retangulo;
    }
    leiaRet(retangulo1);// EX 2
    somaAreaRecursivo(retangulo1,TAM-1,0);//EX 4 B
    
    /*
    somaAreaInterativo(retangulo1); //EX 4 A
    //calculaListaArea(retangulo1);//EX 3
    escrevaRet(retangulo1);// EX 2

    //leiaRetangulos(retangulo);// EX 1
    //escrevaRetangulos(retangulo);// EX 1 
*/
}
