import java.util.ArrayList;

public class GestorCaracteres {
    ArrayList<Filtros> cola = new ArrayList<Filtros>();

    public void add (Filtros f){
        cola.add(f);
    }

    public Filtros get(int i){
        return cola.get(i);
    }
    public String execute (String s){
        String procesar = s;
        // quizas necesito eliminar los filtros que ya he aplicado?
        for(Filtros f : cola){
            procesar = f.execute(procesar);
        }
        return procesar;
    }

}
