package ca.jeonghoon.day5cameraandmenus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CapturePhotoActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA = 1;

    ImageView cameraImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_photo);

        initialize();
    }

    private void initialize() {
        cameraImage = findViewById(R.id.cameraImageView);

        findViewById(R.id.btnCaptureImage).setOnClickListener(view -> captureImage(view));
    }

    public void captureImage(View view) {
        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (photoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(photoIntent, REQUEST_CODE_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            ImageView imageViewCamera = findViewById(R.id.cameraImageView);

            imageViewCamera.setImageBitmap(bitmap);
        }
    }
}
