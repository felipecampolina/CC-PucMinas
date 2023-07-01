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
};
class Cachorro:public Animal{//Herança pública
    public:
    Cachorro(string nome):Animal(nome)// Chamada explicita ao construtor
    {}//Não precisa de nada, pois esta mandando pro construtor de animal
    Cachorro(){};//Autoriza a criação do cachorro sem parametro
};
class Gato:public Animal{ // Herança pública
    public:
    Gato(string nome):Animal(nome)// Chamada explicita ao construtor
    {}//Não precisa de nada, pois esta mandando pro construtor de animal
    Gato(){};
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
        cout<<endl<<animal[i].getNome();
    }

}
int main(){
    /*Animal* animal; // Criando objetos da classe
    animal = new Animal("bichinho");// Utiliza-se o NEW para criar uma instancia que é ponteiro --> MALLOC
    Cachorro* cachorro;// Criando objetos da classe
    cachorro = new Cachorro("sol");
    Gato* gato;// Criando objetos da classe
    gato = new Gato("lua");*/

    Cachorro cachorro[3];
    Gato gato[3];
    Animais Animal[3]
    leCachorros(cachorro);
    leGatos(gato);
    listaCachorros(cachorro);
    listaGatos(gato);

    // O atriuto nome foi herdado pelo cachorro e o gato
    //cout<<animal.getNome()<<endl;
   // cout<<gato.getNome()<<endl;

   /*   Chachorro* [3];
        Cachorro[1] = new Cachorro;
        Cachorro[2] = new Cachorro;
        Cachorro[3] = new Cachorro;
    */
}