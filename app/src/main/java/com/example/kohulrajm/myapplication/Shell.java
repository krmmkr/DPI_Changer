package com.example.kohulrajm.myapplication;

import android.content.SharedPreferences;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Kohul Raj M on 9/27/2017.
 */

public class Shell {
    public String shellCommand(String command, String density, SharedPreferences sp, String mode){
        StringBuffer sb = new StringBuffer();
        String output = "no response" ;
Process p;
        Boolean autoorientation = sp.getBoolean("auto_change_orientation", false);
        Boolean autobrightness = sp.getBoolean("auto_change_brightness", false);
        try{
          p =  Runtime.getRuntime().exec(command);
            //output stream
            DataOutputStream dos = new DataOutputStream(p.getOutputStream());

            dos.writeBytes(density);
            dos.writeBytes("wm reboot\n");

            if(mode.equals("Attached")){
                if(autoorientation == true) {
                    dos.writeBytes("content insert --uri context://settings/system --bind name:s:user_rotation --bind value:i:1\n");
                }
                if(autobrightness == true) {
                    dos.writeBytes("content insert --uri context://settings/system --bind name:s:screen_brightness --bind value:i:0\n");
                }
            }else{
                if(autoorientation == true) {
                    dos.writeBytes("content insert --uri context://settings/system --bind name:s:user_rotation --bind value:i:0\n");
                }
                if(autobrightness == true) {
                    dos.writeBytes("content insert --uri context://settings/system --bind name:s:screen_brightness --bind value:i:15\n");
                }
            }

            dos.writeBytes("exit\n");
            dos.flush();
            dos.close();
            p.waitFor();

            //input stream
        /*  BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
           String line = "";
            while ((line = r.readLine())!= null) {
                sb.append(line);
                sb.append("\n");
            }
            output = sb.toString();*/
        }
        catch (IOException io){
            io.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
