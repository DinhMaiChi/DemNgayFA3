package hoandeptraicompany.com.demngayfa.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import hoandeptraicompany.com.demngayfa.R;

public class FlashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(FlashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 1500);

    }
}
