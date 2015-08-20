package com.maaadgroup.viewpagergb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

@SuppressWarnings("ALL")
public class setEasyViewpagerUI extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener
{
    String title = "Pemberitahuan";
    String message = "Setelah selesai mengatur, diperlukan tindakan re-start SystemUI atau reboot smartphone anda...!! Jika untuk Porting EasyViewPagerUI ke Expanded / Anywhere You Want, Pastikan sudah Root, Deodexed ROM dan terinstall BusyBox. Saya Tidak Bertanggung Jawab atas Kerusakan Smartphone anda jika itu terjadi setelah menerapkan ini di systemui Anda.";

    public int setResource(String name, String Type)
    {
        return getBaseContext().getResources().getIdentifier(name, Type, getBaseContext().getPackageName());
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        addPreferencesFromResource(setResource("pref_easyviewpagerui","xml"));
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage(message);
        b.setCancelable(false);
        b.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.setTitle(title);
        AlertDialog ad = b.create();
        ad.show();
    }

    @Override
    public void onResume(){
        super.onResume();

        final String key_anim="gantiANIM";
        ListPreference listPreference_anim=(ListPreference)findPreference(key_anim);
        final String values_anim=PreferenceManager.getDefaultSharedPreferences(this).getString(key_anim,key_anim);
        final int index_anim=listPreference_anim.findIndexOfValue(values_anim);
        if (index_anim>=0){
            final String summary_anim = (String) listPreference_anim.getEntries()[index_anim];
            listPreference_anim.setSummary(summary_anim);
        }

        final String key="getCount";
        ListPreference listPreference=(ListPreference)findPreference(key);
        final String values=PreferenceManager.getDefaultSharedPreferences(this).getString(key,key);
        final int index=listPreference.findIndexOfValue(values);
        if (index>=0){
            final String summary = (String) listPreference.getEntries()[index];
            listPreference.setSummary(summary);
        }

        SharedPreferences setJudulSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String key1="setJudul1";
        final String key2="setJudul2";
        final String key3="setJudul3";

        String isikanJudul1 = setJudulSharedPreferences.getString(key1, "Page 1");
        String isikanJudul2 = setJudulSharedPreferences.getString(key2, "Page 2");
        String isikanJudul3 = setJudulSharedPreferences.getString(key3, "Page 3");

        Preference pref_judul1= findPreference(key1);
        Preference pref_judul2= findPreference(key2);
        Preference pref_judul3= findPreference(key3);

        pref_judul1.setSummary(isikanJudul1);
        pref_judul2.setSummary(isikanJudul2);
        pref_judul3.setSummary(isikanJudul3);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences options, String key) {
        if (key.equals("gantiANIM")){
            ListPreference listPreference=(ListPreference)findPreference(key);
            final String values=options.getString(key,key);
            final int index=listPreference.findIndexOfValue(values);
            if (index>=0){
                final String summary = (String) listPreference.getEntries()[index];
                listPreference.setSummary(summary);
            }
        }

        if (key.equals("getCount")){
            ListPreference listPreference=(ListPreference)findPreference(key);
            final String values=options.getString(key,key);
            final int index=listPreference.findIndexOfValue(values);
            if (index>=0){
                final String summary = (String) listPreference.getEntries()[index];
                listPreference.setSummary(summary);
            }
        }

        if (key.equals("setJudul1")){
            Preference pref_judul1= findPreference(key);
            String isikanJudul1 = options.getString(key,"Page 1");
            pref_judul1.setSummary(isikanJudul1);        
        }

        if (key.equals("setJudul2")){
            Preference pref_judul2= findPreference(key);
            String isikanJudul2 = options.getString(key,"Page 2");
            pref_judul2.setSummary(isikanJudul2);
        }

        if (key.equals("setJudul3")){
            Preference pref_judul3= findPreference(key);
            String isikanJudul3 = options.getString(key,"Page 3");
            pref_judul3.setSummary(isikanJudul3);
        }
    }

}