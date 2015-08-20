package com.maaadgroup.viewpagergb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suyono on 8/17/2015.
 * Copyright (c) 2015 by Suyono (ion).
 * All rights reserved.
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and decompilation.
 */
@SuppressWarnings("ALL")
public class EasyViewpagerUI extends ViewPager {

    private int NUM_VIEWS = 3;
    private int id_a = setResource("id_EasyViewpagerUI","id");
    private int id_b = setResource("id_Halaman_1","id");
    private int id_c = setResource("id_Halaman_2","id");
    private int id_d = setResource("id_Halaman_3","id");
    private int durasi = 1000;

    public int setResource(String name, String Type)
    {
        return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
    }

    public EasyViewpagerUI (Context context) {
        super(context);

    }

    public EasyViewpagerUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
          Kode_Utama () ;
        }
    }

    private void Kode_Utama() {
        SharedPreferences gantiAnimasi_pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String gantiAnim_str = gantiAnimasi_pref.getString("gantiANIM", "Flash");

        SharedPreferences setJudulSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String isikanJudul1 = setJudulSharedPreferences.getString("setJudul1", "Page 1");
        final String isikanJudul2 = setJudulSharedPreferences.getString("setJudul2", "Page 2");
        String isikanJudul3 = setJudulSharedPreferences.getString("setJudul3", "Page 3");

        final SharedPreferences setCountJumlah = PreferenceManager.getDefaultSharedPreferences(getContext());
        String tampilCount = setCountJumlah.getString("getCount", "3");

        List<String> Isi_Judul_halaman_EasyViewpagerUI = new ArrayList<>();
        Isi_Judul_halaman_EasyViewpagerUI.add(isikanJudul1);
        Isi_Judul_halaman_EasyViewpagerUI.add(isikanJudul2);
        Isi_Judul_halaman_EasyViewpagerUI.add(isikanJudul3);

        final Adapter_EasyViewpagerUI adapter_easyViewpagerUI = new Adapter_EasyViewpagerUI(Isi_Judul_halaman_EasyViewpagerUI);
        ViewPager viewPager = (ViewPager) findViewById(id_a);
        viewPager.setAdapter(adapter_easyViewpagerUI);

        int myNumCount = 0;
        try {
            myNumCount = Integer.parseInt(tampilCount);
        } catch (NumberFormatException nfe) {
            TampilPemberitahuan(getContext(), "Pemberitahuan", "Oppps...!! Sepertinya string ini Tidak bisa diubah jadi int...!! " + nfe);
        }
        setN(myNumCount);
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(NUM_VIEWS);
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                View page1 = findViewById(id_b);
                View page2 = findViewById(id_c);
                View page3 = findViewById(id_d);
                switch (position) {
                    case 0:
                        YoYo.with(Techniques.valueOf(gantiAnim_str))
                                .duration(durasi)
                                .playOn(page1);
                    case 1:
                        YoYo.with(Techniques.valueOf(gantiAnim_str))
                                .duration(durasi)
                                .playOn(page2);
                    case 2:
                        YoYo.with(Techniques.valueOf(gantiAnim_str))
                                .duration(durasi)
                                .playOn(page3);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        adapter_easyViewpagerUI.notifyDataSetChanged();
    }

    private void TampilPemberitahuan(Context ctx, String title, String message) {

        AlertDialog.Builder b = new AlertDialog.Builder(ctx);
        b.setMessage(message);
        b.setCancelable(false);
        b.setNeutralButton("Coba lagi", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.setTitle(title);
        AlertDialog ad = b.create();
        ad.show();

    }

    public void setN(int N) {
        this.NUM_VIEWS = N;
    }

    public class Adapter_EasyViewpagerUI extends PagerAdapter {

        private List<String> Adapter_Judul_Halaman_EasyViewpagerUI;

        public Adapter_EasyViewpagerUI(List<String> Adapter_Judul_Halaman_EasyViewpagerUI)
        {
            this.Adapter_Judul_Halaman_EasyViewpagerUI = Adapter_Judul_Halaman_EasyViewpagerUI;
        }

        @Override
        public int getCount() {
            return NUM_VIEWS;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }
        @Override
        public CharSequence getPageTitle ( int position){
            return Adapter_Judul_Halaman_EasyViewpagerUI.get(position);
        }

        @Override
        public Parcelable saveState () {
            return null;
        }

        @Override
        public Object instantiateItem (ViewGroup container,int position) {
            int id_Halaman = 0;
            switch (position) {
                case 0:
                    id_Halaman = id_b;
                    break;
                case 1:
                    id_Halaman = id_c;
                    break;
                case 2:
                    id_Halaman = id_d;
                    break;
            }
            return findViewById(id_Halaman);
        }
    }
}
