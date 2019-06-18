package ca.jeonghoon.day5cameraandmenus;

import android.content.Intent;
import android.graphics.Camera;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Menus
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu intentSubmenu = menu.addSubMenu("Intent options");

        intentSubmenu.add("Open Browser").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse("http://www.google.com"));

                startActivity(intent);
                return false;
            }
        });

        intentSubmenu.add("open sms screen").setOnMenuItemClickListener((menuItem) -> {
           Intent intent = new Intent(Intent.ACTION_VIEW);

           intent.setData(Uri.parse("sms:1234567890"));
           intent.putExtra("sms_body", "Test sms");

           startActivity(intent);
           return false;
        });

        intentSubmenu.add("open dialer").setOnMenuItemClickListener((menuItem) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.setData(Uri.parse("tel:1234567890"));
            startActivity(intent);
            return false;
        });

        // camera
        intentSubmenu.add("open camera").setOnMenuItemClickListener(menuItem -> {
            Intent intent = new Intent(MainActivity.this, CapturePhotoActivity.class);
            startActivity(intent);
            return false;
        });

        return super.onCreateOptionsMenu(menu);
    }
}
