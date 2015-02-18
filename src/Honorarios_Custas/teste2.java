/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Honorarios_Custas;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

import java.text.*;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author f7057419
 */
public class teste2 {

    public static void main(String args[]) throws FileNotFoundException {

        Random gerador = new Random();
        int numero = gerador.nextInt(256);
        System.out.println(numero);

        Date dataSistema = new Date();

        Date dataHoje = new Date();
        SimpleDateFormat formataData2 = new SimpleDateFormat("dd_MM_yyyy");
        String data = formataData2.format(dataHoje);
        System.out.println(data);
//System.out.println(dataSistema);

        FileOutputStream arquivo;

        String datateste = "c://temp/testedd.txt";
        arquivo = new FileOutputStream("c://temp/testedd" + data + ".txt");
        PrintStream printStream = new PrintStream(arquivo);
        printStream.println("teste");

        try {
            String datad = "16/07/2008";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(datad));
            System.out.println(datad);
        } catch (ParseException e) {
        }
    }
}
