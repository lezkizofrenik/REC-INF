import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;

public class ejercicio16 {
    public static void main(String[] args) throws IOException {
        String cadena="", linea;
        FileReader f = new FileReader("./material/EjercicioExpresiones.txt");
        BufferedReader b = new BufferedReader(f);
        while((linea = b.readLine())!=null) {
            cadena+=linea + "\\n";
        }
        b.close();
        // Lo ideal sería usar [\\W] pero detecta la ñ.

        Pattern pat = Pattern.compile("[\\(\\:\\,\\.\\;\\?\\¿\\¡\\!\\.\\”\\\"\\’\\<\\>\\)]");
        Matcher mat = pat.matcher(cadena);
   
        try {
            String ruta = "./material/ejercicio16.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mat.replaceAll(""));
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    
    }
}

/*Elimina   los   símbolos   (:,.;?¿¡!...”’<<>>)   
del   texto   que   aparece   en   el   fichero “EjercicioExpresiones.txt”*/