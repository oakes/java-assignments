/**
 * Created by zach on 10/12/15.
 */
public class ToDoItem {
    String text;
    boolean isDone;

    public ToDoItem(String text) {
        this.text = text;
        this.isDone = false;
    }

    public ToDoItem(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }
}
