import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;

public class ejercicio18 {
    public static void main(String[] args) throws IOException {
        String cadena="", linea;
        FileReader f = new FileReader("./material/ejercicio17.txt");
        BufferedReader b = new BufferedReader(f);
        while((linea = b.readLine())!=null) {
            cadena+=linea + "\n";
        }
        b.close();

        Pattern pat = Pattern.compile("(\\W)([0-9]+)(\\W)");
        Matcher mat = pat.matcher(cadena);
        //Podría ahorrarme el espacio para luego no tener
        //Que sustituir, pero bueno es lo que pide
        cadena = mat.replaceAll("$1 $3");
        try {
            String ruta = "./material/ejercicio18.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cadena);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(mat.replaceAll(""));
            
    
    }
}

/*Elimina   los   símbolos   (:,.;?¿¡!...”’<<>>)   
del   texto   que   aparece   en   el   fichero “EjercicioExpresiones.txt”*/