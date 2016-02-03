import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Countries {
    public static void main(String[] args) {

    }

    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }
}
