#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool eFim(char x []){
    bool resp = false;
    if((x[0]=='F') && (x[1] == 'I') && (x[2] =='M')) resp = true;
    return resp;
}
bool apenasVogal(char palavra[]) {
		bool resp = true;
		int i =0;
		while(i<strlen(palavra)-1 && resp == true) {
			if(palavra[i]!= 'a'&&palavra[i]!= 'e'&&palavra[i]!= 'i'&&palavra[i]!= 'o'&&palavra[i]!= 'u'&&palavra[i]!= 'A'&&palavra[i]!= 'E'&&palavra[i]!= 'I'&&palavra[i]!= 'O'&&palavra[i]!= 'U'&&palavra[i]!= ' ')resp = false;
			if((palavra[i]>=48) && (palavra[i]<=57))resp=false;
			i++;
		}
		return resp;
}
bool apenasConsoante(char palavra[]) {
		bool resp = true;
		int i =0;
		while(i<strlen(palavra)-1 && resp == true) {
			if(palavra[i]== 97||palavra[i]== 101||palavra[i]== 105||palavra[i]== 111||palavra[i]== 117||palavra[i]== 65||palavra[i]== 69||palavra[i]== 73||palavra[i]== 79||palavra[i]== 85)resp = false;
			if((palavra[i]>=48) && (palavra[i]<=57))resp=false;
			i++;
		}
		return resp;
}
bool eNumeroInteiro(char palavra[]) {
		bool resp = true;
		int i =0;
		while(i<strlen(palavra)-1 && resp == true) {
			if((palavra[i]<48) || (palavra[i]>57))resp=false;
			i++;
		}
		return resp;
}
 bool eNumeroReal(char palavra[]) {
		bool resp = false;
		int i =0;
		while(i<strlen(palavra)-1 && resp == false) {
			if((palavra[i]<48) || (palavra[i]>57))resp=false;
			if((palavra[i]==44) || (palavra[i]==46))resp=true;
			i++;
		}
		return resp;
 }
 void lePalavra(char palavra []){
    fgets(palavra,1000,stdin);
}
    int main() {
		char palavra[1000];
		lePalavra(palavra);
		while(!eFim(palavra)){
			if(apenasVogal(palavra))printf(" SIM ");
			else printf(" NAO ");
			if(apenasConsoante(palavra))printf("SIM ");
			else printf("NAO ");
			if(eNumeroInteiro(palavra))printf("SIM ");
			else printf("NAO ");
			if(eNumeroReal(palavra))printf("SIM \n");
			else printf("NAO\n ");
			lePalavra(palavra);
		}
	}