//Comprobar si una cadena solo contiene los caracteres ālā o āaā

public class ejercicio5 {
    public static void main(String[] args){
        Comprobar c = new Comprobar("[l|a]+");
        c.comprobar("llll");
        c.comprobar("lalala");
        c.comprobar("aa");
        c.comprobar("adsjfkjdf");

    }
}
