package WorkingFiles;

import java.io.*;
import java.util.ArrayList;

public class TextMethods {

    /**
     * Получение ID с блокнота
     */

    public ArrayList getIdOutOfTxtFile() {
        File file = new File(".\\datafiles\\id.txt");
        ArrayList<String> words = new ArrayList<String>();
        try {
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader infile = new BufferedReader(new InputStreamReader(
                    fstream));
            String data = null;
            while ((data = infile.readLine()) != null) {
                for (String word : data.split("\\s+")) {
                    words.add(word);
                }
            }
        } catch (IOException e) {
            // Error
        }
        return words;
    }
}
