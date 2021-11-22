
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import src.org.tartarus.snowball.SnowballStemmer;
import src.org.tartarus.snowball.ext.porterStemmer;

/**
 *
 * @author mjcobo
 */
public class StemExample {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    SnowballStemmer stemmer = new porterStemmer();
    String rawString, stemmedString;
    
    rawString = "anlyze";
    
    stemmer.setCurrent(rawString);
    stemmer.stem();
    stemmedString = stemmer.getCurrent();
    
    System.out.println(stemmedString);
    
  }
  
}
