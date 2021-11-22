import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;

public class ejercicio19 {
    public static void main(String[] args) throws IOException {
        String cadena="", linea;
        FileReader f = new FileReader("./material/ejercicio18.txt");
        BufferedReader b = new BufferedReader(f);
        while((linea = b.readLine())!=null) {
            cadena+=linea + "\n";
        }
        b.close();

        Pattern pat = Pattern.compile("([a-zñ]+)");
        Matcher mat = pat.matcher(cadena);
        
    
        while(mat.find()){
            System.out.println(mat.group(1).toUpperCase());
            cadena = mat.replaceFirst(mat.group(1).toUpperCase());
            mat = pat.matcher(cadena);
        }
        try {
            String ruta = "./material/ejercicio19.txt";
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
    
    }
}

