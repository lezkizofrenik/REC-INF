public class ejercicio6 {
    public static void main(String[] args){
        Comprobar c = new Comprobar(".*2[^6].*");
        c.comprobar("aaa2ddd");
        c.comprobar("aa26ccc");
    }
}
