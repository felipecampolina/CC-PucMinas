#include <iostream>
using std::cout; 
using std::cin;
using std::endl;
#include <string>
using std::string;
 class Data{
    private:
    int dia;
    int mes;
    int ano;

    public:

    void setDia(){
        bool ERRO;
        do{
            cout<<"\nDia: ";
            cin >> dia;
            ERRO = ((dia <= 0) || (dia > 31));
            if(ERRO)cout<<"\nDia Invalido";
        }while(ERRO);
        
    }
    void setMes(){
        bool ERRO;
        do{
        cout<<"\nMes: ";
        cin >> mes;
        ERRO = ((mes <= 0) || (mes > 12));
        if(ERRO)cout<<"Mes Invalido\n";
        }while(ERRO);
        
    }
    void setAno(){
        bool ERRO;
        do{
        cout<<"\nAno: ";
        cin >> ano;
        ERRO = (mes <= 0);
        if(ERRO)cout<<"Ano Invalido\n";
        }while(ERRO);
        
    }
    void setData(){
        setDia();
        setMes();
        setAno();
    }
    int getDia(){
        return dia;
    }
    int getMes(){
        return mes;
    }
    int getAno(){
        return ano;
    }
    void getData(){
        cout<<"\n"<<dia<<"/"<<mes<<"/"<<ano<<"\n";
    }

 };


int main(){
    int TAM = 3;
    Data data[TAM];

    for(int i = 0; i < TAM ; i++){
        data[i].setData();
        data[i].getData();
        }
}