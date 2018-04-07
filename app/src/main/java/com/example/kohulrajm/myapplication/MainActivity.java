package com.example.kohulrajm.myapplication;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView out;
    Button change;
    Button restore;
    Button settings;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        out = (TextView)findViewById(R.id.output);
        change = (Button)findViewById(R.id.change);
        restore = (Button)findViewById(R.id.restore);
        settings = (Button)findViewById(R.id.settings);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        change.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                test(Integer.parseInt(sharedPref.getString("desktop_dpi", "200")), "Attached");
            }
        });

         settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Display the fragment as the main content.
               /*FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(android.R.id.content, new Settings());
                transaction.addToBackStack(null);
                        transaction.commit();*/
                Intent intent = new Intent(v.getContext(), Settings.class);
                startActivity(intent);

            }
        });

        restore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                test(Integer.parseInt(sharedPref.getString("restoration_dpi", "430")), "Detached");
            }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        out.setText("Your Current DPI is "+Integer.toString(metrics.densityDpi));

    }
    public void test(int dpi, String mode){
        ChangeDpi cdpi = new ChangeDpi();
        cdpi.setText(this, dpi, mode);
    }


}