Android is a complicated ecosystem with its own idiosyncrasies. We must start by downloading a variant of IntelliJ maintained by Google called [Android Studio](https://developer.android.com/sdk/index.html).

## ToDoAndroid

At the main screen, go to: Start a new Android Studio project. Call it `ToDoAndroid` with `theironyard.com` as the domain. Select "Phone and Tablet" as the form factor. Then select Empty Activity. Click Finish on the last screen of the wizard.

The next step is to set up an emulator. Click the AVD Manager button in the toolbar and click the Create Virutal Device button. After clicking Next, you may need to download a system image. Download the latest one with the x86_64 ABI. Once it is downloaded, select it and click Next. On the last screen, make sure "Use Host GPU" is checked and then click Show Advanced Settings. Set the RAM to 1024 MB (1 GB) because any more than that will disable the Intel HAXM optimization and cause the emulator to boot more slowly.

Launch the emulator and let it run in the background. Android Studio's console should say *HAXM is working and emulator runs in fast virt mode*.

Open activity_main.xml and delete the default TextView. Add a vertical LinearLayout to the root view. Then add a ListView and a horizontal LinearLayout to that. To the last widget, add a Plain Text and a Button. Adjust the ListView to make room for the bottom bar. Select the Button and set its Text property to "Add". Finally, select the Plain Text and under its properties set `layout:width` to `0` and `layout:weight` to `1`.

![](https://raw.githubusercontent.com/oakes/java-assignments/master/curriculum/images/android-1.png)
