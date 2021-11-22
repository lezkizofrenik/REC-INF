import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;

public class ejercicio20 {
    public static void main(String[] args) throws IOException {
        String cadena="", linea;
        FileReader f = new FileReader("./material/ejercicio19.txt");
        BufferedReader b = new BufferedReader(f);
        while((linea = b.readLine())!=null) {
            cadena+=linea + "\n";
        }
        b.close();

        Pattern pat = Pattern.compile("(\\s{2})");
        Matcher mat = pat.matcher(cadena);
        //cadena = mat.replaceAll(" ");
        //Con replaceall no funciona bien lol
        while(mat.find()){
            System.out.println(mat.group(1).toUpperCase());
            cadena = mat.replaceFirst(" ");
            mat = pat.matcher(cadena);
        }
        try {
            String ruta = "./material/ejercicio20.txt";
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

/*Elimina   los   símbolos   (:,.;?¿¡!...”’<<>>)   
del   texto   que   aparece   en   el   fichero “EjercicioExpresiones.txt”*/