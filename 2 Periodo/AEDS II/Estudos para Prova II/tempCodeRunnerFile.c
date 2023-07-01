#include <stdio.h>
#include <string.h>

void p1(char s[]){
	char c = '0';
	int x = strlen(s)-1;
	while(x >0){
		if(x>=2&&s[x-2]>s[x]){
			c=s[x-2];s[x-2]=s[x];s[x] = c;
			}
			x=x-2;
			
		}
		printf("%s", s);
	}
 
int main(){
	char c[6];
	c[0] = '6';
	c[1] = '5';
	c[2] = '4';
	c[3] = '3';
  c[4] = '2';
	c[5] = '1';
	p1(c);
	printf("%s", c);
	return 0;
	}