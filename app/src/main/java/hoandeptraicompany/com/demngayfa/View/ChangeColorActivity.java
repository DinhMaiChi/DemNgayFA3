package hoandeptraicompany.com.demngayfa.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import de.hdodenhof.circleimageview.CircleImageView;
import hoandeptraicompany.com.demngayfa.R;

public class ChangeColorActivity extends AppCompatActivity implements View.OnClickListener {
    private CircleImageView changeCyan;
    private CircleImageView changeDoNhat;
    private CircleImageView changeXanhNuocBienDam;
    private CircleImageView changeDaCamDam;
    private CircleImageView changeXanhNuocBienNhat;
    private CircleImageView changeDoDam;
    private CircleImageView changeVang;
    private CircleImageView changeXanhDaTroi;
    private CircleImageView changeNau;
    private CircleImageView changeXanhLaCay;
    private CircleImageView changeTim;
    private CircleImageView changeHong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change_color);
        initComps();
        addEvent();


    }

    private void addEvent() {
        changeCyan.setOnClickListener(this);
        changeDoNhat.setOnClickListener(this);
        changeXanhNuocBienDam.setOnClickListener(this);
        changeDaCamDam.setOnClickListener(this);
        changeXanhNuocBienNhat.setOnClickListener(this);
        changeHong.setOnClickListener(this);
        changeDoDam.setOnClickListener(this);
        changeVang.setOnClickListener(this);
        changeXanhDaTroi.setOnClickListener(this);
        changeNau.setOnClickListener(this);
        changeXanhLaCay.setOnClickListener(this);
        changeTim.setOnClickListener(this);
    }

    private void initComps() {
        changeCyan = (CircleImageView) findViewById(R.id.changeCyan);
        changeDoNhat = (CircleImageView) findViewById(R.id.changeDoNhat);
        changeXanhNuocBienDam = (CircleImageView) findViewById(R.id.changeXanhNuocBienDam);
        changeDaCamDam = (CircleImageView) findViewById(R.id.changeDaCamDam);
        changeXanhNuocBienNhat = (CircleImageView) findViewById(R.id.changeXanhNuocBienNhat);
        changeDoDam = (CircleImageView) findViewById(R.id.changeDoDam);
        changeVang = (CircleImageView) findViewById(R.id.changeVang);
        changeXanhDaTroi = (CircleImageView) findViewById(R.id.changeXanhDaTroi);
        changeNau = (CircleImageView) findViewById(R.id.changeNau);
        changeXanhLaCay = (CircleImageView) findViewById(R.id.changeXanhLaCay);
        changeTim = (CircleImageView) findViewById(R.id.changeTim);
        changeHong = (CircleImageView) findViewById(R.id.changeHong);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeCyan:
                requestMainActivity("#3ABEC0", "#3c3ABEC0");

                break;
            case R.id.changeDoNhat:
                requestMainActivity("#FF5151", "#3cFF5151");
                break;
            case R.id.changeXanhNuocBienDam:
                requestMainActivity("#227CD2", "#3c227CD2");
                break;
            case R.id.changeDaCamDam:
                requestMainActivity("#FF9700", "#3cFF9700");
                break;
            case R.id.changeXanhNuocBienNhat:
                requestMainActivity("#3DA0D7", "#3c3DA0D7");
                break;
            case R.id.changeDoDam:
                requestMainActivity("#F5171A", "#3cF5171A");
                break;
            case R.id.changeVang:
                requestMainActivity("#EAC248", "#3cEAC248");
                break;
            case R.id.changeXanhDaTroi:
                requestMainActivity("#00E5FF", "#3cEAC248");
                break;
            case R.id.changeNau:
                requestMainActivity("#560002", "#3c560002");
                break;
            case R.id.changeXanhLaCay:
                requestMainActivity("#4BB050", "#3c4BB050");
                break;
            case R.id.changeTim:
                requestMainActivity("#A44999", "#3cA44999");
                break;
            case R.id.changeHong:
                requestMainActivity("#FF4081", "#3cFF4081");
                break;
            default:
                break;

        }

    }

    public void requestMainActivity(String codeColor, String codeShadow) {
        Intent intent = new Intent();
        intent.putExtra("codeColor", codeColor);
        intent.putExtra("shadowColor", codeShadow);
        setResult(MainActivity.RESULT_CHANGECOLOR, intent);
        finish();


    }
}
