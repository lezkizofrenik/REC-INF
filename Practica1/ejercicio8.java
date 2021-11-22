public class ejercicio8 {
    public static void main(String[] args){
        //He tenido que poner el primer grupo (.*) porque por algún motivo se
        //ha rayado, pero originalmente tenía puesto ^
        Comprobar c = new Comprobar("(.*)w{3}[.][A-Za-z0-9]+[.]es.*");
        c.comprobar("wwww.google.es");
        c.comprobar("wwww.google.com");
        c.comprobar("google.es");
        c.comprobar("google");
        c.comprobar("wwww.google");
        c.comprobar("wwww.google.es/");





    }
}
