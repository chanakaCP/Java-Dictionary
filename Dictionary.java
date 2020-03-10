
package dictionary;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Dictionary {


    public static void main(String[] args) {
        java.io.File dictionaryFile = new java.io.File("dictionary.txt");
        java.io.File textFile = new java.io.File("Text.txt");

        String[] dicTable = new String[1000];

        try {

            Scanner txt = new Scanner(dictionaryFile);

            while (txt.hasNext()) {
                String str1 = txt.next().toLowerCase();
                String str2 = txt.next().toLowerCase();

                char[] y = str1.toCharArray();
                long temp = 0;

                for (int i = 0; i < y.length; i++) {
                    temp = (long) (temp + y[i] * Math.pow(31, i));
                }
                int i = (int) (temp % 997);
                dicTable[i] = str2;
            }
        } catch (FileNotFoundException e) {
            System.err.format("Can`t fond file !!!!!");
        }

        String[] p = new String[100];
        int i = 0;

        try {

            Scanner n = new Scanner(textFile);

            while (n.hasNext()) {

                String str = n.next().toLowerCase();
                char[] chr = str.toCharArray();
                long value = 0;

                for (int j = 0; j < chr.length; j++) {
                    value = (long) (value + chr[j] * Math.pow(31, j));
                }

                int v = (int) (value % 997);

                if (str.equals("is") || str.equals("it") || str.equals("did") || str.equals("the")  ) {
                    //for Processed sentence
                } else if (dicTable[v] == null) {
                    p[i] = "["+str+"]";
                    i++;
                } else {
                    p[i] = " "+dicTable[v]+" ";
                    i++;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.format("Can`t fond file !");
        }

        try {
            try (FileWriter frenchFile = new FileWriter("french.txt")) {
                for (int j = 0; j < i; j++) {
                    frenchFile.write(p[j]);
                    System.out.print(p[j]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error!!");
        }

    }
}
