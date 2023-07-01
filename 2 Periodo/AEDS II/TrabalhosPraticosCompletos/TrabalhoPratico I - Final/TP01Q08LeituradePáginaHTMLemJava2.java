
import java.net.URL;
import java.net.URI;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;



class TP01Q08LeituradePáginaHTMLemJava2 {//Ininico classe

 public static String pegaURL (String endWeb, String nomePag)
 {//Pega URL
    String url = "";
    String resp = "";
    try {
    //definir dados
     int arranjo [] = new int [26];
     URL url2 = new URL (endWeb);
     URI uri = url2.toURI();
     BufferedReader br = new BufferedReader(new InputStreamReader (url2.openStream ()) );
     while ((url = br.readLine()) != null) {
    	 for (int i = 0; i < url.length (); i++)
         {
           if (url.charAt (i) == 'a')
            {
              arranjo [1]++;
            }
           else if (url.charAt (i) == 'e')
              {
                arranjo [2]++;
              }
           else if (url.charAt (i) == 'i')
               {
                 arranjo [3]++;
               }
           else if (url.charAt (i) == 'o')
               {
                 arranjo [4]++;
               }
           else if (url.charAt (i) == 'u')
               {
                 arranjo [5]++;
               }
           else  if (url.charAt (i) == 'á')
              {
                arranjo [6]++;
              }
            else if (url.charAt (i) == 'é')
               {
                 arranjo [7]++;
               }
            else if (url.charAt (i) == 'í')
                {
                  arranjo [8]++;
                }
            else if (url.charAt (i) == 'ó')
                {
                  arranjo [9]++;
                }
            else if (url.charAt (i) == 'ú')
               {
                 arranjo [10]++;
                }
            else if (url.charAt (i) == 'à')
             {
                 arranjo [11]++;
             }
            else if (url.charAt (i) == 'è')
               {
                 arranjo [12]++;
               }
            else if (url.charAt (i) == 'ì')
                {
                  arranjo [13]++;
                }
            else if (url.charAt (i) == 'ò')
                {
                  arranjo [14]++;
                }
            else if (url.charAt (i) == 'ù')
                {
                  arranjo [15]++;
                }
           else if (url.charAt (i) == 'ã')
              {
                  arranjo [16]++;
              }
           else if (url.charAt (i) == 'õ')
              {
                  arranjo [17]++;
              }
           else if (url.charAt (i) == 'â')
              {
                  arranjo [18]++;
              }
           else if (url.charAt (i) == 'ê')
              {
                   arranjo [19]++;
              }
           else if (url.charAt (i) == 'î')
               {
                 arranjo [20]++;
               }
           else if (url.charAt (i) == 'ô')
               {
                 arranjo [21]++;
               }
           else if (url.charAt (i) == 'û')
               {
                 arranjo [22]++;
               }
           else if (url.charAt (i) >= 'a' && url.charAt (i) <= 'z')
              {
                 arranjo [23]++;
              }
           else if (url.charAt (i) == '<')
              {
                 if (i+3 < url.length ())
                 {
                    if (url.charAt (i+1) == 'b' &&  url.charAt (i+2) == 'r' && url.charAt (i+3) == '>' )
                    {
                       arranjo [24]++;
                       arranjo [23] = arranjo[23] - 2;
                    }
                 }
               if (i+5 < url.length())
              { 
                 if (url.charAt (i+1) == 't' && url.charAt (i+2) == 'a' &&  url.charAt (i+3) == 'b' &&
                      url.charAt (i+4) == 'l' && url.charAt (i+5) == 'e' && url.charAt (i+6) == '>' )
                 {
                 arranjo [25]++;
                 arranjo [23] = arranjo [23] - 3;
                 arranjo[1]--;
                 arranjo[2]--;
                 }
              }
           }
         }//fim for
         }//fim while
     resp = "a("+arranjo[1]+ ") e("+arranjo[2]+") i("+arranjo[3]+") o("+arranjo[4]+") u("+arranjo[5]+
    	        ") á("+arranjo[6]+") é("+arranjo[7]+") í("+arranjo[8]+") ó("+arranjo[9]+") ú("+arranjo[10]+
    	        ") à("+arranjo[11]+") è("+arranjo[12]+") ì("+arranjo[13]+") ò("+arranjo[14]+") ù("+arranjo[15]+
    	        ") ã("+arranjo[16]+") õ("+arranjo[17]+") â("+arranjo[18]+") ê("+arranjo[19]+") î("+arranjo[20]+
    	        ") ô("+arranjo[21]+") û("+arranjo[22]+") consoante("+arranjo[23]+
    	        ") <br>("+arranjo[24]+") <table>("+arranjo[25]+ ") " + nomePag; 
     br.close();
     } catch (MalformedURLException excecao) {
          excecao.printStackTrace();
      } catch (URISyntaxException excecao) {
          excecao.printStackTrace();
      } catch (IOException excecao) {
          excecao.printStackTrace();
      }
    return (resp);
 }//fim pegaURL



 public static boolean eIgual (String s1,String s2) {
 //definir dados
        boolean resp = true;
 //iniciar testes
  if (s1.length ( ) != s2.length ( )) {
        resp = false;
  }
  else {
  //iniciar repeticao
   for (int i = 0; i < s1.length ( ); i = i+1) {
        //testar se diferentes
         if (s1.charAt (i) != s2.charAt (i)) {
          resp = false;
          i = s1.length ( );
         }
    }//fim repeticao
  }//fim else
 //retornar se igual ou nao
 return (resp);
 }//fim eIgual


//main
 public static void main (String [] args){
  String [] pagWeb = new String [10];
  String [] endWeb = new String [10];
  int npalavra = 0;
 //iniciar repeticao para leitura de palavras
 do {
  pagWeb [npalavra] = MyIO.readLine ();
  if (eIgual (pagWeb [npalavra], "FIM") == false)
   endWeb [npalavra] = MyIO.readLine ();
  npalavra = npalavra + 1;
 }while ( eIgual (pagWeb [npalavra-1],"FIM") == false);//fim do while
 npalavra = npalavra -1; //desconsiderar palavra "FIM"
 //conferir url
  for (int i = 0; i < npalavra; i = i+1) {
   System.out.println (pegaURL(endWeb [i], pagWeb [i]));
    }//fim for
 }//fim main
}//fim classe
