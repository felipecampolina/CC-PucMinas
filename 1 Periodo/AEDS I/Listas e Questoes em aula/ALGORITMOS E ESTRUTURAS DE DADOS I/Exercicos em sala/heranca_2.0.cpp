#include <iostream>

using namespace std;

class Animal{
    private: // Na OML usa-se #
    string nome;

    public:

    Animal(){};
    Animal(string nome){
        this -> nome=nome;
    }

    string getNome(){
        return nome;
    }
    void setNome(string nome){
        this -> nome=nome;
    }
    virtual void fala()
    {
        cout<<endl<<".";
    }
};
class Cachorro:public Animal{//Heran�a p�blica
    public:
    Cachorro(string nome):Animal(nome)// Chamada explicita ao construtor
    {}//N�o precisa de nada, pois esta mandando pro construtor de animal
    Cachorro(){};//Autoriza a cria��o do cachorro sem parametro
    void fala()
    {
        cout<<endl<<"Au Au";
    }
};
class Gato:public Animal{ // Heran�a p�blica
    public:
    Gato(string nome):Animal(nome)// Chamada explicita ao construtor
    {}//N�o precisa de nada, pois esta mandando pro construtor de animal
    Gato(){};
    void fala()
    {
        cout<<endl<<"Miau";
    }
};

void leCachorros(Animal cachorro[]){
    string nome;
    for(int i = 0; i<3; i++){
        cout<<"\nCachorro"<<i+1<<": ";
        cin>>nome;
        cachorro[i].setNome(nome);
    }
}
void listaCachorros(Cachorro cachorro[]){
    for(int i = 0; i< 3; i++){
    cout<<cachorro[i].getNome()<<endl;
    }
}
void leGatos(Gato gatos[]){
    string nome;
    for(int i = 0; i<3; i++){
        cout<<"\nGato"<<i+1<<": ";
        cin>>nome;
        gatos[i].setNome(nome);
    }
}
void listaGatos(Gato gatos[]){
    for(int i = 0; i< 3; i++){
    cout<<gatos[i].getNome()<<endl;
    }
}
void listaAnimais(Animal animal[]){
    for(int i = 0; i< 3; i++){
        cout<<endl<<animal[i].getNome(); // Apenas possivel, pois getNome esta na superclasse
    }

}
void falaAnimais(Animal animais[])
{
    for(int i =0; i<3 ; i++)
    {
        animais[i].fala();
    }
}

int main(){
    /*Animal* animal; // Criando objetos da classe
    animal = new Animal("bichinho");// Utiliza-se o NEW para criar uma instancia que � ponteiro --> MALLOC
    Cachorro* cachorro;// Criando objetos da classe
    cachorro = new Cachorro("sol");
    Gato* gato;// Criando objetos da classe
    gato = new Gato("lua");*/

    Cachorro cachorro[3];
    Gato gato[3];
    leCachorros(cachorro);
    leGatos(gato);
    listaAnimais(cachorro);
    listaAnimais(gato);
    falaAnimais(cachorro);
    falaAnimais(gato);

    // O atriuto nome foi herdado pelo cachorro e o gato
    //cout<<animal.getNome()<<endl;
   // cout<<gato.getNome()<<endl;

   /*   Chachorro* [3];
        Cachorro[1] = new Cachorro;
        Cachorro[2] = new Cachorro;
        Cachorro[3] = new Cachorro;
    */
}
