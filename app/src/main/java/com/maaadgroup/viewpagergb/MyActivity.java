package com.maaadgroup.viewpagergb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

public class MyActivity extends Activity {

    private ListView mListView;
    private EffectAdapter mAdapter;
    private View mTarget;
    private int durasi = 1000;
    private int durasi_a = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setResource("activity_my","layout"));
        final SharedPreferences setTema = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String setTema_str = setTema.getString("gantiTema", "None");
        setTheme(setResource(setTema_str, "style"));
        mListView = (ListView)findViewById(setResource("list_items","id"));
        mTarget = findViewById(setResource("id_maaadgroup","id"));
        mAdapter = new EffectAdapter(this);
        mListView.setAdapter(mAdapter);
        rope = YoYo.with(Techniques.FadeIn).duration(durasi).playOn(mTarget);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Techniques technique = (Techniques)view.getTag();
                rope =  YoYo.with(technique)
                        .duration(durasi_a)
                        .interpolate(new AccelerateDecelerateInterpolator())
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {

                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                                Toast.makeText(MyActivity.this, "canceled", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .playOn(mTarget);
            }
        });
        findViewById(setResource("id_maaadgroup","id")).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rope != null) {
                    rope.stop(true);
                }
            }
        });
    }
    private YoYo.YoYoString rope;

    public class EffectAdapter extends BaseAdapter {

        private Context mContext;

        public EffectAdapter(Context context){
            mContext = context;
        }

        @Override
        public int getCount() {
            return Techniques.values().length;
        }

        @Override
        public Object getItem(int position) {
            return Techniques.values()[position].getAnimator();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(mContext).inflate(setResource("item","layout"),null,false);
            TextView t = (TextView)v.findViewById(setResource("list_item_text","id"));
            Object o = getItem(position);
            int start = o.getClass().getName().lastIndexOf(".") + 1;
            String name = o.getClass().getName().substring(start);
            t.setText(name);
            v.setTag(Techniques.values()[position]);
            return v;
        }
    }

    public int setResource(String name, String Type)
    {
        return getBaseContext().getResources().getIdentifier(name, Type, getBaseContext().getPackageName());
    }

}
