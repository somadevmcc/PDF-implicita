package src;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class CircularShift implements PropertyChangeListener {

    private Lineas shifts;

    CircularShift(Lineas shifts) {
        this.shifts = shifts;
    }

   
    public void propertyChange(PropertyChangeEvent evento) {
        List<String> result = new LinkedList<>();
        ArrayList<String> frase = (ArrayList<String>) evento.getNewValue();
        ArrayList<String> words = new ArrayList<>();

        words.addAll(Arrays.asList(frase.get(frase.size()-1).split(" ")));
        

        int lastIndex = words.size() - 1;
        for (int i = 0; i < words.size(); ++i) {
            words.add(0, words.remove(lastIndex));
            result.add(arrayToString(words));
        }
    
    
        for (String shift : result) {
            shifts.insert(shift);
        }
    }

    private String arrayToString(List<String> fraseList) {
        StringBuilder fraseNueva = new StringBuilder();
        for (String node : fraseList) {
            fraseNueva.append(node);
            fraseNueva.append(" ");
        }
        fraseNueva.deleteCharAt(fraseNueva.length() - 1);
        return fraseNueva.toString();
    }
}
