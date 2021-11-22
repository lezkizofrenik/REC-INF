public class ejercicio1 {
    public static void main(String[] args){
        Comprobar c = new Comprobar("^abc.*");
        c.comprobar("abcholaa sdkfjsfd");
        c.comprobar("Abcholaa sdkfjsfd");
        c.comprobar("hoabcla");


    }
}
