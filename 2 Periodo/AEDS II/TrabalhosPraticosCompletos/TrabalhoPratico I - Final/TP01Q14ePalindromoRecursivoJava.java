
class Equacao{

	//atributos
	private String s;
	private int count;
	private int limit;
	private boolean[] vars;

	//construtor
	public Equacao(String str){
		this.s = str;
		int n = ((int) this.s.charAt(0)) - 48;
                this.count = 2;
                vars = new boolean[n];

		variaveis(0); // inicializa vars
		this.s = limpaExpressao(0); //limpa a string

		this.limit = this.s.length();
		this.count = 0;
	}
	/**
	*variaveis - inicializa as variaveis do objeto
	*@param boolean[]
	*@return boolean{}
	*/
	private void variaveis (int i){
		if (i < ((int) this.s.charAt(0) - 48)){
			this.vars[i] = (((int)s.charAt(count)) - 48) == 1;
			this.count = this.count + 2;
			variaveis(++i);
		}
	}

	/**
	*limpaExpressao - retira os caracteres desnecessarios da string
	*/
	private String limpaExpressao (int i){
		String resp = "";
		if (i < this.s.length()){
			char c = this.s.charAt(i);
			
			if ((c=='a') || /*char para AND*/
			    (c=='r') || /*char para OR*/
			    (c=='t') || /*char para NOT*/
			    (c==')') || /*char para fechamento de operacao*/
			    ('A'<=c && c<='Z')){ /*char para variaveis*/  
				
				resp =  c + limpaExpressao(++i);
			}
			else{
				resp = limpaExpressao(++i);
			}
		}
		return resp;
	}

	
	public char getChar(){
		return (this.s.charAt(this.count));
	}

	
	public void updateContador (int i){
		this.count = this.count + i;
	}

	
	public int getCount(){
		return this.count;
	}

	/**
	*getLimit - retorna o limite da string
	*return in
	*/
	public int getLimit(){
		return this.limit;
	}

	
	public boolean getValue (char c){
		int i = ((int)c) - 65;
		return this.vars[i]; 
	
	}
}

public class TP01Q14ePalindromoRecursivoJava{
	
	/**
	* Metodo main
	*/
	public static void main (String[] args){
		ePalindromoRecursivo();
	}

	/**
	* ePalindromoRecursivo permite percorrer recursivamente todas as linhas de input
	*/
	public static void ePalindromoRecursivo (){
		String input = MyIO.readLine();
		if (!isEnd(input)){	
			if (booleana(input))
				MyIO.println("1");	
			else
				MyIO.println("0");
			
			ePalindromoRecursivo();
		}
	}

	
	//Verifica o fim das entradas
	
	public static boolean isEnd (String s){
		return (s.length()==1 && s.charAt(0)=='0');
	}

	
	//Resolve uma expressao booleana
	
	public static boolean booleana (String s){
		Equacao eq = new Equacao(s);
		return (booleana(eq));
	}
	//Resolve uma expressao booleana
	
	public static boolean booleana (Equacao eq){
		//declaracoes
		boolean resp = false;

		if (eq.getChar() == 'a'){
			eq.updateContador(1);
			resp =  and(eq);
		}
		else if (eq.getChar() == 'r'){
			eq.updateContador(1);
			resp = or(eq);
		}
		else if (eq.getChar() == 't'){
			eq.updateContador(1);
			resp = not(eq);
		}
	
		return resp;
	}

	
	//retorna solucao AND da variaveis
	
	public static boolean and (Equacao eq){
		boolean a = true;
		boolean b = true;//caractere neutro para and
			
		if (eq.getChar() == 'a'){
			eq.updateContador(1);
			a = and(eq);
		}
		else if (eq.getChar() == 'r'){
			eq.updateContador(1);
			a = or(eq);
		}
		else if (eq.getChar() == 't'){
			eq.updateContador(1);
			a = not(eq);
		}
		else if ('A'<=eq.getChar() && eq.getChar()<='Z'){
				a = eq.getValue(eq.getChar());
				eq.updateContador(1);	
		}

		if (eq.getChar() != ')'){
			b = and(eq);
		}
		else{
			eq.updateContador(1);
		}
		
		return (a && b);
	}
	
	
	 //retorna solucao OR da variaveis
	
	public static boolean or (Equacao eq){
		boolean a = false;
		boolean b = false;//caractere neutro para or
			
		if (eq.getChar() == 'a'){
			eq.updateContador(1);
			a = and(eq);;
		}
		else if (eq.getChar() == 'r'){
			eq.updateContador(1);
			a = or(eq);
		}
		else if (eq.getChar() == 't'){
			eq.updateContador(1);
			a = not(eq);
		}
		else if ('A'<=eq.getChar() && eq.getChar()<='Z'){
			a = eq.getValue(eq.getChar());
			eq.updateContador(1);
		}		
	
		if (eq.getChar() != ')'){
			b = or(eq);
		}
		else{
			eq.updateContador(1);
		}		

		return (a || b);
	}
		
	
	public static boolean not (Equacao eq){
		boolean resp = false;
				
		if (eq.getChar() == 'a'){
			eq.updateContador(1);
			resp = and(eq);
		}
		else if (eq.getChar() == 'r'){
			eq.updateContador(1);
			resp = or(eq);
		}
		else if (eq.getChar() == 't'){
			eq.updateContador(1);
			resp = not(eq);
		}
		else if ('A'<=eq.getChar() && eq.getChar()<='Z'){
			resp = eq.getValue(eq.getChar());
			eq.updateContador(1);
		}		
		
		eq.updateContador(1);
	
		return !resp;
	}
}