package main.maths.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileUtils {

    public String loadAsString( String path ) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(path);
            Reader r = new InputStreamReader(fis, "UTF-8");  //or whatever encoding
            char[] buf = new char[1024];
            int amt = r.read(buf);
            while (amt > 0) {
                sb.append(buf, 0, amt);
                amt = r.read(buf);
            }

        } catch (IOException e) {
            System.err.println("Couldn't finde File at " + path);
        }



        return sb.toString();
    }
}
