public class ejercicio7 {
    public static void main(String[] args){
        Comprobar c = new Comprobar("([A-Za-z]\\s?){5,10}");
        c.comprobar("abcde");
        c.comprobar("ABCDE");
        c.comprobar("AbCdE");
        c.comprobar("abcdefghijklm");
        c.comprobar("aDfCsWevdSdF");
        c.comprobar("Es pa ci os");
    }
}
//No sé si debo admitir los espacios?
//Con el punto admito que haya cualquier cosa en medio