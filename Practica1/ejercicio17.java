import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;

public class ejercicio17 {
    public static void main(String[] args) throws IOException {
        String [] reemplazar = {"á", "é", "í", "ó", "ú"};
        String [] reemplazar2 = {"a", "e", "i", "o", "u"};
        String cadena="", linea;
        FileReader f = new FileReader("./material/ejercicio16.txt");
        BufferedReader b = new BufferedReader(f);
        while((linea = b.readLine())!=null) {
            cadena+=linea + "\\n";
        }
        b.close();
        // Lo ideal sería usar [\\W] pero detecta la ñ.

       
        for(int i =0; i < reemplazar.length; i++) {   
            Pattern pat = Pattern.compile(reemplazar[i]);
            Matcher mat = pat.matcher(cadena);
            cadena = mat.replaceAll(reemplazar2[i]);
        }
    
        try {
            String ruta = "./material/ejercicio17.txt";
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

/*7.Quita  las  tildes del  texto  obtenido  en  
el  ejercicio  anterior;  reemplaza  por  la  letra  no acentuada*/