package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    ObservableList<ToDoItem> items = FXCollections.observableArrayList();

    @FXML
    TextField textField;
    @FXML
    ListView listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(items);
    }

    public void addItem() {
        ToDoItem item = new ToDoItem(textField.getText(), false);
        items.add(item);
        textField.setText("");
    }

    public void toggleItem() {
        ToDoItem item = (ToDoItem) listView.getSelectionModel().getSelectedItem();
        if (item != null) {
            item.isDone = !item.isDone;

            // force listView to refresh
            listView.setItems(null);
            listView.setItems(items);
        }
    }
}
