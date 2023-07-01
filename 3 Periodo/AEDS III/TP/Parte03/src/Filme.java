import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.RandomAccessFile;

import java.io.IOException;

class Filme {

    // Atributos
    public int id;
    public String titulo;
    public String lingua;
    public Date data;
    public String[] generos;

    // Construtor
    public Filme(int id, String titulo, String lingua, Date data, String[] generos) {
        this.id = id;
        this.titulo = titulo;
        this.lingua = lingua;
        this.data = data;
        this.generos = generos;
    }

    // Setters e Getters

    public Filme() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String[] getGeneros() {
        return generos;
    }

    public void setGeneros(String[] generos) {
        this.generos = generos;
    }

    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(id);
        dos.writeUTF(titulo);
        dos.writeUTF(lingua);
        dos.writeUTF(data.toString());
        for (int i = 0; i < generos.length; i++) {
            dos.writeUTF(generos[i]);
        }

        return baos.toByteArray();
    }

    public void fromByteArray(byte ba[]) throws IOException, ParseException {

        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        String generosSemSeparacao = "", dataString;
        SimpleDateFormat dataTeste = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        id = dis.readInt();
        titulo = dis.readUTF();
        lingua = dis.readUTF();
        dataString = dis.readUTF();
        data = dataTeste.parse(dataString);

        boolean continuar = true;
        while (continuar) {
            try {
                generosSemSeparacao += dis.readUTF() + ";";
            } catch (java.io.EOFException e) {
                continuar = false;
            }
        }
        generos = generosSemSeparacao.split(";");
    }

    public void fromByteArrayToDelete(byte ba[]) throws IOException, ParseException {

        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        String generosSemSeparacao = "", dataString;
        SimpleDateFormat dataTeste = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        id = dis.readInt();
        id = 0;
        titulo = dis.readUTF();
        lingua = dis.readUTF();
        dataString = dis.readUTF();
        data = dataTeste.parse(dataString);

        boolean continuar = true;
        while (continuar) {
            try {
                generosSemSeparacao = dis.readUTF() + ";" + dis.readUTF();
            } catch (java.io.EOFException e) {
                continuar = false;
            }
        }
        generos = generosSemSeparacao.split(";");
    }

}


