package sample;

/**
 * Created by zach on 10/20/15.
 */
public class ToDoItem {
    String text;
    boolean isDone;

    public ToDoItem(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String done = "";
        if (isDone) {
            done = "(done)";
        }
        return String.format("%s %s", text, done);
    }
}
