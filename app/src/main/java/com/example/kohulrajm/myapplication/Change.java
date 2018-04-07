package com.example.kohulrajm.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Kohul Raj M on 10/2/2017.
 */

public class Change extends BroadcastReceiver{
    int dpii= 200;

    public void setText(Context context){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean autodpi = sharedPref.getBoolean("auto_change_dpi", false);

        if(autodpi == true) {
            dpii = Integer.parseInt(sharedPref.getString("desktop_dpi", "200"));
            String command = "su";
            if(dpii<100){
                dpii=100;
            }
            if(dpii>500){
                dpii=500;
            }
            String density = "wm density "+Integer.toString(dpii) + "\n";
            Shell shell= new Shell();
            shell.shellCommand(command, density, sharedPref, "Attached");
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        setText(context);
    }
}
