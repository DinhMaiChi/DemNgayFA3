package hoandeptraicompany.com.demngayfa.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import hoandeptraicompany.com.demngayfa.R;

public class ChangeBottomTitleActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCancel;
    private Button btnOK;
    private EditText edTitleBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bottom_title);
        initComps();
        addEvent();

    }

    private void addEvent() {
        btnCancel.setOnClickListener(this);
        btnOK.setOnClickListener(this);


    }

    private void initComps() {
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnOK = (Button) findViewById(R.id.btnOK);
        edTitleBottom = (EditText) findViewById(R.id.edBottomTitle);

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
            default:
                break;
        }

    }

    private void requestMainActivity() {
        String newTitle = edTitleBottom.getText().toString();
        if (newTitle.equals("")) {
            finish();
        } else {
            Intent intent = new Intent();
            intent.putExtra("newTitleBottom", newTitle);
            setResult(MainActivity.RESULT_CHANGEBOTTOMTITLE, intent);
            finish();
        }
    }
}
