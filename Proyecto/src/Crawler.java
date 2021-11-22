import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.nio.file.Files;

public class Crawler {
    static HashMap<String, Integer> mapaFrecuencias;
    static HashMap<String, Pair> indiceInvertido;
    static HashMap<String, Double> longitudDocumento;
    static HashMap<String, Double> docRecuperados;

    static GestorCaracteres gestorCaracteres = new GestorCaracteres();
    static File carpeta = new File("/home/arachnida/Documentos/RECINF/Proyecto/corpus/");
    static File longitud = new File("/home/arachnida/Documentos/RECINF/Proyecto/src/longitud.txt");
    static File indice = new File("/home/arachnida/Documentos/RECINF/Proyecto/src/indice.txt");

    public static void recuperacion() throws IOException {
        int n = 10;
        String consulta = leer();
        String processedtext = preprocesarCaracteres(consulta);
        ArrayList<String> vTermsProcesados = preprocesarTerminos(processedtext);
        recuperarDocumentos(vTermsProcesados);
        docRecuperados = sortByValue(docRecuperados);
        Iterator<Map.Entry<String, Double>> it = docRecuperados.entrySet().iterator();
        if (docRecuperados.isEmpty())
            System.out.println("No se ha encontrado ningún resultado para su consulta.");
        else {
            while (it.hasNext() && n > 0) {
                Map.Entry<String, Double> pair = it.next();
                System.out.println("Document: " + pair.getKey() + " Weight: " + pair.getValue());
                leerDocumento(pair.getKey());
                it.remove();
                n--;
            }
        }
    }

    public static void leerDocumento(String id) {
        FileReader fr = null;
        BufferedReader br = null;

        String ruta = "/home/arachnida/Documentos/RECINF/Proyecto/corpus/" + id;
        File archivo = new File(ruta);
        int n = 1;
        if (archivo.exists()) {
            try {
                fr = new FileReader(ruta);
                br = new BufferedReader(fr);

                // Lectura del fichero
                String linea;
                while ((linea = br.readLine()) != null && n > 0) {
                    System.out.println(linea);
                    n--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static HashMap<String, Double> sortByValue(HashMap<String, Double> map) {

        LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();

        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        return reverseSortedMap;
    }

    public static void recuperarDocumentos(ArrayList<String> v) {
        docRecuperados = new HashMap<String, Double>();
        for (String t : v) {
            if(indiceInvertido.containsKey(t)){
                HashMap<String, Double> idpeso = indiceInvertido.get(t).getDocPeso();
                double IDF = indiceInvertido.get(t).getIDF();
                for (Map.Entry<String, Double> it : idpeso.entrySet()) {
                    String doc = it.getKey();
                    Double peso = it.getValue();
                    Double pesoDocRec = peso * IDF * IDF;
                    if (docRecuperados.containsKey(doc)) {
                        Double valor = docRecuperados.get(doc);
                        docRecuperados.put(doc, valor + pesoDocRec);
                    } else {
                        docRecuperados.put(doc, pesoDocRec);
                    }
            }
        }

        }
        for (Map.Entry<String, Double> it : docRecuperados.entrySet()) {
            if (!longitudDocumento.containsKey(it.getKey()))
                System.out.println("No tiene " + it.getKey());
            docRecuperados.put(it.getKey(), it.getValue() / longitudDocumento.get(it.getKey()));
        }
    }

    public static void inicializarGestorCaracteres() {
        gestorCaracteres.add(new toLowerCase());
        // gestorCaracteres.add(new SpecialCharacterFilter("[^A-Za-z0-9]", " "));
        gestorCaracteres.add(new SpecialCharacterFilter("[^-\\w]", " "));
        // Eliminar signos de puntuacion
        gestorCaracteres.add(new SpecialCharacterFilter("\\b[0-9]+\\b", " "));
        // Eliminar numeros que no formen parte de una palabra
        gestorCaracteres.add(new SpecialCharacterFilter("-+ | -+", " "));
        // Eliminar los guiones que no formen parte de una palabra
        gestorCaracteres.add(new SpecialCharacterFilter(" +", " "));
        // Eliminar los espacios duplicados

    }

    // la entrada es una secuencia de caracteres
    public static String preprocesarCaracteres(String consulta) {
        return gestorCaracteres.execute(consulta);
    }

    // la entrada es una secuencia (vector) de términos

    public static ArrayList<String> preprocesarTerminos(String processedtext) throws IOException {

        ArrayList<String> vTerms = new ArrayList<>(Arrays.asList(processedtext.split(" ")));
        for (int i = 0; i < vTerms.size(); i++) {
            String term = Stemmer.execute(vTerms.get(i));
            vTerms.set(i, term);
            if (vTerms.get(i).length() < 2 || vTerms.get(i).isEmpty()) {
                vTerms.remove(i);
                i = i - 1;
            } // Umbral

        }

        vTerms = PalabrasVacias.execute(vTerms);

        return vTerms;
    }

    public static String leer() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca su consulta:");
        String consulta = sc.nextLine();

        // sc.close();
        // System.in is same Object (public final static InputStream in; of System
        // class)
        // both method calls are using, closing in one method will automatically close
        // System.in for other method

        return consulta;
    }

    public static void indexado(File folder) throws IOException {
        System.out.println("Nº de ficheros: " + folder.listFiles().length);
        for (final File ficheroEntrada : folder.listFiles()) {
            String text = new String(Files.readAllBytes(Paths.get(ficheroEntrada.getAbsolutePath())));
            String processedtext = preprocesarCaracteres(text);
            ArrayList<String> vTermsProcesados = preprocesarTerminos(processedtext);
            calcularTF_paso1(vTermsProcesados);
            calcularTF_paso2(ficheroEntrada.getName());
        }
        calcularIDF(folder.listFiles().length);

        calcularLongDocumentos();
        escribirFicheroIndice();
        escribirFicheroLongitud();
    }

    public static void escribirFicheroIndice() throws IOException {
        String ruta = "/home/arachnida/Documentos/RECINF/Proyecto/src/indice.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (!archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (String t : indiceInvertido.keySet()) {
                HashMap<String, Double> idpeso = indiceInvertido.get(t).getDocPeso();
                double IDF = indiceInvertido.get(t).getIDF();
                bw.write(t + "/" + IDF + "/");
                for (Map.Entry<String, Double> it : idpeso.entrySet()) {
                    bw.write(it.getKey() + ":" + it.getValue() + ":");
                }
                bw.newLine();
            }
            bw.close();
        }
    }

    public static void escribirFicheroLongitud() throws IOException {
        String ruta = "/home/arachnida/Documentos/RECINF/Proyecto/src/longitud.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (!archivo.exists()) {
            System.out.println("LongitudDocumento: " + longitudDocumento.size());
            bw = new BufferedWriter(new FileWriter(archivo));
            for (Map.Entry<String, Double> it : longitudDocumento.entrySet()) {
                bw.write(it.getKey() + ":" + it.getValue());
                bw.newLine();
            }

            bw.close();
        }
    }

    public static void calcularIDF(int nDocsFolder) {
        for (String t : indiceInvertido.keySet()) {
            int nDocs = indiceInvertido.get(t).getSize();
            double IDF = Math.log((double) nDocsFolder / nDocs) / Math.log(2); /// comprobado
            indiceInvertido.get(t).setIDF(IDF);

            HashMap<String, Double> idpeso = indiceInvertido.get(t).getDocPeso();
            // tfidf
            for (Map.Entry<String, Double> it : idpeso.entrySet()) {
                String key = it.getKey();
                double value = it.getValue();
                double tfidf = value * IDF;
                if (longitudDocumento.containsKey(key)) {
                    // sumatorio de w^2
                    double sumatorio = longitudDocumento.get(key) + tfidf * tfidf;
                    longitudDocumento.put(key, sumatorio);
                } else
                    longitudDocumento.put(key, tfidf * tfidf);
                // almaceno tf-idf en vez de tf
            }

            // crear estructura cuya key sean los documentos y meter ahi el
            // longitudDocumento,
            // haciendo que en cada iteracion calcule la longitud
        }

    }

    public static void calcularLongDocumentos() {
        for (Map.Entry<String, Double> it : longitudDocumento.entrySet()) {
            longitudDocumento.put(it.getKey(), Math.sqrt(it.getValue()));
        }
    }

    public static void calcularTF_paso2(String docID) {
        for (Map.Entry<String, Integer> entry : mapaFrecuencias.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            double tf = 1 + Math.log(value) / Math.log(2);
            if (indiceInvertido.containsKey(key))
                indiceInvertido.get(key).add(docID, tf);
            else {
                Pair pairIDFDocPeso = new Pair(docID, tf); // IDF - <DOC,PESO>
                indiceInvertido.put(key, pairIDFDocPeso);
            }
        }
    }

    // Conteo de elementos
    public static void calcularTF_paso1(ArrayList<String> terminos) {
        mapaFrecuencias = new HashMap<String, Integer>();

        for (String t : terminos) {
            if (mapaFrecuencias.containsKey(t)) {
                Integer f = mapaFrecuencias.get(t);
                f = f + 1;
                mapaFrecuencias.put(t, f);

            } else {
                mapaFrecuencias.put(t, 1);
            }
        }

    }

    public static void volcarIndice() {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(indice);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            //int n =0;
            indiceInvertido = new HashMap<String, Pair>();
            while ((linea = br.readLine()) != null) {
                
                String[] indice = linea.split("/");
                String[] Vidpeso = indice[2].split(":");
                //if(n==41418) System.out.println("Vidpeso empty" + Vidpeso.length);
                Pair idpeso = new Pair(Double.parseDouble(indice[1]));

                for (int i = 0; i + 2 <= Vidpeso.length; i = i + 2) {
                    idpeso.add(Vidpeso[i], Double.parseDouble(Vidpeso[i + 1]));
                }
                indiceInvertido.put(indice[0], idpeso);
                //if(n==41418) System.out.println("idpeso empty" + idpeso.docpeso.isEmpty());
                //n++;

            }
            System.out.println("Volcado indice completado...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void volcarLongitud() {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(longitud);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] idlongitud = linea.split(":");
                longitudDocumento.put(idlongitud[0], Double.parseDouble(idlongitud[1]));

            }
            System.out.println("Volcado longitud completado...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        longitudDocumento = new HashMap<String, Double>();
        indiceInvertido = new HashMap<String, Pair>();
        // Si los ficheros no existen..
        if (!longitud.exists() && !indice.exists()) {
            inicializarGestorCaracteres();
            indexado(carpeta);
        } else {
            volcarIndice();
            volcarLongitud();
        }
        int bucle = 0;
        while (bucle == 0)
            recuperacion();

    }
}
