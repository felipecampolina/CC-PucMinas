class Data {
	private:
	int dia;
	int mes; 
	int ano;
	public:
		void setDia (int dia){
			this -> dia = dia; 
//(dia = atributo do objeto corrente/classe)
}

		int getDia (){
			return this -> dia;
}

void setData (int dia, int mes, int ano){
	setDia(dia);
setMes(mes);
setAno(ano);
	

}  //não esquecer do ponto e vírgula
};

int main(){
	Data D;
	//D.dia = 17;
	//D.mes = 6;
	//D.ano = 20220;  //isso não funciona mais (diferenciação entre private-public)
	D.setDia(17);
	//printf(“\n Dia = %i”, D.dia); //erro
printf(“\n Dia = %i”, D.getDia());

}

