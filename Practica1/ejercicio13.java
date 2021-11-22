public class ejercicio13 {
    public static void main(String[] args){
        Comprobar c = new Comprobar("(([a-zA-Z]|\\s)*[\\!\\@1]+([a-zA-Z]|\\s)*)+");
        c.comprobar("vi@gra");
        c.comprobar("v1agra");
        c.comprobar("v1@gra");
        c.comprobar("v!@gr@");
        c.comprobar("c1frado sp@m");
        c.comprobar("viagra");
        c.comprobar("1234");




    }
}

/*Para  evitar  el  spam,  intenta  localizar  posibles  
alteraciones  que  se  utilizan  para saltarse los filtros 
de correo. Por ejemplo, la palabra “viagra” podría encontrarse:
•vi@gra
•v1agra
•v1@gra
•v!@gr@
*/

/* A la izquierda y a la derecha de un carácter @ ! o 1 debe
haber otra letra o un espacio(texto)*/