package src;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Lineas {
    private List<String> lineas;
    private HashMap<String, List<Integer>> hashPalabras;

    
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public Lineas(){
        lineas = new ArrayList<>();
        hashPalabras = new HashMap<>();
    }
   
   
    public void agregarEscuchador(PropertyChangeListener funcion) {
        support.addPropertyChangeListener(funcion);
    }

    public void insert(String lineaNueva) {
        List<String> lineaAnterior = new ArrayList<>(lineas);
        lineas.add(lineaNueva);
        support.firePropertyChange("lineas", lineaAnterior, lineas);
    }

    public void insert(String lineaNueva, List<Integer> paginas) {
        List<String> lineaAnterior = new ArrayList<>(lineas);
        lineas.add(lineaNueva);
        hashPalabras.put(lineaNueva, paginas);
        support.firePropertyChange("lineas", lineaAnterior, hashPalabras);
        
    }

    public void insert(int index,String frase,HashMap<String, List<Integer>> hashPalabras) {
        List<String> lineaAnterior = new ArrayList<>(lineas);
        String listaPaginas = "";
        for (int i = 0; i < hashPalabras.get(frase).size(); i++) {
            listaPaginas += hashPalabras.get(frase).get(i) + ", ";
        }
        try {
            listaPaginas = listaPaginas.substring(0, listaPaginas.length() - 2); // remover la ultima coma
            // TODO: handle exception
        }catch (Exception e){
            return;
        }
        


        String lineaNueva = ""+frase+" ["+listaPaginas+"]";
        lineas.add(index, lineaNueva);
    }
    public void insert(int index,String lineaNueva){
        lineas.add(index,lineaNueva);
    }
    public String getUltimoInsert(){
        return lineas.remove(lineas.size() - 1);
    }

    public List<String> getLineas() {
        return lineas;
        
    }
    public HashMap<String, List<Integer>> getHashPalabras() {
        return hashPalabras;
    }
}
