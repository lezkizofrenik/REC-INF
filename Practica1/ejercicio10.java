public class ejercicio10 {
    public static void main(String[] args){
        Comprobar c = new Comprobar("((2[0-5]{2}|1[0-9]{2}|[0-9]|[1-9][0-9])\\.){3}(2[0-5]{2}|1[0-9]{2}|[0-9]|[1-9][0-9])");
        c.comprobar("192.168.1.1");
        c.comprobar("200.36.127.40");
        c.comprobar("10.128.1.253");
        c.comprobar("0.0.0.0");
        c.comprobar("999.128.1.253");
        c.comprobar("10.355.1.253");
        c.comprobar("10000.128.1.253");






    }
}
