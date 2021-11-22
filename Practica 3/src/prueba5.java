
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tika.Tika;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.ToXMLContentHandler;

public class prueba5 {

    static Scanner sc = new Scanner(System.in);;
    static Matcher mat, mat2;
    static Pattern pat3 = Pattern.compile("href=\"\\/wiki\\/.*:.*\""); // Para limpiar enlaces que no sirven
    static Pattern pat2 = Pattern.compile("href=\"(\\/wiki\\/([A-Za-z0-9\\_\\-\\.\\(\\)ñáéíóúüÑÁÉÍÓÚÜ]+))"); // Enlaces
                                                                                                             // que
                                                                                                             // queremos
    static Pattern pat = Pattern
            .compile("https:\\/\\/es\\.wikipedia\\.org\\/wiki\\/([A-Za-z0-9\\_\\-\\.\\(\\)ñáéíóúüÑÁÉÍÓÚÜ]+)"); // Semilla
    static int download_cont = 0;

    static ArrayList<String> urls = new ArrayList<String>(); // O(n)
    static Set<String> visitados = new HashSet<>(); // O(1)

    static String[] reemplazaroriginal = { "á", "é", "í", "ó", "ú", "Á", "É", "Í", "Ó", "Ú", "ñ", "Ñ", "ü", "Ü" };

    static String[] reemplazar = { "%C3%A1", "%C3%A9", "%C3%AD", "%C3%B3", "%C3%BA", "%C3%81", "%C3%89", "%C3%8D",
            "%C3%93", "%C3%9A", "%C3%B1", "%C3%91", "%C3%BC", "%C3%9C" };

    public static void escribirFichero(File file, String content) {
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String HTMLCode(URL url) {
        String r = "";
        try {
            InputStream input = TikaInputStream.get(url);
            ToXMLContentHandler handler = new ToXMLContentHandler();
            Metadata metadata = new Metadata();
            HtmlParser parser = new HtmlParser();
            parser.parse(input, handler, metadata);
            input.close();
            r = handler.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;

    }

    public static int HttpResponse(URL url) {
        int r = -1;
        try {
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            huc.connect();
            r = huc.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public static String HTML_filters(String html) {

        for (int i = 0; i < reemplazar.length; i++) {
            Pattern pat4 = Pattern.compile(reemplazar[i]);
            Matcher mat4 = pat4.matcher(html);
            html = mat4.replaceAll(reemplazaroriginal[i]);
        }

        Matcher mat3 = pat3.matcher(html);
        html = mat3.replaceAll(" ");

        return html;

    }

    public static void fill_URL_array(String html) {
        Matcher mat2 = pat2.matcher(html);
        while (mat2.find()) {
            String visited = "https://es.wikipedia.org" + mat2.group(1);
            if (!visitados.contains(visited) && !urls.contains(visited))
                urls.add(visited);
        }
    }

    public static void nucleo() {
        try {
            String first = urls.get(0);
            mat = pat.matcher(first);
            if (mat.matches()) {
                String title = mat.group(1);
                download_cont++;
                System.out.println(download_cont + " DESCARGANDO........ " + title);

                File file = new File("/home/arachnida/Documentos/RECINF/Practica 3/Archivos/" + title + ".doc");
                URL url = new URL(first);
                int code = HttpResponse(url);

                if (!file.exists() && !visitados.contains(first) && code != 404/* &&mat.matches() */) {
                    visitados.add(first);

                    String html = HTMLCode(url);
                    String content = new Tika().parseToString(url);

                    escribirFichero(file, content);
                    html = HTML_filters(html);
                    fill_URL_array(html);

                } else {
                    if (!file.exists())
                        System.out.println("ERROR: EL ARCHIVO " + first + " YA EXISTE");
                    else if (!visitados.contains(first))
                        System.out.println("ERROR: " + first + " YA HA SIDO VISITADO");
                    else if (code != 404)
                        System.out.println(
                                "ERROR: LA ENTRADA DE WIKIPEDIA " + first + " NO EXISTE O AÚN NO HA SIDO ESCRITA");
                }
                urls.remove(0);

            } else
                System.out.println("ERROR DE MATCH: " + first);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Introduzca una URL de Wikipedia:");
        String page;
        boolean match = false;
        do {
            page = sc.nextLine();
            mat = pat.matcher(page);
            int i = 30000;
            if (mat.matches()) {
                if (HttpResponse(new URL(page)) != 404) {
                    urls.add(page);
                    match = true;
                    sc.close();
                    mat = pat.matcher(page);

                    while (i > 0 && urls.size() > 0) {
                        nucleo();
                        i--;
                    }
                } else {
                    System.out
                            .println("ERROR: LA ENTRADA DE WIKIPEDIA " + page + " NO EXISTE O AÚN NO HA SIDO ESCRITA");
                    System.out.println("Introduzca un nuevo link:");
                }

            } else {
                match = false;
                System.out.println("El link es incorrecto. Inténtelo de nuevo.");
            }

        } while (!match);

    }
}
