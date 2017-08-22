package hoandeptraicompany.com.demngayfa.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.gelitenight.waveview.library.WaveView;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import hoandeptraicompany.com.demngayfa.ObjectClass.ForeverAlone;
import hoandeptraicompany.com.demngayfa.ObjectClass.WaveHelper;
import hoandeptraicompany.com.demngayfa.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CHANGETOPTITLE = 0;
    public static final int RESULT_CHANGETOPTITLE = 1;
    public static final int REQUEST_CHANGEBOTTOMTITLE = 2;
    public static final int RESULT_CHANGEBOTTOMTITLE = 3;
    public static final int REQUEST_CHANGECOLOR = 4;
    public static final int RESULT_CHANGECOLOR = 5;
    public static final int REQUEST_CHANGE_BACKGROUND = 6;
    public static final int RESULT_CHANGE_BACKGROUND = 7;
    private NotificationCompat.Builder builder;
    private TextView txtSencond;
    private TextView txtMinute;
    private TextView txtHour;
    private TextView txtDay;
    private TextView txtWeek;
    private TextView txtMonth;
    private TextView txtYear;
    private LinearLayout lnSetTime;
    //    private int faDay, faMonth, faYear;
//    private int coutFaDay;
    private Date dateFa;
    private Button btnShow, btnMusic;
    private Button btnSetting;
    private TextView txtToptitle;
    private TextView txtBottomTitle;
    private ForeverAlone foreverAlone;
    private WaveView waveView;
    private TextView txtCountDayFA;
    WaveHelper mWaveHelper;
    private int mBorderColor = Color.parseColor("#44FFFFFF");
    private int mBorderWidth = 10;
    private LinearLayout lnMain;
    private MediaPlayer mediaPlayer;

    public static final int STATE_PLAYING = 2;
    public static final int STATE_PAUSE = 3;
    private int state;
    private boolean isPlaying;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComps();
        addEvent();

    }

    private void addEvent() {
        lnSetTime.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnMusic.setOnClickListener(this);

    }

    private void initComps() {
        initForeverAloneClass();
        findView();
        pushNotification();
        coungtingDay();

    }

    private void pushNotification() {

        Uri notiSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.tv_fa, "FA");
        remoteViews.setTextViewText(R.id.tv_number_date, foreverAlone.getCountDayFA() + "");
        remoteViews.setTextViewText(R.id.tv_date, "ngày");
        remoteViews.setImageViewResource(R.id.imv_background, R.drawable.bg1);

        this.builder = new NotificationCompat.Builder(this);
        this.builder.setOngoing(true);
        this.builder.setAutoCancel(false);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("This is a ticker");

        // Sét đặt thời điểm sự kiện xẩy ra.
        // Các thông báo trên Panel được sắp xếp bởi thời gian này.
        builder.setWhen(System.currentTimeMillis() + 10 * 1000);
        builder.setContentTitle("This is title");
        builder.setContentText("This is content text ....");

        // Tạo một Intent
        Intent intent = new Intent(MainActivity.this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // PendingIntent.getActivity(..) sẽ start mới một Activity và trả về
        // đối tượng PendingIntent.
        // Nó cũng tương đương với gọi Context.startActivity(Intent).
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);


        builder.setContentIntent(pendingIntent);
        builder.setContent(remoteViews)
                .setSound(notiSound);
        // Lấy ra dịch vụ thông báo (Một dịch vụ có sẵn của hệ thống).
        /*NotificationManager notificationService =
                (NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);*/

        // Xây dựng thông báo và gửi nó lên hệ thống.

        /*Notification.Builder noti = new Notification.Builder(this);
        noti.setContentTitle("Title")
                .setOngoing(true)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle("this is content")
                .setSmallIcon(R.mipmap.ic_launcher);*/

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = builder.build();
        manager.notify(2, notification);
    }


    private void initForeverAloneClass() {
        SharedPreferences share = getSharedPreferences("appfa", MODE_PRIVATE);
        Calendar cal = Calendar.getInstance();
        int ngay = share.getInt("ngay", cal.get(Calendar.DAY_OF_MONTH));
        int thang = share.getInt("thang", cal.get(Calendar.MONTH));
        int nam = share.getInt("nam", cal.get(Calendar.YEAR));
        foreverAlone = new ForeverAlone(ngay, thang, nam);
        int id = share.getInt("background", R.drawable.bg1);
        lnMain = (LinearLayout) findViewById(R.id.activity_main);
        Log.d("kiemtraid", id + "");
        lnMain.setBackgroundResource(id);
    }

//    private void initDateLove() {
//        faDay = 16;
//        faMonth = 7;
//        faYear = 2017;
//        Calendar cal = Calendar.getInstance();
//        cal.set(faYear, faMonth, faDay);
//        dateFa = cal.getTime();
//
//
//    }

    private void coungtingDay() {
        Timer T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        txtSencond.setText(calendar.get(Calendar.SECOND) + "");
                        txtHour.setText(calendar.get(Calendar.HOUR) + "");
                        txtMinute.setText(calendar.get(Calendar.MINUTE) + "");
                        txtDay.setText(foreverAlone.getCountDayFAKE() + "");
                        txtMonth.setText(foreverAlone.getCountMonthFA() + "");
                        txtWeek.setText(foreverAlone.getCountWeekDayFA() + "");
                        txtYear.setText(foreverAlone.getCountYearFA() + "");
                        txtCountDayFA.setText(foreverAlone.getCountDayFA() + "");

//                        long count = calendar.getTime().getTime() - 15 * 60 * 60 * 1000 - calendar.get(Calendar.MINUTE) * 60 * 1000 - calendar.get(Calendar.SECOND) * 1000 - dateFa.getTime();
//                        DecimalFormat formar = new DecimalFormat("#");
//                        Log.d("ngayfa", count + "");


                    }
                });
            }
        }, 1000, 1000);

    }

    private void findView() {
        lnMain = (LinearLayout) findViewById(R.id.activity_main);
        txtSencond = (TextView) findViewById(R.id.txtSecond);
        txtMinute = (TextView) findViewById(R.id.txtMinute);
        txtHour = (TextView) findViewById(R.id.txtHour);
        txtDay = (TextView) findViewById(R.id.txtDay);
        txtWeek = (TextView) findViewById(R.id.txtWeek);
        txtMonth = (TextView) findViewById(R.id.txtMonth);
        txtYear = (TextView) findViewById(R.id.txtYear);
        lnSetTime = (LinearLayout) findViewById(R.id.lnSetTime);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnShow = (Button) findViewById(R.id.btnShown);
        txtBottomTitle = (TextView) findViewById(R.id.txtBottomTitle);
        txtToptitle = (TextView) findViewById(R.id.txtTopTitle);
        txtCountDayFA = (TextView) findViewById(R.id.txtCountDayFA);
        btnMusic = (Button) findViewById(R.id.btn_music);

        mediaPlayer = MediaPlayer.create(this, R.raw.fa);
        mediaPlayer.start();

        state = STATE_PLAYING;

        waveView = (WaveView) findViewById(R.id.wvFA);

        SharedPreferences sharedPreferences = getSharedPreferences("appfa", MODE_PRIVATE);
        String colorwave = sharedPreferences.getString("colorwave", "#227CD2");
        String colorwaveshadow = sharedPreferences.getString("shadowcolorwave", "#3c227CD2");

        startAnim(colorwave, colorwaveshadow);
    //    startAnim("#227CD2", "#3c227CD2");


    }


    public void startAnim(String color, String shadow) {
        waveView.setBorder(mBorderWidth, mBorderColor);
        float level = 0f;
        int cout = foreverAlone.getCountDayFA();
        if (foreverAlone.getCountDayFA() / 100.0 > 1) {
            level = 1f;
        } else {
            level = foreverAlone.getCountDayFA() / 100.0f ;
        }
        Log.d("kiemtralevel", cout/100 + "-" + cout);
        mWaveHelper = new WaveHelper(waveView, level);
        waveView.setShapeType(WaveView.ShapeType.CIRCLE);
        waveView.setBorder(mBorderWidth, mBorderColor);
        waveView.setWaveColor(
                Color.parseColor(color),
                Color.parseColor(shadow));
        mBorderColor = Color.parseColor(color);
        waveView.setBorder(mBorderWidth, mBorderColor);
        mWaveHelper.start();
    }

//    private void initAninmation(WaveView mWaveView, AnimatorSet mAnimatorSet) {
//        List<Animator> animators = new ArrayList<>();
//
//        // horizontal animation.
//        // wave waves infinitely.
//        ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(
//                mWaveView, "waveShiftRatio", 0f, 1f);
//        waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
//        waveShiftAnim.setDuration(1000);
//        waveShiftAnim.setInterpolator(new LinearInterpolator());
//        animators.add(waveShiftAnim);
//
//        // vertical animation.
//        // water level increases from 0 to center of WaveView
//        ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(
//                mWaveView, "waterLevelRatio", 0f, 0.5f);
//        waterLevelAnim.setDuration(10000);
//        waterLevelAnim.setInterpolator(new DecelerateInterpolator());
//        animators.add(waterLevelAnim);
//
//        // amplitude animation.
//        // wave grows big then grows small, repeatedly
//        ObjectAnimator amplitudeAnim = ObjectAnimator.ofFloat(
//                mWaveView, "amplitudeRatio", 0.0001f, 0.05f);
//        amplitudeAnim.setRepeatCount(ValueAnimator.INFINITE);
//        amplitudeAnim.setRepeatMode(ValueAnimator.REVERSE);
//        amplitudeAnim.setDuration(5000);
//        amplitudeAnim.setInterpolator(new LinearInterpolator());
//        animators.add(amplitudeAnim);
//
//        mAnimatorSet = new AnimatorSet();
//        mAnimatorSet.playTogether(animators);
//
//    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lnSetTime:
                showDialog();

                break;
            case R.id.btnSetting:
                showDialog();
                break;
            case R.id.btnShown:
                View rootView = findViewById(android.R.id.content).getRootView();
                initShareIntent(MainActivity.this, "com.facebook.katana", getScreenShot(rootView));
                break;
            case R.id.btn_music:
                if (state == STATE_PLAYING) {
                    mediaPlayer.pause();
                    btnMusic.setBackgroundResource(R.drawable.play_circle);
                    state = STATE_PAUSE;
                } else {
                    mediaPlayer.start();
                    state = STATE_PLAYING;
                    btnMusic.setBackgroundResource(R.drawable.pause_circle);
                }

                break;

            default:
                break;
        }
    }

    public static void initShareIntent(Context context, String type, Bitmap shareBody) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        shareBody.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), shareBody, "Title", null);
        Uri imageUri = Uri.parse(path);

        boolean found = false;
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("image/jpeg");

        // gets the list of intents that can be loaded.
        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(share, 0);
        if (!resInfo.isEmpty()){
            for (ResolveInfo info : resInfo) {
                if (info.activityInfo.packageName.toLowerCase().contains(type) ||
                        info.activityInfo.name.toLowerCase().contains(type)) {
                    share.putExtra(Intent.EXTRA_STREAM, imageUri);
                    share.setPackage(info.activityInfo.packageName);
                    found = true;
                    break;
                }
            }
            if (!found) {
                Toast.makeText(context, "Ứng dụng này chưa được cài đặt", Toast.LENGTH_SHORT).show();
                return;
            }

            context.startActivity(Intent.createChooser(share, "Share via"));
        }
    }

    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }


    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_setting_dialog);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        TextView txtChangDate = (TextView) dialog.findViewById(R.id.txtChangeDate);
        txtChangDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                showCalendarDialog();

            }
        });
        TextView txtChangeColor = (TextView) dialog.findViewById(R.id.txtChangeColor);
        txtChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangeColorActivity.class);
                startActivityForResult(intent, REQUEST_CHANGECOLOR);
                dialog.cancel();

            }
        });
        TextView txtChangeTopTitle = (TextView) dialog.findViewById(R.id.txtChangeTopTitle);
        txtChangeTopTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangeTopTitleActivity.class);
                startActivityForResult(intent, REQUEST_CHANGETOPTITLE);
                dialog.cancel();
            }
        });
        dialog.show();
        TextView txtChangeBottomTitle = (TextView) dialog.findViewById(R.id.txtChangeBottomTitle);
        txtChangeBottomTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangeBottomTitleActivity.class);
                startActivityForResult(intent, REQUEST_CHANGEBOTTOMTITLE);
                dialog.cancel();

            }
        });

        TextView txtChangeBackground = (TextView) dialog.findViewById(R.id.txtChangeBackground);
        txtChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ChangeBackgroundActivity.class);
                startActivityForResult(i, REQUEST_CHANGE_BACKGROUND);
                dialog.cancel();
            }
        });


    }

    private void showCalendarDialog() {
        DatePickerDialog.OnDateSetListener onDataset = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                faYear = year;
//                faMonth = month;
//                faDay = dayOfMonth;
//                Calendar cal = Calendar.getInstance();
//                cal.set(faYear, faMonth, faDay);
//                dateFa = cal.getTime();
                SharedPreferences share = getSharedPreferences("appfa", MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putInt("ngay", dayOfMonth);
                editor.putInt("thang", month);
                editor.putInt("nam", year);
                editor.commit();
                foreverAlone = new ForeverAlone(dayOfMonth, month, year);
                String colorwave = share.getString("colorwave", "#227CD2");
                String colorwaveshadow = share.getString("shadowcolorwave", "#3c227CD2");
                startAnim(colorwave, colorwaveshadow);


            }
        };

//        DatePickerDialog pic = new DatePickerDialog(MainActivity.this, R.style.DialogTheme, onDataset, faYear, faMonth, faDay);
//        pic.show();
        DatePickerDialog pic = new DatePickerDialog(MainActivity.this, R.style.DialogTheme, onDataset, foreverAlone.getYearFA(), foreverAlone.getMonthFA(), foreverAlone.getDayFA());
        pic.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHANGETOPTITLE) {
            if (resultCode == RESULT_CHANGETOPTITLE) {
                String newTitle = data.getStringExtra("newTitle");
                txtToptitle.setText(newTitle);

            }
        }
        if (requestCode == REQUEST_CHANGEBOTTOMTITLE) {
            if (resultCode == RESULT_CHANGEBOTTOMTITLE) {
                String newTitle = data.getStringExtra("newTitleBottom");
                txtBottomTitle.setText(newTitle);
            }
        }
        if (requestCode == REQUEST_CHANGECOLOR) {
            if (resultCode == RESULT_CHANGECOLOR) {
                String newTitle = data.getStringExtra("codeColor");
                Log.d("kiemtramau", newTitle);
                SharedPreferences share = getSharedPreferences("appfa", MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                String shadowColor = data.getStringExtra("shadowColor");
                editor.putString("colorwave", newTitle);
                editor.putString("shadowcolorwave", shadowColor);
                editor.commit();
                startAnim(newTitle, shadowColor);
            }
        }

        if (requestCode == REQUEST_CHANGE_BACKGROUND) {
            if (resultCode == RESULT_CHANGE_BACKGROUND) {
                int idBackground = data.getIntExtra("123", 0);
//                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//                int idBackground = sharedPreferences.getInt("1", 0);
                Log.d("ID Background", idBackground + "");
                SharedPreferences share = getSharedPreferences("appfa", MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putInt("background", idBackground);
                editor.commit();
                lnMain.setBackgroundResource(idBackground);

            }

            super.onActivityResult(requestCode, resultCode, data);
        }

    }



    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }


}
