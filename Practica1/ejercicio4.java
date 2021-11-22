public class ejercicio4 {
    public static void main(String[] args){
        Comprobar c = new Comprobar(".*[^0-9]$");
        c.comprobar("hola");
        c.comprobar("hola9");
        c.comprobar("hola (espacio) ");
        c.comprobar("hola%");



    }
}
