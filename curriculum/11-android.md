Android is a complicated ecosystem with its own idiosyncrasies. We must start by downloading a variant of IntelliJ maintained by Google called [Android Studio](https://developer.android.com/sdk/index.html).

## ToDoAndroid

At the main screen, go to: Start a new Android Studio project. Call it `ToDoAndroid` with `theironyard.com` as the domain. Select "Phone and Tablet" as the form factor. Then select Empty Activity. Click Finish on the last screen of the wizard.

The next step is to set up an emulator. Click the AVD Manager button in the toolbar and click the Create Virutal Device button. After clicking Next, you may need to download a system image. Download the latest one with the x86_64 ABI. Once it is downloaded, select it and click Next. On the last screen, make sure "Use Host GPU" is checked and then click Show Advanced Settings. Set the RAM to 1024 MB (1 GB) because any more than that will disable the Intel HAXM optimization and cause the emulator to boot more slowly.
