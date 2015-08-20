package com.maaadgroup.viewpagergb;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import eu.chainfire.libsuperuser.Shell;

/**
 * Created by Suyono on 6/3/2015.
 * Copyright (c) 2015 by Suyono (ion).
 * All rights reserved.
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and decompilation.
 */
public class JalankanSetting extends LinearLayout {
    public int setResource(String name, String Type)
    {
        return getContext().getResources().getIdentifier(name, Type, getContext().getPackageName());
    }
    public JalankanSetting(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View root = inflater.inflate(setResource("setting_animasi", "layout"), this, true);
            Button config = (Button) root.findViewById(setResource("tombol", "id"));
            config.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(getContext(), setEasyViewpagerUI.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    getContext().startActivity(intent);
                }
            });
            Button preview = (Button) root.findViewById(setResource("previewnya", "id"));
            preview.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(getContext(), MyActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    getContext().startActivity(intent);
                }
            });
            Button restartui = (Button) root.findViewById(setResource("restartui", "id"));
            restartui.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), RestartUI.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    getContext().startActivity(intent);
                }
            });
        }
    }

    private class StartUp extends AsyncTask<String,Void,Void> {


        private Context context = null;
        boolean suAvailable = false;
        public StartUp setContext(Context context) {
            this.context = context;
            return this;
        }

        @Override
        protected Void doInBackground(String... params) {
            suAvailable = Shell.SU.available();
            if (suAvailable) {

                // suResult = Shell.SU.run(new String[] {"cd data; ls"}); Shell.SU.run("reboot");
                switch (params[0]){
                    //case "sysui"   : Shell.SU.run("am startservice -n com.android.systemui/.SystemUIService");break;
                    case "sysui"   : Shell.SU.run("pkill com.android.systemui");break;
                }
            }
            else{
                Toast.makeText(getContext(), "Phone not Rooted /n Sebaiknya di Root dulu HH nya ya gan :)", Toast.LENGTH_SHORT).show();
            }

            return null;
        }
    }
}
