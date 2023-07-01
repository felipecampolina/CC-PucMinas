#include <iostream>

using namespace std;

const int MAX = 5;
int tamanho = 0;
int tamanho1 = 0;

class Data{
private:
    int dia,mes, ano;

public:

    /*Data(int dia = 0, int mes = 0, int ano = 0){
        this->setDia(dia);
        this ->setMes(mes);
        this ->setAno(ano);
    }*/
    // Método construtor
    
    Data(){};


    int getDia(){
        return dia;
    }
    int getMes(){
        return mes;
    }
    int getAno(){
        return ano;
    }

    bool setDia(int dia){
        bool sucesso = false;
        if(dia>=0 || dia<=31){
            this -> dia = dia;
            sucesso = true;
        }
        return sucesso;
    }

    bool setMes(int mes){
        bool sucesso = false;
        if(mes>=0 || mes<=12){
            this -> mes = mes; //this passa a ser obrigatorio quando tiver nomes iguais
            sucesso = true;
        }
        return sucesso;
    }

    bool setAno(int ano){ //não tem necessidade, mas é uma boa prática definir como booleana
        bool sucesso = false;
        if(ano>=0){
            this -> ano = ano; // this -> atributo = variavel parametrica
            sucesso = true;
        }
        return sucesso;
    }


    void escreveData(){
        printf("%i/%i/%i\n", getDia(), getMes(), getAno()); //padrão é ser do objeto corrente (se não tiver nenhuma variável local, ele vai procurar os atributos)
    }
    void leData(){
        printf("\ndd/mm/aaaa: ");
        scanf("%i/%i/%i", &dia, &mes, &ano);
        setDia(dia);
        setMes(mes);
        setAno(ano);
        }
    char* mesExtenso(){
        char* mes[] = {"janeiro","fevereiro","marco","abril","maio","junho","julho","agosto","setembro","outubro","novembro","dezembro"};
        return mes[this->mes-1];
    }
    bool dataValida(){
        bool valida = true;
        if(this->dia<1 || this->dia>31)valida = false;
        else if (this->mes<1||this->mes>12)valida = false;
        return valida;
    }
    void setData(int dia, int mes, int ano){
        this ->dia = dia;
        this ->mes = mes;
        this ->ano = ano;

    }

};

class Pessoa{
    private:
    string nome;
    Data nascimento;

    public:
    /* Pessoa(int dia = 0, int mes = 0, int ano = 0,string nome =""){
        this->nascimento.setDia(dia);
        this ->nascimento.setMes(mes);
        this ->nascimento.setAno(ano);
        this -> setNome(nome);
    }// Método construtor*/

    Pessoa(){};
    void setNome(string nome){
        this ->nome=nome;

    }
    string getNome(){
        return this->nome;
    }
    bool setNascimento(int dia, int mes, int ano){
        this->nascimento.setData(dia,mes,ano);
    }
    Data getNascimento(){
        return this ->nascimento;
    }
    void lePessoa(){
        fflush(stdin);
        cout<<"\nNome:";
        getline(cin,this->nome);
        cout<<"Data de nascimento: ";
        this->nascimento.leData();
    }
    void escrevePessoa(){
        cout<<"\nNome: "<<this->getNome();
        cout<<"\nData de nascimento: ";
        this ->nascimento.escreveData();
    }
    virtual void escrevePessoa(Pessoa* pessoa[]) {};
};

class Aluno:public Pessoa{
    private:
    string curso;

    public:
    Aluno(){};

    void leAlunos(){
        string curso, nome;
        int i = 0, dia, mes,ano;
        cout<<"\nNome: ";
        cin>>nome;
        setNome(nome);
        cout<<"Data de Nascimento[dd/m/aaaa]: ";
        bool ERRO;
        do{
        scanf("%i/%i/%i", &dia, &mes, &ano);
        ERRO = ((dia < 1)|| (dia > 31)) || ((mes < 1)|| (mes > 12));
        if(ERRO)printf("\nData Invalida, Tente novamente!: \n");
        }while(ERRO);
        setNascimento(dia,mes,ano);
        cout<<"\nCurso: ";
        cin>>curso;
        setCurso(curso);
        
    }
    
    void setCurso(string curso){
        this->curso=curso;
    }
    string origem(){
        return this -> curso;
    }
    void escreveAluno(){
       //escrevePessoa();
       cout<<"Curso:"<<origem();
    }
    void escrevePessoa(Pessoa* pessoa[]){
        cout<<"\nNome: "<<this->getNome();
        cout<<"\nData de nascimento: ";
        this ->getNascimento().escreveData();
        cout<<"Curso:"<<origem();


    };
    
};
class Professor:public Pessoa{
    private:
    string departamento;
    public:
    
    void setDepartamento(string departamento){
        this->departamento=departamento;
    }
    string origem(){
        return this -> departamento;
    }
    void leProfessores(){
        string departamento, nome;
        int i = 0, dia, mes,ano;
        cout<<"\nNome: ";
        cin>>nome;
        setNome(nome);
        cout<<"Data de Nascimento[dd/m/aaaa]: ";
        bool ERRO;
        do{
        scanf("%i/%i/%i", &dia, &mes, &ano);
        ERRO = ((dia < 1)|| (dia > 31)) || ((mes < 1)|| (mes > 12));
        if(ERRO)printf("\nData Invalida, Tente novamente!: \n");
        }while(ERRO);
        setNascimento(dia,mes,ano);
        cout<<"\nDepartamento: ";
        cin>>departamento;
        setDepartamento(departamento);
        
    }
    void escreveProfessor(){
       //escrevePessoa();
       cout<<"Departamento:"<<origem();
    }
    void escrevePessoa(Pessoa* pessoa[]){
        cout<<"\nNome: "<<this->getNome();
        cout<<"\nData de nascimento: ";
        this ->getNascimento().escreveData();
         cout<<"Departamento:"<<origem();


    };
};



int menu(){
    int op;
        puts("");
    do{
        puts("0 - Sair");
        puts("1 - Cadastrar um Professor");
        puts("2 - Cadastrar um Aluno");
        puts("3 - Listar Pessoas");
        puts("4 - Pesquisar Aniversariantes do Mes");
        printf("\nSua opcao: ");
        scanf("%i", &op);
    }while(op<0 || op>5);
}

int main()
{
    cout << "Trabalho Pratico AEDS -- Felipe Campolina Soares de Paula" << endl;
    Pessoa* pessoa[MAX];
    Aluno* alunos[MAX];
    Professor* professores[MAX];
    int opcao;
    do{
        opcao = menu();
        switch(opcao){
        case 0: puts("Tchau!");
            break;
        case 1:
            professores[tamanho1] = new Professor(); //vai ser executada nessa instancia que acabou de ser criada
            if(tamanho1<MAX) {
                professores[tamanho1] -> leProfessores();
                tamanho1++;
            }
            break;
        case 2:
            alunos[tamanho] = new Aluno(); //vai ser executada nessa instancia que acabou de ser criada
            if(tamanho<MAX) {
                alunos[tamanho]->leAlunos();
                tamanho++;
            }
            break;

        case 3:
            for(int i=0; i<tamanho; i++){
                //pessoa[i] -> escrevePessoa();
                alunos[i]->escrevePessoa(pessoa);
            }
            for(int i=0; i<tamanho1; i++){
                professores[i]->escrevePessoa(pessoa);
            }
            break;
        case 4:
        int mes_pesquisado;

        cout<<"\nDigite um mes para pesquisar: ";
        scanf("%i",&mes_pesquisado);
        for(int i=0; i<tamanho; i++){

                if(mes_pesquisado == alunos[i]->getNascimento().getMes()) alunos[i] -> escrevePessoa(pessoa);;
            }
        for(int i =0; i<tamanho1 ; i++){
            if(mes_pesquisado == professores[i]->getNascimento().getMes()) professores[i] -> escrevePessoa(pessoa);;
        }
        }
        
    }while(opcao != 0);

    return 0;
}
