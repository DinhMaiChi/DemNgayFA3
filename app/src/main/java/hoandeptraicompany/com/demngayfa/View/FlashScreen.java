package hoandeptraicompany.com.demngayfa.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import hoandeptraicompany.com.demngayfa.R;

public class FlashScreen extends AppCompatActivity {


    private static final int REQUEST_WRITE_EXTERNAL_STORAGE_IMAGE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] permissionRequest = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissionRequest, REQUEST_WRITE_EXTERNAL_STORAGE_IMAGE);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(FlashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 2500);

    }
}
