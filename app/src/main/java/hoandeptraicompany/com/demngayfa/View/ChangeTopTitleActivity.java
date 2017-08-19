package hoandeptraicompany.com.demngayfa.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import hoandeptraicompany.com.demngayfa.R;

public class ChangeTopTitleActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnOK;
    private Button btnCancel;
    private EditText edTopTitile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_top_title);
        initComps();
        addEvent();
    }

    private void addEvent() {
        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void initComps() {
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnOK = (Button) findViewById(R.id.btnOK);
        edTopTitile = (EditText) findViewById(R.id.edTopTitle);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnCancel:
                finish();
                break;
            case R.id.btnOK:
                requestMainActivity();
                break;

        }
    }

    private void requestMainActivity() {
        String newTitle = edTopTitile.getText().toString();
        if (newTitle.equals("")) {
            finish();
        } else {
            Intent intent = new Intent();
            intent.putExtra("newTitle", newTitle);
            setResult(MainActivity.RESULT_CHANGETOPTITLE, intent);
            finish();


        }

    }
}
