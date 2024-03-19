package src;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PaginasPDF implements PropertyChangeListener {

    private Lineas index;
    private File file;
    PDDocument document;
    PaginasPDF(Lineas index,File file) {
        try {
            document = Loader.loadPDF(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.index = index;
        this.file = file;
    }

     public void propertyChange(PropertyChangeEvent evento){
        String text = "";
        String palabra = "";
        boolean flagANd = true;
        List<Integer> paginasIndex = new ArrayList<Integer>();
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        StringBuilder concatenatedText = new StringBuilder();
        
        ArrayList<String> frase = (ArrayList<String>) evento.getNewValue();
        ArrayList<String> words = new ArrayList<>();
        words.addAll(Arrays.asList(frase.get(frase.size()-1).split(" ")));
        palabra = frase.get(frase.size()-1);
        if (words.size() > 1 && words.get(words.size()-2).equals("AND")) {
            
           if(checarFormatoAND(words)){
                return; // Formato invalido para palabras con AND
           }

           
                for (int pageNumber = 1; pageNumber <= document.getNumberOfPages(); pageNumber++) {

                    pdfTextStripper.setStartPage(pageNumber);
                    pdfTextStripper.setEndPage(pageNumber);
                    try {
                        text = removeSpecialSymbols(pdfTextStripper.getText(this.document));
                        concatenatedText.append(text).append("\n");
                    } catch (Exception e) {
                        
                    }
                    for (String palabraEnLista : words) {
                        if (!concatenatedText.toString().toLowerCase().contains(palabraEnLista.toLowerCase())) {
                            flagANd = false;
                            break;
                        }

                        
                    }
                    if(flagANd){
                        paginasIndex.add(pageNumber);
                    }
                    
                    flagANd = true;
                    concatenatedText.setLength(0);
                }
               
            
            
            
        }else{
            
            for (int pageNumber = 1; pageNumber <= document.getNumberOfPages(); pageNumber++) {
                pdfTextStripper.setStartPage(pageNumber);
                pdfTextStripper.setEndPage(pageNumber);
                try {
                    text = removeSpecialSymbols(pdfTextStripper.getText(this.document));
                    concatenatedText.append(text).append("\n");
                } catch (Exception e) {
                    
                }
                if(concatenatedText.toString().toLowerCase().contains(palabra.toLowerCase())){
                    paginasIndex.add(pageNumber);
                }
                concatenatedText.setLength(0);
            }
            
        }
       
       
        index.insert(palabra,paginasIndex);

        
        
    }
    public boolean checarFormatoAND(List<String> list) {
        // Check if the size of the list is even
        if (list.size() % 2 != 0) {
            return false; // Invalid format: odd number of elements
        }

        for (int i = 0; i < list.size(); i += 2) {
            // Check if the current and next elements form the "AND" sequence
            if (!list.get(i).equalsIgnoreCase("AND") || i == list.size() - 1 || !list.get(i + 1).equalsIgnoreCase("AND")) {
                return false; // Invalid format: not "word AND word" sequence
            }
        }
        return true; // All pairs follow the "word AND word" format
    }

    public String removeSpecialSymbols(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String result = normalized.replaceAll("[^\\p{L}\\p{M}0-9\\s]", "");
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(result).replaceAll("");
        }
}
