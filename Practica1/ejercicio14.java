import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

public class ejercicio14 {
    public static void main(String[] args) throws IOException {
       

        String cadena="", linea;
        FileReader f = new FileReader("./uca.html");
        BufferedReader b = new BufferedReader(f);
        while((linea = b.readLine())!=null) {
            cadena+=linea + "\n";
        }
        b.close();
        
        Pattern pat = Pattern.compile("<img.*src=\"(.*)\">");
        Matcher mat = pat.matcher(cadena);
    
        System.out.println("Imágenes encontradas:"); 
        while (mat.find()) { 
            
            System.out.println(mat.group(1));
            
        }         
    } 
    
       
    
}
