import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zach on 10/19/15.
 */
public class People {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Person> people = new ArrayList();

        Scanner scanner = new Scanner(new File("people.csv"));
        scanner.nextLine();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] columns = line.split(",");
            Person person = new Person(Integer.valueOf(columns[0]), columns[1], columns[2], columns[3], columns[4], columns[5]);
            people.add(person);
        }

        // write Spark route here
    }
}
