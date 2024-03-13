package src;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

class Lineas {
    private List<String> lineas;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public Lineas(){
        lineas = new ArrayList<>();
    }
   

    public void agregarEscuchador(PropertyChangeListener funcion) {
        support.addPropertyChangeListener(funcion);
    }

    public void insert(String lineaNueva) {
        List<String> lineaAnterior = new ArrayList<>(lineas);
        lineas.add(lineaNueva);
        support.firePropertyChange("lineas", lineaAnterior, lineas);
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
}
