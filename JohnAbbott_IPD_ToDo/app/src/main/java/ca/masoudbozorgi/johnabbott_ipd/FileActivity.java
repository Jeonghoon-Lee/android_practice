package ca.masoudbozorgi.johnabbott_ipd;

import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import ca.masoudbozorgi.johnabbott_ipd.file.FileManager;

public class FileActivity extends AppCompatActivity implements View.OnClickListener {

    // Permissions we need in AndroidManifest.xml
    // <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    // <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    int REQUEST_CODE = 1;

    EditText editText;
    Button writeStringPrivateExternalStorageBtn,
           readStringPrivateExternalStorageBtn,

           writeStringPublicExternalStorageBtn,
           readStringPublicExternalStorageBtn,

           writeObjectPrivateExternalStorageBtn,
           readObjectPrivateExternalStorageBtn;

    File file_object_private_external_storage,
            file_string_private_external_storage,
            file_string_public_external_storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        initialize();
        storagePermissionRequest();

        // Internal Storage
        // The internal storage should only be used for application data,
        // (preferences files and settings, sound or image media for the app to work).
        // When the user uninstalls your application, these files are removed/deleted.
        // To access files in Internal Storage, you have to root your Android phone.
        // Your app's internal storage directory is specified by your app's package name
        // in a special location of the Android file system

        // External Storage
        // Every Android-compatible device supports a shared "external storage" that you can use to save files.
        // This can be a removable storage media (such as an SD card) or an internal (non-removable) storage ...
        // External storage is often bigger.
        // Storing data on the internal storage may prevent the user to install other applications

        createFileInExternalStoragePrivateDirectory();
        createFileInExternalStoragePublicDirectory();
    }

    private void initialize() {

        editText = findViewById(R.id.editText);

        writeStringPrivateExternalStorageBtn = findViewById(R.id.writeStringPrivateExternalStorageBtn);
        writeStringPrivateExternalStorageBtn.setOnClickListener(this);

        readStringPrivateExternalStorageBtn = findViewById(R.id.readStringPrivateExternalStorageBtn);
        readStringPrivateExternalStorageBtn.setOnClickListener(this);

        writeStringPublicExternalStorageBtn = findViewById(R.id.writeStringPublicExternalStorageBtn);
        writeStringPublicExternalStorageBtn.setOnClickListener(this);

        readStringPublicExternalStorageBtn = findViewById(R.id.readStringPublicExternalStorageBtn);
        readStringPublicExternalStorageBtn.setOnClickListener(this);

        writeObjectPrivateExternalStorageBtn = findViewById(R.id.writeObjectPrivateExternalStorageBtn);
        writeObjectPrivateExternalStorageBtn.setOnClickListener(this);

        readObjectPrivateExternalStorageBtn = findViewById(R.id.readObjectPrivateExternalStorageBtn);
        readObjectPrivateExternalStorageBtn.setOnClickListener(this);
    }

    private void storagePermissionRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"},
                REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE){
            if (grantResults[0]== PackageManager.PERMISSION_DENIED){
                Toast.makeText(this,
                        "Permission Denied",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this,
                        "Permission Granted",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // If you want to save files on external storage that are private to your app and
    // not accessible by the other apps like MediaStore content provider,
    // you can acquire a directory that's used by only your app by calling getExternalFilesDir()
    private void createFileInExternalStoragePrivateDirectory() {


        // On Android 6.0 (API level 23) and lower, other apps can read your internal files
        // if you set the file mode to be world readable.
        // However, the other app must know your app package name and file names.
        // Other apps cannot browse your internal directories and do not have read or write access
        // unless you explicitly set the files to be readable or writable.

        // Remember that getExternalFilesDir() creates a directory that will be
        // deleted when the user uninstalls your app.
        file_object_private_external_storage = FileManager.createFile(getExternalFilesDir("private_file"),
                "file_object_private_external_storage");
        file_string_private_external_storage = FileManager.createFile(getExternalFilesDir("private_file"),
                "file_string_private_external_storage");
    }

    private void createFileInExternalStoragePublicDirectory() {
        // This example DIRECTORY_PICTURES or can be DIRECTORY_MUSIC
        file_string_public_external_storage =
                FileManager.createFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "file_string_public_external_storage");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            // String ------------------------------------------------------------------------------
                // private .......................
            case R.id.writeStringPrivateExternalStorageBtn:
                FileManager.saveByte(file_string_private_external_storage,
                        editText.getText().toString());
                break;
            case R.id.readStringPrivateExternalStorageBtn:
                editText.setText("Read Private External Storage String: " +
                        FileManager.loadByte(file_string_private_external_storage));
                break;

                // public .......................
            case R.id.writeStringPublicExternalStorageBtn:
                FileManager.saveByte(file_string_public_external_storage,
                        editText.getText().toString());
                break;
            case R.id.readStringPublicExternalStorageBtn:
                editText.setText("Read Public External Storage String: " +
                        FileManager.loadByte(file_string_public_external_storage));
                break;
            // -------------------------------------------------------------------------------------



            // Object ------------------------------------------------------------------------------
            case R.id.writeObjectPrivateExternalStorageBtn:
                FileManager.saveObject(file_object_private_external_storage,
                        new StringBuilder(editText.getText().toString()));
                break;
            case R.id.readObjectPrivateExternalStorageBtn:
                editText.setText("Read Private External Storage Object: " +
                        FileManager.loadObject(file_object_private_external_storage).toString());
                break;
            // -------------------------------------------------------------------------------------
        }
    }
}