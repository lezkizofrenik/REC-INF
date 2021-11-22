import java.util.Scanner;
import java.util.regex.*;
public class Comprobar {

    private static String p;

    public Comprobar(String p){
        Comprobar.p = p;
    }

    public void comprobar(){    
        System.out.println("Introduzca una cadena");
        Scanner sc = new Scanner(System.in);
        String cadena = sc.nextLine (); 
        sc.close();
        Pattern pat = Pattern.compile(p);
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
            System.out.println("SI");
        } 
        else {
            System.out.println("NO");
        }
    }

    public void comprobar(String cadena){    
        System.out.println("Comprobando: \"" + cadena + "\"...");
        Pattern pat = Pattern.compile(p);
        Matcher mat = pat.matcher(cadena);
        if(mat.matches()) {
            System.out.println("SI");
        } 
        else {
            System.out.println("NO");
        }
    }
}

/*
public static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
    String cadena;
    FileReader f = new FileReader(archivo);
    BufferedReader b = new BufferedReader(f);
    while((cadena = b.readLine())!=null) {
        System.out.println(cadena);
    }
    b.close();
}

public static void main(String[] args) throws IOException {
    muestraContenido("/home/mario/archivo.txt");
}
*/