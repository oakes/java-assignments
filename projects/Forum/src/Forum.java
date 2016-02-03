import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zach on 10/15/15.
 */
public class Forum {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Post> posts = new ArrayList();

        Scanner fileScanner = new Scanner(new File("posts.txt"));

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            int replyId = Integer.valueOf(columns[0]);
            String author = columns[1];
            String text = columns[2];
            Post post = new Post(replyId, author, text);
            posts.add(post);
        }

        Scanner consoleScanner = new Scanner(System.in);
        int postId = -1;
        while (true) {
            printPosts(posts, postId);
            System.out.println("Enter post id to see replies.");
            postId = Integer.valueOf(consoleScanner.nextLine());
        }
    }

    static void printPosts(ArrayList<Post> posts, int postId) {
        int id = 0;
        for (Post post : posts) {
            if (post.replyId == postId) {
                System.out.println(String.format("[%d] %s (%s)", id, post.text, post.author));
            }
            id++;
        }
    }

    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }
}
