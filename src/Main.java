package src;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.apache.pdfbox.util.*;
public class Main {

    public static void main(String[] args) {


        boolean continuar = true;


        Lineas lineas = new Lineas();
        Lineas shifts = new Lineas();

        Input input = new Input();

        File file = new File("harry-potter-1.pdf");
        File file2 = new File("8 YummySAD IBM.pdf");
       try {
        String text = input.getText(file2);
       } catch (Exception e) {
        // TODO: handle exception
       }
        
       // CircularShift circularShift = new CircularShift(shifts);
       /*
        Alphabetizer alphabetizer = new Alphabetizer();
        Output output = new Output();

        //lineas.agregarEscuchador(circularShift);
        shifts.agregarEscuchador(alphabetizer);

        try {
            input.readFile(lineas, new File(path));
            output.writeFile(shifts, new File(path2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }


    


}

