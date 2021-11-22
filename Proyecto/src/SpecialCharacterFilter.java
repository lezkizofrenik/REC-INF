import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharacterFilter extends Filtros {
    String reemplazo;
    Pattern p;
    public SpecialCharacterFilter(String patron, String reemplazo){
        this.reemplazo = reemplazo;
        p = Pattern.compile(patron);
    }

    public String execute(String text){
        Matcher m = p.matcher(text);
        return m.replaceAll(reemplazo);
    }
}
