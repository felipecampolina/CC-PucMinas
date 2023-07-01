import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;


class Filme{
    private Integer id;
    private String nome;
    private Date data;
    private Float nota;
    private char[] certificado;
    private String[] generos;
    private int segmento;

    public Filme(){
        this.id = -1;
        this.nome = "";
        this.data = null;
        this.nota = null;
        this.certificado = new char[3];
        this.generos = null;
        this.segmento = 0;

    }

    public Filme(Integer id, String nome, Date data, Float nota, char[] certificado, String[] generos){
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.nota = nota;
        this.certificado = certificado;
        this.generos = generos;
        this.segmento = 0;
    }

    public byte[] toByteArray() throws IOException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.id);
        dos.writeUTF(this.nome);
        dos.writeUTF(formatter.format(this.data));
        dos.writeFloat(this.nota);
        dos.writeUTF(Arrays.toString(this.certificado));
        dos.writeUTF(Arrays.toString(this.generos));

        return baos.toByteArray();
    }

    public void fromByteArray(byte ba[]) throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.id=dis.readInt();
        this.nome=dis.readUTF();
        this.data = formatter.parse(dis.readUTF());
        this.nota=dis.readFloat();
        
        String[] certificadoString = dis.readUTF().split(",");
        
        for(int i = 0; i < certificado.length; i++){
            if(certificadoString[i].charAt(0) != '['){
                this.certificado[i] = certificadoString[i].charAt(0);
            } else{
                this.certificado[i] = certificadoString[i].charAt(1);
            }
       } 

        String generoLinha = dis.readUTF();
        String generoReplace1 = generoLinha.replace('[', ' ');
        String generoReplace2 = generoReplace1.replace(']', ' ');
        this.generos = generoReplace2.split(",");
       
    }

    public String getNome(){
        return this.nome;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setSegmento(int seg){
        this.segmento = seg;
    }

    public int getSegmento(){
        return this.segmento;
    }

    public void printFilme(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

        System.out.println("[" + this.id + ", " + this.nome + ", " 
        + formatter.format(this.data) + ", " + this.nota + ", " + Arrays.toString(this.generos) + ", "
        + Arrays.toString(this.certificado) + "]\n");
    }
}

public class TP1{

    public static Filme[] ordenar(Filme[] ordenados){
        int menor = 0;

        for(int a = 1; a<ordenados.length - 1; a++){
            menor = a;
            for(int b = a+1; b<ordenados.length; b++){
                if(ordenados[b] != null && ordenados[menor] != null){
                    if(ordenados[b].getId() < ordenados[menor].getId()){
                        menor = b;
                    }
                }
            }
            
            Filme temp = ordenados[menor];
            ordenados[menor] = ordenados[a];
            ordenados[a] = temp;
            
        }

        return ordenados;
    }

    public static Filme[] IntercalacaoBalanceada(int bloco, int n, Filme[] imdb) throws Exception{
        int contadorBloco = 0;
        Filme[] ordenados = new Filme[bloco];

        RandomAccessFile arq1 = new RandomAccessFile("dados/temp/arq1.tmp", "rw");
        RandomAccessFile arq2 = new RandomAccessFile("dados/temp/arq2.tmp", "rw");
        boolean switchArq1 = true;

            
        for (int i = 0; i < imdb.length; i++) {
            if (imdb[i] != null) {
                if (contadorBloco == bloco) {
                    ordenados = ordenar(ordenados);
                    if (switchArq1 == true) {
                        byte[] ba;
                        int contador = 0;

                        while (contador < ordenados.length) {
                            if (ordenados[contador].getId() != -1) {
                                ba = ordenados[contador].toByteArray();
                                arq1.writeInt(ba.length);
                                arq1.write(ba);
                            }

                            contador++;
                        }

                        for (int a = 0; a < ordenados.length; a++) {
                            ordenados[a] = null;
                        }

                        switchArq1 = false;
                        i--;
                        contadorBloco = 0;
                    } else {
                        byte[] ba;
                        int contador = 0;

                        while (contador < ordenados.length) {
                            if (ordenados[contador].getId() != -1) {
                                ba = ordenados[contador].toByteArray();
                                arq2.writeInt(ba.length);
                                arq2.write(ba);
                            }

                            contador++;
                        }

                        for (int a = 0; a < ordenados.length; a++) {
                            ordenados[a] = null;
                        }

                        switchArq1 = true;
                        i--;
                        contadorBloco = 0;
                    }
                } else {
                    ordenados[contadorBloco] = imdb[i];
                    contadorBloco++;
                }
            } else {
                if (ordenados[0] != null) {
                    ordenados = ordenar(ordenados);
                    if (switchArq1 == true) {
                        byte[] ba;
                        int contador = 0;

                        while (contador < ordenados.length) {
                            if (ordenados[contador] != null) {
                                ba = ordenados[contador].toByteArray();
                                arq1.writeInt(ba.length);
                                arq1.write(ba);
                            }
                            contador++;
                        }
                        ordenados[0] = null;
                    } else {
                        byte[] ba;
                        int contador = 0;

                        while (contador < ordenados.length) {
                            if (ordenados[contador] != null) {
                                ba = ordenados[contador].toByteArray();
                                arq2.writeInt(ba.length);
                                arq2.write(ba);
                            }
                            contador++;
                        }
                        ordenados[0] = null;
                    }
                }
            }
        }
        
        Filme[] temp1 = lerTemp("dados/temp/arq1.tmp"); 
        Filme[] temp2 = lerTemp("dados/temp/arq2.tmp");
        
        RandomAccessFile arq3 = new RandomAccessFile("dados/temp/arq3.tmp", "rw");
        RandomAccessFile arq4 = new RandomAccessFile("dados/temp/arq4.tmp", "rw");
        boolean switchArq2 = true;
        
        int contador = 0;
        Boolean controle = false;
        int contadorPulos = 0;
        int countBloco1 = 0;
        int countBloco2 = 0;
        int countEtapa = 0;
        int ponteiro1 = 0;
        int ponteiro2 = 0;
        Filme[] etapa2 = new Filme[(bloco*n)*2];
        
                /* ETAPA 2 - Primeira Intercalacao */
                while(temp1[ponteiro1] != null && temp2[ponteiro2] != null){
                    while(countBloco1 < bloco*n || countBloco2 < bloco*n){

                        if(temp1[ponteiro1] != null && temp2[ponteiro2] == null && countBloco1 < bloco*n){
                            etapa2[countEtapa] = temp1[ponteiro1];
                            countBloco1++;
                            ponteiro1++;
                            countEtapa++;
                        } else if(temp2[ponteiro2] != null && temp1[ponteiro1] == null && countBloco2 < bloco*n){
                            etapa2[countEtapa] = temp2[ponteiro2];
                            countBloco2++;
                            ponteiro2++;
                            countEtapa++;
                        } else if(temp1[ponteiro1] == null && temp2[ponteiro2] == null){
                            countBloco1 = bloco*n;
                            countBloco2 = bloco*n;
                        } else if(countBloco1 < bloco *n && countBloco2 >= bloco *n){
                            etapa2[countEtapa] = temp1[ponteiro1];
                            countBloco1++;
                            ponteiro1++;
                            countEtapa++;
                        } else if(countBloco2 < bloco * n && countBloco1 >= bloco * n){
                            etapa2[countEtapa] = temp2[ponteiro2];
                            countBloco2++;
                            ponteiro2++;
                            countEtapa++;
                        } else if(temp1[ponteiro1].getId() < temp2[ponteiro2].getId() && temp1[ponteiro1].getId() != -1){
                            etapa2[countEtapa] = temp1[ponteiro1];
                            countBloco1++;
                            ponteiro1++;
                            countEtapa++;
                        } else if(temp2[ponteiro2].getId() < temp1[ponteiro1].getId() && temp2[ponteiro2].getId() != -1){
                            etapa2[countEtapa] = temp2[ponteiro2];
                            countBloco2++;
                            ponteiro2++;
                            countEtapa++;
                        }
                    }

                    etapa2 = ordenar(etapa2);
                    
                    for(int i = 0; i<etapa2.length; i++){
                        if(switchArq2 == true){
                            if(etapa2[i] != null){
                                byte[] ba;
                                ba = etapa2[i].toByteArray();
                                arq3.writeInt(ba.length); 
                                arq3.write(ba);
                            }
                        } else {
                            if(etapa2[i] != null){
                                byte[] ba;
                                ba = etapa2[i].toByteArray();
                                arq4.writeInt(ba.length); 
                                arq4.write(ba);
                            }
                        }
                    }

                    switchArq2 = !(switchArq2);
                    countBloco1 = 0;
                    countBloco2 = 0;
                    countEtapa = 0;

                    for(int i = 0; i<etapa2.length; i++){
                        etapa2[i] = null;
                    }
                }


        Filme[] temp3 = lerTemp("dados/temp/arq3.tmp"); 
        Filme[] temp4 = lerTemp("dados/temp/arq4.tmp"); 
        RandomAccessFile arq5 = new RandomAccessFile("dados/temp/arq5.tmp", "rw");
        RandomAccessFile arq6 = new RandomAccessFile("dados/temp/arq6.tmp", "rw");

        switchArq2 = true;
        
        contador = 0;
        controle = false;
        contadorPulos = 0;
        countBloco1 = 0;
        countBloco2 = 0;
        countEtapa = 0;
        ponteiro1 = 0;
        ponteiro2 = 0;
        Filme[] etapa3 = new Filme[(bloco*n)*4];
        
                /* ETAPA 3 - Segunda Intercalacao */
                while(temp3[ponteiro1] != null || temp4[ponteiro2] != null){
                    while(countBloco1 < (bloco*n) * 2 || countBloco2 < (bloco*n) * 2){

                        if(temp3[ponteiro1] != null && temp4[ponteiro2] == null && countBloco1 < (bloco*n) * 2){
                            etapa3[countEtapa] = temp3[ponteiro1];
                            countBloco1++;
                            ponteiro1++;
                            countEtapa++;
                        } else if(temp4[ponteiro2] != null && temp3[ponteiro1] == null && countBloco2 < (bloco*n) * 2){
                            etapa3[countEtapa] = temp4[ponteiro2];
                            countBloco2++;
                            ponteiro2++;
                            countEtapa++;
                        } else if(temp3[ponteiro1] == null && temp4[ponteiro2] == null){
                            countBloco1 = (bloco*n) * 2;
                            countBloco2 = (bloco*n) * 2;
                        } else if(countBloco1 < (bloco*n) * 2 && countBloco2 >= (bloco*n) * 2){
                            etapa3[countEtapa] = temp3[ponteiro1];
                            countBloco1++;
                            ponteiro1++;
                            countEtapa++;
                        } else if(countBloco2 < (bloco*n) * 2 && countBloco1 >= (bloco*n) * 2){
                            etapa3[countEtapa] = temp4[ponteiro2];
                            countBloco2++;
                            ponteiro2++;
                            countEtapa++;
                        } else if(temp3[ponteiro1].getId() < temp4[ponteiro2].getId() && temp3[ponteiro1].getId() != -1){
                            etapa3[countEtapa] = temp3[ponteiro1];
                            countBloco1++;
                            ponteiro1++;
                            countEtapa++;
                        } else if(temp4[ponteiro2].getId() < temp3[ponteiro1].getId() && temp4[ponteiro2].getId() != -1){
                            etapa3[countEtapa] = temp4[ponteiro2];
                            countBloco2++;
                            ponteiro2++;
                            countEtapa++;
                        }
                    }

                    etapa3 = ordenar(etapa3);
                    
                    for(int i = 0; i<etapa3.length; i++){
                        if(switchArq2 == true){
                            if(etapa3[i] != null){
                                byte[] ba;
                                ba = etapa3[i].toByteArray();
                                arq5.writeInt(ba.length); 
                                arq5.write(ba);
                            }
                        } else {
                            if(etapa3[i] != null){
                                byte[] ba;
                                ba = etapa3[i].toByteArray();
                                arq6.writeInt(ba.length); 
                                arq6.write(ba);
                            }
                        }
                    }

                    switchArq2 = !(switchArq2);
                    countBloco1 = 0;
                    countBloco2 = 0;
                    countEtapa = 0;

                    for (int i = 0; i < etapa3.length; i++) {
                        etapa3[i] = null;
                    }
                }

        Filme[] temp5 = lerTemp("dados/temp/arq5.tmp"); 
        Filme[] temp6 = lerTemp("dados/temp/arq6.tmp");
        RandomAccessFile arq7 = new RandomAccessFile("dados/temp/arq7.tmp", "rw");

        switchArq2 = true;
        
        contador = 0;
        controle = false;
        contadorPulos = 0;
        countBloco1 = 0;
        countBloco2 = 0;
        countEtapa = 0;
        ponteiro1 = 0;
        ponteiro2 = 0;
        Filme[] etapa4 = new Filme[(bloco * n) * 8];
        
                /* ETAPA 4 - Ultima Intercalacao */
                while(temp5[ponteiro1] != null || temp6[ponteiro2] != null){
                    while(countBloco1 < (bloco*n) * 4 || countBloco2 < (bloco*n) * 4){ 
                        
                        if(temp5[ponteiro1] != null && temp6[ponteiro2] == null && countBloco1 < (bloco*n) * 4){
                            etapa4[countEtapa] = temp5[ponteiro1];
                            countBloco1++;
                            ponteiro1++;
                            countEtapa++;
                        } else if(temp6[ponteiro2] != null && temp5[ponteiro1] == null && countBloco2 < (bloco*n) * 4){
                            etapa4[countEtapa] = temp6[ponteiro2];
                            countBloco2++;
                            ponteiro2++;
                            countEtapa++;
                        } else if(temp5[ponteiro1] == null && temp6[ponteiro2] == null){
                            countBloco1 = (bloco*n) * 4;
                            countBloco2 = (bloco*n) * 4;
                        } else if(countBloco1 < (bloco*n) * 4 && countBloco2 >= (bloco*n) * 4){
                            etapa4[countEtapa] = temp5[ponteiro1];
                            countBloco1++;
                            ponteiro1++;
                            countEtapa++;
                        } else if(countBloco2 < (bloco*n) * 4 && countBloco1 >= (bloco*n) * 4){
                            etapa4[countEtapa] = temp6[ponteiro2];
                            countBloco2++;
                            ponteiro2++;
                            countEtapa++;
                        } else if(temp5[ponteiro1].getId() < temp6[ponteiro2].getId() && temp5[ponteiro1].getId() != -1){
                            etapa4[countEtapa] = temp5[ponteiro1];
                            countBloco1++;
                            ponteiro1++;
                            countEtapa++;
                        } else if(temp6[ponteiro2].getId() < temp5[ponteiro1].getId() && temp6[ponteiro2].getId() != -1){
                            etapa4[countEtapa] = temp6[ponteiro2];
                            countBloco2++;
                            ponteiro2++;
                            countEtapa++;
                        }
                    }

                    etapa4 = ordenar(etapa4);
                    
                     for(int i = 0; i<etapa4.length; i++){
                            if(etapa4[i] != null){
                                byte[] ba;
                                ba = etapa4[i].toByteArray();
                                  
                                arq7.writeInt(ba.length); 
                                arq7.write(ba);
                            }
                    } 

                    countBloco1 = 0;
                    countBloco2 = 0;
                    countEtapa = 0;

                    for (int i = 0; i < etapa4.length; i++) {
                        etapa4[i] = null;
                    }
                }

        Filme[] temp7 = lerTemp("dados/temp/arq7.tmp");

        System.out.println("\nDEPOIS => ");
        System.out.print("[");
        for(int i = 0; i<temp7.length; i++){
            if(temp7[i] != null){
                System.out.print(temp7[i].getId());
                System.out.print(" ");
            }
        } 
        System.out.print("]"); 

        return temp7;
        
    }

    public static Filme[] IntercalacaoBalanceadaVariavel(int bloco, int n, Filme[] imdb) throws Exception {
        int contadorBloco = 0;
        Filme[] ordenados = new Filme[bloco];

        RandomAccessFile arq1 = new RandomAccessFile("dados/temp/arq1.tmp", "rw");
        RandomAccessFile arq2 = new RandomAccessFile("dados/temp/arq2.tmp", "rw");
        boolean switchArq1 = true;

        for (int i = 0; i < imdb.length; i++) {
            if (imdb[i] != null) {
                if (contadorBloco == bloco) {
                    ordenados = ordenar(ordenados);
                    if (switchArq1 == true) {
                        byte[] ba;
                        int contador = 0;

                        while (contador < ordenados.length) {
                            if(ordenados[contador].getId() != -1){
                                ba = ordenados[contador].toByteArray();
                                arq1.writeInt(ba.length);
                                arq1.write(ba);
                            }

                                contador++;
                        }

                        for (int a = 0; a < ordenados.length; a++) {
                            ordenados[a] = null;
                        }

                        switchArq1 = false;
                        i--;
                        contadorBloco = 0;
                    } else {
                        byte[] ba;
                        int contador = 0;

                        while (contador < ordenados.length) {
                            if (ordenados[contador].getId() != -1) {
                                ba = ordenados[contador].toByteArray();
                                arq2.writeInt(ba.length);
                                arq2.write(ba);
                            }

                                contador++;
                        }

                        for(int a=0; a<ordenados.length; a++){
                            ordenados[a] = null;
                        }

                        switchArq1 = true;
                        i--;
                        contadorBloco = 0;
                    }
                } else {
                    ordenados[contadorBloco] = imdb[i];
                    contadorBloco++;
                }
            } else {
                if (ordenados[0] != null) {
                    ordenados = ordenar(ordenados);
                    if (switchArq1 == true) {
                        byte[] ba;
                        int contador = 0;

                        while (contador < ordenados.length) {
                            if (ordenados[contador] != null) {
                                ba = ordenados[contador].toByteArray();
                                arq1.writeInt(ba.length);
                                arq1.write(ba);
                            }
                            contador++;
                        }
                        ordenados[0] = null;
                    } else {
                        byte[] ba;
                        int contador = 0;

                        while (contador < ordenados.length) {
                            if (ordenados[contador] != null) {
                                ba = ordenados[contador].toByteArray();
                                arq2.writeInt(ba.length);
                                arq2.write(ba);
                            }
                            contador++;
                        }
                        ordenados[0] = null;
                    }
                }
            }
        }

        Filme[] temp1 = lerTemp("dados/temp/arq1.tmp");
        Filme[] temp2 = lerTemp("dados/temp/arq2.tmp");

        RandomAccessFile arq3 = new RandomAccessFile("dados/temp/arq3.tmp", "rw");
        RandomAccessFile arq4 = new RandomAccessFile("dados/temp/arq4.tmp", "rw");

        Boolean switchArq2 = true;
        Boolean controle1 = false;
        Boolean controle2  = false;
        Boolean controleArq = false;
        int countEtapa = 0;
        int ponteiro1 = 0;
        int ponteiro2 = 0;
        Filme[] etapa2 = new Filme[3000];

        /* ETAPA 2 - Primeira Intercalacao */
        while (temp1[ponteiro1] != null || temp2[ponteiro2] != null) {
            while (controle1 == false || controle2 == false) {

                if (temp1[ponteiro1] == null && temp2[ponteiro2] == null) {
                    controle1 = true;
                    controle2 = true;
                }
                else if(temp1[ponteiro1] == null){
                    controle1 = true;
                    ponteiro1++;
                } 
                else if (temp1[ponteiro1] != null && temp1[ponteiro1+1] != null) {
                    if(temp1[ponteiro1].getId() < temp1[ponteiro1+1].getId()){
                        etapa2[countEtapa] = temp1[ponteiro1];
                        ponteiro1++;
                        countEtapa++;
                    } else{
                        controle1 = true;
                        ponteiro1++;
                    }
                } else{
                    etapa2[countEtapa] = temp1[ponteiro1];
                    controle1 = true;
                    ponteiro1++;
                    countEtapa++;
                }
                if (temp2[ponteiro2] == null) {
                    controle2 = true;
                    ponteiro2++;
                }
                else if (temp2[ponteiro2] != null && temp2[ponteiro2 + 1] != null) {
                    if (temp2[ponteiro2].getId() < temp2[ponteiro2 + 1].getId()) {
                        etapa2[countEtapa] = temp2[ponteiro2];
                        ponteiro2++;
                        countEtapa++;
                    } else {
                        controle2 = true;
                        ponteiro2++;
                    }
                } else{
                    etapa2[countEtapa] = temp2[ponteiro2];
                    controle2 = true;
                    ponteiro2++;
                    countEtapa++;
                }
                
            }
            etapa2 = ordenar(etapa2);
            
            for (int i = 0; i < etapa2.length; i++) { 
                if(switchArq2 == true){
                    if (etapa2[i] != null) {
                        byte[] ba;
                        ba = etapa2[i].toByteArray();
                        arq3.writeInt(ba.length);
                        arq3.write(ba);
                    }
                } else {
                    if (etapa2[i] != null) {
                        byte[] ba;
                        ba = etapa2[i].toByteArray();
                        arq4.writeInt(ba.length);
                        arq4.write(ba);
                        controleArq = true;
                    }
                } 
                    
            }

            switchArq2 = !switchArq2;
            countEtapa = 0;
            controle1 = false;
            controle2 = false;

            for (int i = 0; i < etapa2.length; i++) {
                etapa2[i] = null;
            }
        }

        Filme[] temp3 = lerTemp("dados/temp/arq3.tmp");
        Filme[] temp4 = lerTemp("dados/temp/arq4.tmp");

        if(controleArq){
            RandomAccessFile arq5 = new RandomAccessFile("dados/temp/arq5.tmp", "rw");
            controle1 = false;
            controle2 = false;
            controleArq = false;
            countEtapa = 0;
            ponteiro1 = 0;
            ponteiro2 = 0;
            Filme[] etapa3 = new Filme[3000];

            /* ETAPA 2 - Primeira Intercalacao */
            while (temp3[ponteiro1] != null || temp4[ponteiro2] != null) {
                while (controle1 == false || controle2 == false) {

                    if (temp3[ponteiro1] == null && temp4[ponteiro2] == null) {
                        controle1 = true;
                        controle2 = true;
                    } else if (temp3[ponteiro1] == null) {
                        controle1 = true;
                        ponteiro1++;
                    } else if (temp3[ponteiro1] != null && temp3[ponteiro1 + 1] != null) {
                        if (temp3[ponteiro1].getId() < temp3[ponteiro1 + 1].getId()) {
                            etapa3[countEtapa] = temp3[ponteiro1];
                            ponteiro1++;
                            countEtapa++;
                        } else {
                            controle1 = true;
                            ponteiro1++;
                        }
                    } else {
                        etapa3[countEtapa] = temp3[ponteiro1];
                        controle1 = true;
                        ponteiro1++;
                        countEtapa++;
                    }
                    if (temp4[ponteiro2] == null) {
                        controle2 = true;
                        ponteiro2++;
                    } else if (temp4[ponteiro2] != null && temp4[ponteiro2 + 1] != null) {
                        if (temp4[ponteiro2].getId() < temp4[ponteiro2 + 1].getId()) {
                            etapa3[countEtapa] = temp4[ponteiro2];
                            ponteiro2++;
                            countEtapa++;
                        } else {
                            controle2 = true;
                            ponteiro2++;
                        }
                    } else {
                        etapa3[countEtapa] = temp4[ponteiro2];
                        controle2 = true;
                        ponteiro2++;
                        countEtapa++;
                    }

                }
                etapa3 = ordenar(etapa3);

                for (int i = 0; i < etapa3.length; i++) {
                        if (etapa3[i] != null) {
                            byte[] ba;
                            ba = etapa3[i].toByteArray();
                            arq5.writeInt(ba.length);
                            arq5.write(ba);
                        }
                }

                switchArq2 = !switchArq2;
                countEtapa = 0;
                controle1 = false;
                controle2 = false;

                for (int i = 0; i < etapa3.length; i++) {
                    etapa3[i] = null;
                }
            }

            Filme[] temp5 = lerTemp("dados/temp/arq5.tmp");
            System.out.println("\nDEPOIS => ");
            System.out.print("[");
            for (int i = 0; i < temp5.length; i++) {
                if (temp5[i] != null) {
                    System.out.print(temp5[i].getId());
                    System.out.print(" ");
                }
            }
            System.out.print("]");

            return temp5;
            
        } else{
            System.out.println("\nDEPOIS => ");
            System.out.print("[");
            for (int i = 0; i < temp3.length; i++) {
                if (temp3[i] != null) {
                    System.out.print(temp3[i].getId());
                    System.out.print(" ");
                }
            }
            System.out.print("]");

            return temp3;
        }
    }
    
    public static Filme[] lerTemp(String path) throws Exception{
        int len;
        byte[] ba;
        int contador = 0;
        boolean controle = false;

        RandomAccessFile arq = new RandomAccessFile(path, "rw");
        Filme[] temps = new Filme[3000];
        
        while(controle == false){
            try {
                Filme filme_temp = new Filme();
                len = arq.readInt();
                ba = new byte[len];
                arq.read(ba);
                filme_temp.fromByteArray(ba);

                temps[contador] = filme_temp;

                contador++;
            } catch (Exception e) {
                break;
            }
        }

        return temps;
        
    }

    public static void deletarFilme(int id, Filme[] imdb){
        int contador = 0;
        if(id != -1){
            while(imdb[contador] != null){
                if(Objects.equals(imdb[contador].getId(), id)){
                    imdb[contador].setId(-1);
                    System.out.println("\n---- Filme deletado com sucesso! ----");
                }
                contador++;
            }
        } else{
            System.out.println("\n---- ID invalido! ----");
        }

    }

    public static void alterarFilme(Filme alterar, Filme[]imdb)throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("-- Qual o novo nome do Filme? --");
        String nome = sc.nextLine();

        System.out.println("-- Qual a nova data do Filme? --");
        Date ano = formatter.parse(sc.nextLine());

        System.out.println("-- Qual a nova nota do Filme? --");
        Float nota = Float.parseFloat(sc.nextLine()) ;

        System.out.println("-- Qual o novo certificado do Filme? --");
        String certificadoString = sc.nextLine();
        char[] certificado = new char[3];
        for(int i = 0; i<certificadoString.length(); i++){
            certificado[i] = certificadoString.charAt(i);
        }

        System.out.println("-- Quais os novos generos do Filme? --");
        String generoString = sc.nextLine();
        String[] generos = generoString.split(",");
        Filme alterado = new Filme(alterar.getId(), nome, ano, nota, certificado, generos);

        System.out.println("\n---- Filme atualizado com sucesso! ----");
        gravarAlterado(alterar, alterado, imdb);

    }

    public static void gravarAlterado(Filme alterar, Filme alterado, Filme[]imdb)throws Exception{
        byte[] ba;
        ba = alterar.toByteArray();
        byte[] bb;
        bb = alterado.toByteArray();


        if(Objects.equals(ba.length, bb.length)){
            for(int i = 0; i<imdb.length; i++){
                if(imdb[i] != null){
                    if(Objects.equals(imdb[i].getId(), alterar.getId())){
                        imdb[i] = alterado;
                    }
                }
            }
            gravarArquivo(imdb);
        } else {
            for(int i = 0; i<imdb.length; i++){
                if(imdb[i] != null){
                    if(Objects.equals(imdb[i].getId(), alterar.getId())){
                        imdb[i].setId(-1);
                    }
                    if(imdb[i+1] == null){
                        imdb[i+1] = alterado;
                        i = imdb.length;
                }
                }
            }
            gravarArquivo(imdb);
        }
        
    }


    public static Filme encontrarFilme(Integer id, Filme[] imdb) throws Exception{
        byte[] ba;
        int len;
        int i = 0;
        boolean controle = false;

        RandomAccessFile arq = new RandomAccessFile("dados/Imdb.bin", "rw");
        Filme filme_temp = new Filme();
        while(imdb[i] != null && controle == false){
            len = arq.readInt();
            ba = new byte[len];
            arq.read(ba);
            filme_temp.fromByteArray(ba);
            if(Objects.equals(filme_temp.getId(), id)){
                filme_temp.printFilme();
                controle = true;
            }
            i++;
        }

        return filme_temp;
        
    }

    public static void gravarArquivo(Filme[] imdb) throws Exception{
        byte[] ba;
        int contador = 0;

        RandomAccessFile arq = new RandomAccessFile("dados/Imdb.bin", "rw");
        arq.seek(0);

        while(contador < imdb.length && imdb[contador] != null){
            try {
                ba = imdb[contador].toByteArray();
                arq.writeInt(ba.length); 
                arq.write(ba);

                contador++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        arq.close();
        
    }

    public static Filme[] lerDadosDaBase() throws Exception{
        File path = new File("./imdb_top_1000.csv");
        Scanner sc = new Scanner(path);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

        Filme[] imdbFilmes = new Filme[3000];
        int id = 0;
        int contArray = 0;

        sc.nextLine(); /* pula o cabecalho */
        
        while(sc.hasNextLine()){
            String linha = sc.nextLine();
            String[] linhaSeparada = linha.split(",");
            
            String nome = linhaSeparada[4];
            Date ano = null;
            Float nota = null;
            int contLinha = 4;

            
            if(nome.charAt(0) == '\"'){
                while(nome.charAt(nome.length()-1) != '\"'){
                    nome += linhaSeparada[++contLinha];
                }
            }

            
            if(linhaSeparada[contLinha+1].charAt(0) >= '0' && 
            linhaSeparada[contLinha+1].charAt(0) <= '9'){
                ano = formatter.parse(linhaSeparada[++contLinha]);
            }


            char[] certificado = new char[3];
            if(certificado.length >= linhaSeparada[contLinha+1].length()){
                for(int i = 0; i < linhaSeparada[contLinha+1].length(); i++){
                    certificado[i] = linhaSeparada[contLinha+1].charAt(i);
                }
            }


            String genero = linhaSeparada[contLinha+3];
            if(genero.charAt(0) == '\"'){
                while(genero.charAt(genero.length()-1) != '\"'){
                    genero += ", " + linhaSeparada[++contLinha+3];
                }
            }
            String[] generoArray = genero.split(",");


            if(linhaSeparada[contLinha+4].charAt(0) >= '0' && 
            linhaSeparada[contLinha+4].charAt(0) <= '9'){
                nota = Float.parseFloat(linhaSeparada[contLinha+4]);
            }


            if(nota != null && ano != null){
                imdbFilmes[contArray] = new Filme(++id, nome, ano, nota, certificado, generoArray);
                imdbFilmes[contArray].printFilme();
                contArray++;
            }

        };

        sc.close();

        return imdbFilmes;

    }

    public static Integer entrada(){
        int entrada = 0;

        while(entrada != 1 && entrada != 6){
        System.out.println("\n----- Aperte o numero da opcao desejada -----");
        System.out.println("--> [1]- Ler Dados da Base do Imdb");
        System.out.println("--> [2]- Ler um Registro");
        System.out.println("--> [3]- Atualizar um Registro");
        System.out.println("--> [4]- Deletar um Registro");
        System.out.println("--> [5]- Ordenacao Externa");
        System.out.println("--> [6]- Encerrar o Programa");

        Scanner sc = new Scanner(System.in);
        entrada = sc.nextInt();

        if(entrada != 1 && entrada != 6){
            System.out.println("---- Carga de dados da base, ainda nao realizada. ----");
        }
        
        }

        return entrada;
    }

    public static Integer entradaAposDados(){
        int entrada = 0;

        System.out.println("\n----- Aperte o numero da opcao desejada -----");
        System.out.println("--> [2]- Ler um Registro");
        System.out.println("--> [3]- Atualizar um Registro");
        System.out.println("--> [4]- Deletar um Registro");
        System.out.println("--> [5]- Ordenacao Externa");
        System.out.println("--> [6]- Encerrar o Programa");

        Scanner sc = new Scanner(System.in);
        entrada = sc.nextInt();

        return entrada;
    }
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        int entrada = entrada();
        Filme[]Imdb;

        Imdb = lerDadosDaBase();
        gravarArquivo(Imdb);
        System.out.println("---- Base de dados carregada com sucesso! ----\n");
/*         Random gerador = new Random();
        for (int i=0; i < (Imdb.length - 1); i++) {

			int j = gerador.nextInt(Imdb.length);
            if(Imdb[i] != null && Imdb[j] != null){
                Filme temp = Imdb[i];
                Imdb[i] = Imdb[j];
                Imdb[j] = temp;
            }
		} */

        while(entrada != 6 ){
            entrada = entradaAposDados();
            if(entrada == 2){
                System.out.println("-- Qual o id do Filme que deseja PROCURAR? --");
                int id = sc.nextInt();
                encontrarFilme(id, Imdb);
            } else if(entrada == 3){
                System.out.println("-- Qual o id do Filme que deseja ALTERAR? --");
                int id = sc.nextInt();
                if(id != -1){
                    Filme alterar = encontrarFilme(id, Imdb);
                    alterarFilme(alterar, Imdb);
                } else{
                    System.out.println("-- ID invalido! --");
                }
            } else if(entrada == 4){
                System.out.println("-- Qual o id do Filme que deseja DELETAR? --");
                int id = sc.nextInt();
                deletarFilme(id, Imdb);
            } else if(entrada == 5){
                System.out.println("\n----- Aperte o numero da opcao desejada -----");
                System.out.println("--> [1]- Intercalação balanceada comum");
                System.out.println("--> [2]- Intercalação balanceada com blocos de tamanho variável");
                System.out.println("--> [3]- Intercalação balanceada com seleção por substituição");
                int intercalacao = sc.nextInt();
                
                if(intercalacao == 1){
                    System.out.println("-- De um valor para M: (Valor recomendado: 4)--");
                    int m = sc.nextInt();
                    System.out.println("-- De um valor para N: (Valor recomendado: 1)--");
                    int n = sc.nextInt();
                    System.out.println("\nANTES => ");
                    System.out.print("[");
                    for(int i = 0; i<Imdb.length; i++){
                        if(Imdb[i] != null){
                            System.out.print(Imdb[i].getId());
                            System.out.print(" ");
                     }
                    } 
                    System.out.print("]");
                    Imdb = IntercalacaoBalanceada(m, n, Imdb);
                }
                else if (intercalacao == 2) {
                    System.out.println("-- De um valor para M: (Valor recomendado: 4)--");
                    int m = sc.nextInt();
                    System.out.println("-- De um valor para N: (Valor recomendado: 1)--");
                    int n = sc.nextInt();
                    System.out.println("\nANTES => ");
                    System.out.print("[");
                    for (int i = 0; i < Imdb.length; i++) {
                        if (Imdb[i] != null) {
                            System.out.print(Imdb[i].getId());
                            System.out.print(" ");
                        }
                    }
                    System.out.print("]");
                    Imdb = IntercalacaoBalanceadaVariavel(m, n, Imdb);
                }
            }
        }
    }
}