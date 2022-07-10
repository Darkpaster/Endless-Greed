package com.darkpaster.pixellife;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.darkpaster.myLibrary.utils.Random;
import com.darkpaster.pixellife.R;

public class MainActivity extends Activity {

boolean CheckboxPreference;
String ListPreference;
String editTextPreference;
String ringtonePreference;
String secondEditTextPreference;
String customPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        
//        TextView button1 = findViewById(R.id.play_button);
        
//button1.setOnTouchListener(this);
    }
    
     @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    
    
    
    
    public void play(View v) {
    Intent intent = new Intent(this, PlayActivity.class);
startActivity(intent);
finish();
    }
    
    public void settings(View v) {
    Intent settingsActivity = new Intent(getBaseContext(),
                                                Preferences.class);
                                startActivity(settingsActivity);
                                
    }
    public void exit(View v) {
    finish();
    System.exit(0);
    }
    
    private void getPrefs() {
                // Get the xml/preferences.xml preferences
                SharedPreferences prefs = PreferenceManager
                                .getDefaultSharedPreferences(getBaseContext());
                CheckboxPreference = prefs.getBoolean("checkboxPref", true);
                ListPreference = prefs.getString("listPref", "nr1");
                editTextPreference = prefs.getString("editTextPref",
                                "Nothing has been entered");
                ringtonePreference = prefs.getString("ringtonePref",
                                "DEFAULT_RINGTONE_URI");
                secondEditTextPreference = prefs.getString("SecondEditTextPref",
                                "Nothing has been entered");
                // Get the custom preference
                SharedPreferences mySharedPreferences = getSharedPreferences(
                                "myCustomSharedPrefs", Activity.MODE_PRIVATE);
                customPref = mySharedPreferences.getString("myCusomPref", "");
        }
    
    
protected void onStart() { 
super.onStart();
getPrefs();
}
protected void onResume() { 
super.onResume();
}
protected void onPause() { 
super.onPause();
}
protected void onStop() { 
super.onStop();
}
protected void onDestroy() { 
super.onDestroy();
}
protected void onRestart() { 
super.onRestart();
}


}
