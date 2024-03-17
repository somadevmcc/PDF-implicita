package src;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
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

