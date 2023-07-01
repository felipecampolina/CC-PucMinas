#include<iostream>
const int MAX = 4;
using namespace std;
class FiguraGeometrica{
    public:

    virtual float calculaPerimetro() = 0;
    virtual float calculaArea() = 0;
    //virtual void setLado(float lado) = 0; // EX 6 e 7
    virtual void setRaio(float raio) = 0; // EX 6 e 7
    
};
class Quadrado:public FiguraGeometrica{
    private:
    float lado;
    public:
    void setLado(float lado){
        this->lado=lado;
    }
    float getLado(){
        return this->lado;
    }
    float calculaPerimetro(){
        float perimetro;
        perimetro = getLado()*4;
        return perimetro;
    }
    float calculaArea(){
        float area;
        area = getLado() * getLado();
        return area;
    }
    
    /*Quadrado maiorQuadrado(Quadrado *quadrado){
        printf("Lado1: %f",this->lado);
        printf("LAdo2: %f",quadrado->getLado());
        if(this->lado > quadrado->getLado())return *this;
        else return *quadrado;
    }*/ //EX 8

};
class Circulo:public FiguraGeometrica{
    private:
    float raio;
    public:
    void setRaio(float raio){
        this->raio=raio;
    }
    float getRaio(){
        return this->raio;
    }
    float calculaPerimetro(){
        float perimetro;
        printf("teste %f",getRaio());
        perimetro = getRaio() *3.14*2;
        return perimetro;
    }
    float calculaArea(){
        float area;
        area = getRaio() * getRaio() * 3.14;
        return area;
    }
    void escreveAreaDoisCirculos(Circulo *circulo1,Circulo *circulo2){
        printf("\nArea circulo1: %f",circulo1->calculaArea());
        printf("\nArea circulo2: %f",circulo2->calculaArea());
    }
};

float calculaPerimetroGeometrico(FiguraGeometrica* FiguraGeometrica){
    return FiguraGeometrica->calculaPerimetro();
}
float calculaAreaGeometrico(FiguraGeometrica* FiguraGeometrica){
    return FiguraGeometrica->calculaArea();
}
void escreveAreaDoisCirculos(Circulo *circulo1,Circulo *circulo2){
        printf("\nArea circulo1: %f",circulo1->calculaArea());
        printf("\nArea circulo2: %f",circulo2->calculaArea());
    }
void ordenaArranjoCirculos(Circulo circulo1[]){
    float aux;
    for(int i = 0; i< MAX ; i++){
    printf("\nRaio c1 : %f",circulo1[i].getRaio());
    }
    for(int i = 0; i<MAX; i++){
        for(int j = i+1; j<MAX; j++){
            if(circulo1[i].getRaio() > circulo1[j].getRaio()){
                aux = circulo1[i].getRaio();
                circulo1[i] = circulo1[j];
                circulo1[j].setRaio(aux);
            }

        }
    }
    for(int i = 0; i<MAX ; i++){
        printf("\nA[%i] = %f",i,circulo1[i].getRaio());
    }

}
int main(){
    /*Quadrado* quadrado;
    quadrado = new Quadrado;
    Circulo* circulo;
    circulo = new Circulo;*/ //EX5

    //FiguraGeometrica* quadrado;
    //quadrado = new Quadrado;
    //FiguraGeometrica* circulo;
    //circulo = new Circulo;
    //circulo->setRaio(5);
    //quadrado->setLado(5);
    //printf("\nPerimetro: %f ",calculaPerimetroGeometrico(circulo)); // EX6
    //printf("\nPerimetro: %f ",calculaPerimetroGeometrico(quadrado)); // EX6
    //printf("Area: %f",calculaAreaGeometrico(circulo));//EX7
    //printf("Area: %f",calculaAreaGeometrico(quadrado));//EX7
    
    
    /*Quadrado* quadrado2;
    quadrado2 = new Quadrado;
    Quadrado* quadrado3;
    quadrado3 = new Quadrado;
    quadrado2 -> setLado(10);
    quadrado3 -> setLado(15);
    quadrado3->maiorQuadrado(quadrado2);*/ //EX 8


   /* Circulo* circulo2;
    circulo2 = new Circulo;
    Circulo* circulo3;
    circulo3 = new Circulo;
    circulo2 ->setRaio(10);
    circulo3 ->setRaio(20);
    circulo3->escreveAreaDoisCirculos(circulo2,circulo3); 
    escreveAreaDoisCirculos(circulo2,circulo3); */ //EX 9 e 10


    Circulo circulo[MAX];
    circulo[0].setRaio(10);
    circulo[1].setRaio(2);
    circulo[2].setRaio(30);
    circulo[3].setRaio(5);
    ordenaArranjoCirculos(circulo); // EX 11


}