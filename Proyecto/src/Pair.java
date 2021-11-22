import java.util.HashMap;

public class Pair {
    double IDF;
    HashMap<String, Double> docpeso;

    public Pair(double IDF, HashMap<String, Double> docpeso){
        this.IDF = IDF;
        this.docpeso = docpeso;
    }

    public Pair(){
        IDF = 0;
        docpeso = new HashMap<String, Double>();
    }

    public Pair(String key, Double value){
        IDF = 0;
        docpeso = new HashMap<String, Double>();
        docpeso.put(key, value);
    }

    public Pair(Double IDF){
        this.IDF = IDF;
        docpeso = new HashMap<String, Double>();

    }

    double getIDF(){
        return IDF;
    }

    void setIDF(double IDF){
        this.IDF =IDF;
    }

    Double getPeso(String doc){
        if(docpeso.containsKey(doc)) return docpeso.get(doc);
        else return -1.0;
    }
    
    HashMap<String, Double> getDocPeso(){
        return docpeso;
    }

    void add(String key, Double value){
        docpeso.put(key, value);
    }

    int getSize(){
        return docpeso.size();
    }
}
