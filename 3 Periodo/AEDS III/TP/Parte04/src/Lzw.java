import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

class LZW {

    public void compress(String source, String destin,int numeroEscolhido) throws Exception {

        //converte o arquivo em string
        String originString = "";
        long posIni = 0;
        boolean valido;
        int len;
        byte ba[];
        Filme filmeTemp = new Filme();

        try {

            RandomAccessFile raf = new RandomAccessFile(source, "rw");

            raf.seek(posIni);
            while (raf.getFilePointer() < raf.length() - 1) {
                valido = raf.readBoolean();//ler lapide, se verdadeiro, o filme existe, se não o filme é apagado
                len = raf.readInt(); //ler tamanho do registro
                ba = new byte[len]; //cria um vetor de bytes com o tamanho do registro
                raf.read(ba); //ler registro
                filmeTemp.fromByteArray(ba); //transforma vetor de bytes lido por instancia de filme
                posIni = raf.getFilePointer();//marca posição que acabou o registro e será iniciado outro

                //concatena as informações do filme à string de origem
                originString += "true" + "~";
                originString += len + "~";
                originString += filmeTemp.id + "~";
                originString += filmeTemp.titulo + "~";
                originString += filmeTemp.lingua + "~";
                originString += filmeTemp.data + "~";
                originString += filmeTemp.generos.length + "~";
                for (int j = 0; j < filmeTemp.generos.length; j++) {
                    originString += filmeTemp.generos[j] + "~";
                }

            }

            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    
        //cria o dicionário
        //converte espaços em branco na string de origem para evitar problemas de delimitação
        originString = originString.replaceAll(" ", "\\^");

        ArrayList<String> dictionary = new ArrayList<String>();

        for (int i = 0; i < originString.length(); i++) {

            String s = Character.toString(originString.charAt(i));

            if (!dictionary.contains(s))
                dictionary.add(s);
        }


        //cria a saída do dicionário
        ArrayList<Integer> output = new ArrayList<Integer>();

        for (int i = 0; i < originString.length(); i++) {

            String s = Character.toString(originString.charAt(i));

            while (true) {

                if (i == originString.length() - 1)
                    break;

                s += originString.charAt(i + 1);

                if (dictionary.contains(s)) {

                    if (i == originString.length() - 2) {

                        output.add(dictionary.indexOf(s));
                        break;
                    } else
                        i++;
                } else {

                    dictionary.add(s);

                    if (i == originString.length() - 2)
                        output.add(dictionary.indexOf(s));
                    else
                        output.add(dictionary.indexOf(s.substring(0, s.length() - 1)));
                    break;
                }
            }

            if (i == originString.length() - 1)
                break;
        }

        //cria o arquivo comprimido
        RandomAccessFile raf = new RandomAccessFile(destin, "rw");
        RandomAccessFile raf2 = new RandomAccessFile("dados/dicionarioLZW"+numeroEscolhido+".db", "rw");

        //escreve o tamanho do dicionário no arquivo
        raf2.writeInt(dictionary.size());

        //escreve cada entrada do dicionário no arquivo
        for (String str : dictionary)
        raf2.writeUTF(str);

        //escreve o tamanho da saída no arquivo
        raf2.writeInt(output.size());

        //escreve os valores da saída no arquivo
        if (dictionary.size() < 256) {
            for (int i : output)
                raf.writeByte(i);
        } else if (dictionary.size() < 65536) {

            for (int i : output)
                raf.writeShort(i);
        } else {

            for (int i : output)
                raf.writeInt(i);
        }

        raf.close();

        //remove o arquivo de origem
        new File(source).delete();
    }

    public void decompress(String source, String destin,int numeroEscolhido) {

        ArrayList<String> dictionary = new ArrayList<String>();
        ArrayList<Integer> output = new ArrayList<Integer>();

        //lê o dicionário e a saída
        try {

        RandomAccessFile raf = new RandomAccessFile(source, "rw");
        RandomAccessFile raf2 = new RandomAccessFile("dados/dicionarioLZW"+numeroEscolhido+".db", "rw");

            //lê o tamanho do dicionário
            int dictionarySize = raf2.readInt();

            //lê cada entrada do dicionário
            for (int i = 0; i < dictionarySize; i++)
                dictionary.add(raf2.readUTF());

            //lê o tamanho da saída
            int outputSize = raf2.readInt();

            //lê as saídas do arquivo
            if (dictionarySize < 256) {

                for (int i = 0; i < outputSize; i++)
                    output.add((int) raf.readByte());
            } else if (dictionarySize < 65536) {

                for (int i = 0; i < outputSize; i++)
                    output.add((int) raf.readShort());
            } else {

                for (int i = 0; i < outputSize; i++)
                    output.add(raf.readInt());
            }

            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //cria uma string "descompactada"
        String file = "";

        for (int i : output)
            file += dictionary.get(i);

        //converte os marcadores de espaço em branco de volta para espaços em branco
        file = file.replaceAll("\\^", " ");


        //cria um arquivo descompactado
        String args[] = file.split("~");

        try {

            RandomAccessFile raf = new RandomAccessFile(destin, "rw");
            int generosLen = 0;

            for (int i = 0; i < args.length; i++) {



                raf.writeBoolean(Boolean.parseBoolean(args[i]));//escreve lapide
                raf.writeInt(Integer.parseInt(args[++i]));//escreve id
                raf.writeInt(Integer.parseInt(args[++i]));// escreve len
                raf.writeUTF(args[++i]);//escreve titulo
                raf.writeUTF(args[++i]);//escreve lingua
                raf.writeUTF(args[++i]);//escreve data
                generosLen = Integer.parseInt(args[++i]);
                for (int x = 0; x < generosLen; x++)
                    raf.writeUTF(args[++i]);
            }

            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}