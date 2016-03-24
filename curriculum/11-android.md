Download and install [Android Studio](https://developer.android.com/sdk/index.html).

## ToDoAndroid

At the main screen, go to: Start a new Android Studio project. Call it `ToDoAndroid` with `theironyard.com` as the domain. Select "Phone and Tablet" as the form factor. Then select Empty Activity. Click Finish on the last screen of the wizard.

The next step is to set up an emulator. Click the AVD Manager button in the toolbar and click the Create Virutal Device button. After clicking Next, you may need to download a system image. Download the latest one with the x86_64 ABI. Once it is downloaded, select it and click Next. On the last screen, make sure "Use Host GPU" is checked and then click Show Advanced Settings. Set the RAM to 1024 MB (1 GB) because any more than that will disable the Intel HAXM optimization and cause the emulator to boot more slowly.

Launch the emulator and let it run in the background. Android Studio's console should say *HAXM is working and emulator runs in fast virt mode*.

Open activity_main.xml and delete the default TextView. Add a vertical LinearLayout to the root view. Then add a ListView and a horizontal LinearLayout to that. To the last widget, add a Plain Text and a Button. Adjust the ListView to make room for the bottom bar. Select the Button and set its Text property to "Add". Finally, select the Plain Text and under its properties set `layout:width` to `0` and `layout:weight` to `1`.

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/assets/android-1.png)

Now that we have the layout, let's open `MainActivity.java` and bring the widgets in:

```java
public class MainActivity extends AppCompatActivity {
    ListView list;
    EditText text;
    Button addButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listView);
        text = (EditText) findViewById(R.id.editText);
        addButton = (Button) findViewById(R.id.button);
    }
}
```

Then create an `ArrayAdapter<String>` to hold the todo items:

```java
public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> items;
    
    ...
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        
        items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(items);
    }
}
```

Finally, implement and add the appropriate listeners for the button click and the list item's long click:

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    ...
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        
        addButton.setOnClickListener(this);
        list.setOnItemLongClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        String item = text.getText().toString();
        items.add(item);
        text.setText("");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String item = items.getItem(position);
        items.remove(item);
        return true;
    }
}
```

## BrowserAndroid

At the main screen, go to: Start a new Android Studio project. Call it `BrowserAndroid` with `theironyard.com` as the domain. Select "Phone and Tablet" as the form factor. Then select Empty Activity. Click Finish on the last screen of the wizard. Launch the previously-created emulator if it isn't already running.

Open activity_main.xml and delete the default TextView. Add a vertical LinearLayout to the root view. Then add a a horizontal LinearLayout and a WebView to that. Then add two Small Button widgets, a Plain Text, and another Small Button to the horizontal LinearLayout. Select the horizontal LinearLayout and set its `layout:height` to `wrap_content`. Select the WebView and set its `layout:width` and `layout:height` to `fill_parent`. Select each button and set their Text property to `<`, `>`, and `Go` respectively. Finally, select the Plain Text and under its properties set `layout:width` to `0` and `layout:weight` to `1`.

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/assets/android-2.png)

Now that we have the layout, let's open `MainActivity.java` and bring the widgets in:

```java
public class MainActivity extends AppCompatActivity {
    EditText addressBar;
    Button backButton;
    Button forwardButton;
    Button goButton;
    WebView webView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addressBar = (EditText) findViewById(R.id.editText);
        backButton = (Button) findViewById(R.id.button);
        forwardButton = (Button) findViewById(R.id.button2);
        goButton = (Button) findViewById(R.id.button3);
        webView = (WebView) findViewById(R.id.webView);
    }
}
```

Then implement and add the appropriate listeners for the buttons:

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        
        backButton.setOnClickListener(this);
        forwardButton.setOnClickListener(this);
        goButton.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        if (v == backButton) {
            webView.goBack();
        }
        else if (v == forwardButton) {
            webView.goForward();
        }
        else if (v == goButton) {
            String address = addressBar.getText().toString();
            if (!address.startsWith("http")) {
                address = "http://" + address;
            }
            webView.loadUrl(address);
        }
    }
}
```

Make sure to add the appropriate permission to AndroidManifest.xml:

```
<uses-permission android:name="android.permission.INTERNET" />
```

Finally, add code to `onCreate` that updates the address bar:

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                addressBar.setText(url);
            }
        });
    }
    
    ...
}
```
