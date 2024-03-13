package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.regex.Pattern;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


class Input {
    public void readFile(Lineas lines, File file) throws IOException {
        for (String line : Files.readAllLines(Paths.get(file.getName()))) {
            lines.insert(line);
        }
    }
    public String getText(File pdfFile) throws IOException {
        PDDocument document = Loader.loadPDF(pdfFile);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        
       
        for (int pageNumber = 1; pageNumber <= document.getNumberOfPages(); pageNumber++) {
            pdfTextStripper.setStartPage(pageNumber);
            pdfTextStripper.setEndPage(pageNumber);
            String text = removeSpecialSymbols(pdfTextStripper.getText(document));
            
            System.out.println(text);
           // java.io.PrintStream p = new java.io.PrintStream(System.out,false,"UTF-8");
           // p.println(text);
        }
        
        //System.out.println(document.getNumberOfPages());
        return new PDFTextStripper().getText(document);
        }
    public String removeSpecialSymbols(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String result = normalized.replaceAll("[^\\p{L}\\p{M}0-9\\s]", "");
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(result).replaceAll("");
        }
    
}
