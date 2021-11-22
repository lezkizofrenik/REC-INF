import src.org.tartarus.snowball.SnowballStemmer;
import src.org.tartarus.snowball.ext.porterStemmer;

public class Stemmer{
    public static String execute(String rawString) {
        SnowballStemmer stemmer = new porterStemmer();
        String stemmedString;
                
        stemmer.setCurrent(rawString);
        stemmer.stem();
        stemmedString = stemmer.getCurrent();
        return stemmedString;
    }
}
