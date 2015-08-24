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
        addPreferencesFromResource(setResource("pref_easyviewpagerui", "xml"));
        final SharedPreferences setTema = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String setTema_str = setTema.getString("gantiTema", "NonAktif");
        setTheme(setResource(setTema_str,"style"));
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

        final String key_tema="gantiTema";
        ListPreference listPreference_tema=(ListPreference)findPreference(key_tema);
        final String values_tema=PreferenceManager.getDefaultSharedPreferences(this).getString(key_tema,key_tema);
        final int index_tema=listPreference_tema.findIndexOfValue(values_tema);
        if (index_tema>=0){
            final String summary_tema = (String) listPreference_tema.getEntries()[index_tema];
            listPreference_tema.setSummary(summary_tema);
        }

        final String key_transisi="gantiIonSetTransformPages";
        ListPreference listPreference_transisi=(ListPreference)findPreference(key_transisi);
        final String values_transisi=PreferenceManager.getDefaultSharedPreferences(this).getString(key_transisi,key_transisi);
        final int index_transisi=listPreference_transisi.findIndexOfValue(values_transisi);
        if (index_transisi>=0){
            final String summary_transisi = (String) listPreference_transisi.getEntries()[index_transisi];
            listPreference_transisi.setSummary(summary_transisi);
        }

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
            listPreference.setSummary(summary+" Pages");
        }

        final String key_jadikanhome="jadikanHome";
        ListPreference listPreference_jadikanhome=(ListPreference)findPreference(key_jadikanhome);
        final String values_jadikanhome=PreferenceManager.getDefaultSharedPreferences(this).getString(key_jadikanhome,key_jadikanhome);
        final int index_jadikanhome=listPreference_jadikanhome.findIndexOfValue(values_jadikanhome);
        if (index_jadikanhome>=0){
            final String summary_jadikanhome = (String) listPreference_jadikanhome.getEntries()[index_jadikanhome];
            listPreference_jadikanhome.setSummary("Home : (Page "+summary_jadikanhome+")");
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

        if (key.equals("gantiTema")){
            ListPreference listPreference=(ListPreference)findPreference(key);
            final String values=options.getString(key,key);
            final int index=listPreference.findIndexOfValue(values);
            if (index>=0){
                final String summary = (String) listPreference.getEntries()[index];
                listPreference.setSummary(summary);
            }
        }


        if (key.equals("gantiIonSetTransformPages")){
            ListPreference listPreference=(ListPreference)findPreference(key);
            final String values=options.getString(key,key);
            final int index=listPreference.findIndexOfValue(values);
            if (index>=0){
                final String summary = (String) listPreference.getEntries()[index];
                listPreference.setSummary(summary);
            }
        }

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
                listPreference.setSummary(summary+" Pages");
            }
        }

        if (key.equals("jadikanHome")){
            ListPreference listPreference=(ListPreference)findPreference(key);
            final String values=options.getString(key,key);
            final int index=listPreference.findIndexOfValue(values);
            if (index>=0){
                final String summary = (String) listPreference.getEntries()[index];
                listPreference.setSummary("Home : (Page "+summary+")");
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