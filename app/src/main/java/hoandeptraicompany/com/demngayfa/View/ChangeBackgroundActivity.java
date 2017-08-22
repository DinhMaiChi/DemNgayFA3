package hoandeptraicompany.com.demngayfa.View;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import hoandeptraicompany.com.demngayfa.R;

/**
 * Created by SONY VAIO E SERIES on 21/08/2017.
 */

public class ChangeBackgroundActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BackgroundAdapter adapter;
    private int curIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        viewPager = (ViewPager) findViewById(R.id.vp_bg);
        adapter = new BackgroundAdapter(this);
        viewPager.setAdapter(adapter);

    }

}
