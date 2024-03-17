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
        Lineas index = new Lineas();

        Input input = new Input();

        File file = new File("harry-potter-5.pdf");
        PageCheck pdf = new PageCheck(index,file);
        Alphabetizer alphabetizer = new Alphabetizer();
        Output output = new Output();
        lineas.agregarEscuchador(pdf);
        index.agregarEscuchador(alphabetizer);
        
        try {
            input.readFile(lineas, new File("input.txt"));
            output.writeFile(index, new File("output.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     
    }


    


}

