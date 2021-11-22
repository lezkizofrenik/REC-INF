public class ejercicio11 {
    public static void main(String[] args){
        Comprobar c = new Comprobar("\\+34\\s?[98765]([0-9]\\s?){8}");
        c.comprobar("+34 95 6030466");
        c.comprobar("+34 956030466");
        c.comprobar("+34956030466");
        c.comprobar("+34 956 03 04 66");
        c.comprobar("+34956 03 04 66");
        c.comprobar("+65 95 6030466");
        c.comprobar("+34 95 603042342342366");
        c.comprobar("+34 126030466");







    }
}

/*¿Qué  expresión  regular  utilizarías  para  comprobar
si  un  número  de  teléfono  fijo  es español? Ten en 
cuenta el siguiente ejemplo para realizar el patrón: 
+34 95 6030466
*/

//\+34\s?[98765](([0-9]\s?[0-9]{7})|(([0-9]{2})\s?){4})

