public class ejercicio2 {
    public static void main(String[] args){
        Comprobar c = new Comprobar("^(abc|Abc).*");
        c.comprobar("abcholaa sdkfjsfd");
        c.comprobar("Abcholaa sdkfjsfd");
        c.comprobar("hoabcla");
    }
}
