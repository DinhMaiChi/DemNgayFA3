package hoandeptraicompany.com.demngayfa.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import hoandeptraicompany.com.demngayfa.ObjectClass.Background;
import hoandeptraicompany.com.demngayfa.R;

/**
 * Created by SONY VAIO E SERIES on 18/08/2017.
 */

public class BackgroundAdapter extends PagerAdapter {

    private List<Background> listBackgroundList;
    private Context mContext;
    private AdapterView.OnItemClickListener onItemClickListener;
    private int idBackground;
    private SharedPreferences sharedPreferences;

    public BackgroundAdapter(Context mContext) {
        this.mContext = mContext;
        initData();
    }

    private void initData() {
        listBackgroundList = new ArrayList<>();
        listBackgroundList.add(new Background(R.drawable.bg1));
        listBackgroundList.add(new Background(R.drawable.bg2));
        listBackgroundList.add(new Background(R.drawable.bg3));
        listBackgroundList.add(new Background(R.drawable.bg4));
        listBackgroundList.add(new Background(R.drawable.bg5));
    }



    @Override
    public int getCount() {
        return listBackgroundList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = View.inflate(mContext, R.layout.item_background, null);

        final ImageView imvBG = (ImageView) itemView.findViewById(R.id.imv_bg);

        imvBG.setImageResource(listBackgroundList.get(position).getId());

        final int pos = position;

        imvBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.putExtra("123", listBackgroundList.get(pos).getId());
//                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.getApplicationContext());
//                sharedPreferences.edit().putInt("1", idBackground).apply();
//                Log.d("ID Background adapter", idBackground + "");
                Activity activity= (Activity) mContext;
                activity.setResult(MainActivity.RESULT_CHANGE_BACKGROUND, i);
                activity.finish();

            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup viewPager, int position, Object object) {
        viewPager.removeView((View) object);
    }

}
