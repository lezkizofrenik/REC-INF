public class ejercicio3 {
    public static void main(String[] args){
        Comprobar c = new Comprobar("^[^0-9].*");
        c.comprobar("hola");
        c.comprobar("9hola");
        c.comprobar("h0la");


    }
}
