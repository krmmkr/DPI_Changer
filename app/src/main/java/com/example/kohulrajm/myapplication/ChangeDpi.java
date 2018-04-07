package com.example.kohulrajm.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Kohul Raj M on 9/30/2017.
 */

public class ChangeDpi {
    int dpii;
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean autodpi = sharedPref.getBoolean("auto_change_dpi", false);
        if(autodpi == true) {
            setText(this, Integer.parseInt(sharedPref.getString("desktop_dpi", "200")));
        }
        this.finishAffinity();
        this.finish();
    }*/
    public void setText(Context context, int dpi, String mode){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        dpii = dpi;
            String command = "su";
            if (dpii < 100) {
                dpii = 100;
            }
            if (dpii > 500) {
                dpii = 500;
            }
            String density = "wm density " + Integer.toString(dpii) + "\n";
            Shell shell = new Shell();
            shell.shellCommand(command, density, sharedPref, mode);
    }
}
