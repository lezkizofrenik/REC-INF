import java.util.Scanner;
import java.util.regex.*;

public class ejercicio9 {
    public static boolean comprobar(String p, String cadena) {
        Pattern pat = Pattern.compile(p);
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static void comprobarTodo(String cadena) {
        System.out.println("Comprobando: \"" + cadena + "\"...");
        boolean aux = comprobar("((3[01])|((0?[1-9]|[12][0-9])))\\/((0?[13578])|1[02])\\/[0-9]?[0-9]", cadena)|| 
        comprobar("(30)|((0?[1-9]|[12][0-9]))\\/((0?[4569])|11)\\/[0-9]?[0-9]", cadena) || 
        comprobar("((0?[1-9]|1[0-9]|2[0-8])\\/(0?2)\\/[0-9]?[0-9])", cadena) || 
        comprobar("29\\/(0?2)\\/(([02468]?[048])|([1369][26]))", cadena);
        if (aux) {
            System.out.println("SI");
        }
        else System.out.println("NO");

    }

    public static void main(String[] args) {
        /*System.out.println("Introduzca una cadena");
        Scanner sc = new Scanner(System.in);
        String cadena = sc.nextLine();
        sc.close();
        if (comprobarTodo(cadena)) {
            System.out.println("SI");
        }
        else System.out.println("NO");*/
        System.out.println("Fechas con formato incorrecto:");
        comprobarTodo("01/071/00");
        comprobarTodo("1/071/00");
        comprobarTodo("01/07/0000");
        comprobarTodo("001/07/00");
        System.out.println("Fechas que no existen: ");
        comprobarTodo("00/07/00");
        comprobarTodo("29/02/01");
        comprobarTodo("31/2/00");
        comprobarTodo("31/04/00");
        System.out.println("Fechas válidas:");
        comprobarTodo("29/02/12");
        comprobarTodo("13/07/40");
        comprobarTodo("01/7/00");
        comprobarTodo("1/07/00");











        
    }
}

//basico original: [1-9][0-9]?[\\\\/]([1-9]|([1][0-2]))[\\\\/][0-9]{2}
/*Comprobar si una cadena es una fecha dd/mm/yy. 
Comprueba que tu patrón coincida con las siguientes fechas: 
25/10/83, 4/11/56, 30/6/71 y 4/3/85


Si el año es uniformemente divisible por 4, vaya al paso 2. De lo contrario, vaya al paso 5.
Si el año es uniformemente divisible por 100, vaya al paso 3. De lo contrario, vaya al paso 4.
Si el año es uniformemente divisible por 400, vaya al paso 4. De lo contrario, vaya al paso 5.
El año es un año bisiesto (tiene 366 días).
El año no es un año bisiesto (tiene 365 días).

Si la fecha tiene formato yy quiere decir que se refiere a los
años 20xx. Luego existen años bisiestos para todo 20xx.

meses con 31 dias:
enero, marzo, mayo, julio, agosto, octubre, diciembre

(3[01])|((0|[12])?[1-9]))\/((0?[13578])|1[02])\/[0-9]?[0-9]))

meses con 30 dias:
abril, junio, septiembre, noviembre

(30)|(([(0|[12])?[1-9]))\/((0?[4569])|11)\/[0-9]?[0-9])

febrero:

Años con 28: (((0?|[12])[1-8])\/(0?2)\/[0-9]?[0-9])
Años con 29: (29\/(0?2)\/(([02468]?[048])|([1369][26])
Para determinar los años bisiestos me he basado en la tabla del 4.

si el primer decimal es impar y el segundo es 2 o 6, es multiplo de 4.
Si el primero es par y el segundo 048 es múltiplo de 4.



*/

