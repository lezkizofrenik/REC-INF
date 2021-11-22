import java.util.Arrays;
import java.util.regex.Pattern;

/*“<a>uno</a><b>dos</b><c>tres</c><d>cuatro</d><e>cinco</e>” 
Extraelos caracteres    escritos    entre    los    <tags></tags>   
utilizando    el   siguiente    patrón: 
<[^>]*>([^<]*)</[^>]*>  
¿es  correcto?  Si  no  es  así,  corrígelo.  Intenta  ahora  utilizar  
el siguiente patrón: 
<.*>(.*)<\/.*>  
¿funciona?  ¿qué diferencia  hay  con  el patrón  anterior?
Finalmente   utiliza   el   siguiente   patrón:   
<.*?>(.*?)<\/.*?>   
¿Obtienes   los   mismos resultados? ¿Qué diferencia hay entre unos y otros?
*/

public class ejercicio15 {
    static String cad = "<a>uno</a><b>dos</b><c>tres</c><d>cuatro</d><e>cinco</e>";
    static String patron1 = "<[^>]*>([^<]*)</[^>]*>";
    static String patron2 = "<.*>(.*)<\\/.*>";
    static String patron3 = "<.*?>(.*?)<\\/.*?>";
    static String patron4 = "<\\/?[a-zA-Z]+>";
    static String[] solucion = { "uno", "dos", "tres", "cuatro", "cinco" };

    public ejercicio15() {
    }

    /*
    private static void compare(String[] cad1, String cad2[]) {
        boolean iguales = false;
        int cont = 0;
        for(String s : cad1) {
            if(!s.isEmpty()){
                if(s == cad2[cont]) iguales = true;
            } 
        }

        if(iguales) System.out.println("Iguales");
        else System.out.println("Distintos");
    }
    */

    private static void print(String[] cad){
        if(cad.length==0) System.out.println("Sin resultados");
        else{
            System.out.println("Encontrado:");
            for(String s : cad) {
                System.out.println(s);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Probando patrón: " + patron1);
        Pattern p = Pattern.compile(patron1);
        String[] items = p.split(cad);
        //compare(items, solucion);
        print(items);

        System.out.println("Probando patrón: " + patron2);
        p = Pattern.compile(patron2);
        items = p.split(cad);
        //compare(items, solucion);
        print(items);


        System.out.println("Probando patrón: " + patron3);
        p = Pattern.compile(patron3);
        items = p.split(cad);
        //compare(items, solucion);
        print(items);


        System.out.println("Probando mi patrón: " + patron4);
        p = Pattern.compile(patron4);
        items = p.split(cad);
        //compare(items, solucion);
        print(items);
        //print(solucion);


    }
}
