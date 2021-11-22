public class ejercicio12 {
    public static void main(String[] args){
        Comprobar c = new Comprobar("[A-Z]((\\s[0-9]{2}\\-[0-9]{5}|((\\-|(\\#\\s?))|\\s)\\d{2}(\\-\\d{4}|\\s?\\d{4})))");
        c.comprobar("A 12-34567");
        c.comprobar("A-12-3456");
        c.comprobar("A# 12 3456");
        c.comprobar("A#12-3456");
        c.comprobar("A 123456");

        c.comprobar("A-12-34567");
        c.comprobar("A-12#34567");
        c.comprobar("A 1234567");
        c.comprobar("A#1234567");
        c.comprobar("A-123 4567");





    }
}

/*12.¿Qué  expresión  regular  utilizarías  para  comprobar  el  número  de  pedido  de  
una empresa cuyo ID puede tener los siguientes:
·P nn-nnnnn
•P-nn-nnnn
•P# nn nnnn
•P#nn-nnnn
•P nnnnnn*/
