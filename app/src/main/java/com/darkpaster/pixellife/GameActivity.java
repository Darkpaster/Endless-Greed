package com.darkpaster.pixellife;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.pixellife.level.TileMap;

public class GameActivity extends Activity implements OnTouchListener {

private int icon = R.drawable.ic_launcher;
public Texture txt;
private Canvas canv;
private Game gameThread;
private TileMap map;

public static float touch_x;
public static float touch_y;
public static boolean touch;
public static boolean stop = false;
private boolean tap = false;

public static float center_x = 0.0f;
public static float center_y = 0.0f;
public static float cam_x = 0.0f;
public static float cam_y = 0.0f;
public static float x = 0.0f;
public static float y = 0.0f;
public static float x_10 = 0.0f;
public static float y_10 = 0.0f;
private int width;
private int height;
private final String CAM_X_KEY = "Xbdejr";
private final String CAM_Y_KEY = "Ymfhwj";
private final String X_KEY = "Xqpjr";
private final String Y_KEY = "Ymqlj";


//public static boolean ready = true;

@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
    RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.game_layout);
    
        txt = new Texture(this);
 mainLayout.addView(txt);
 
 
 canv = txt.cnv;
 
 mainLayout.setOnTouchListener(this);
 Toast.makeText(getApplicationContext(), "onCreate called", Toast.LENGTH_LONG).show();
        
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            
            
        }
        map = new TileMap();
        Display display = getWindowManager().getDefaultDisplay();
Point size = new Point();
display.getSize(size);
//width = (size.x / 2) - 48;
//height = (size.y / 2) - 48;
width = ((size.x / Texture.TOTAL_SIZE) / 2);
height = ((size.y / Texture.TOTAL_SIZE) / 2);
center_x = TileMap.map_pos_x[height][width];
center_y = TileMap.map_pos_y[height][width];
//center_x = TileMap.map_pos_x[4][4];
//center_y = TileMap.map_pos_y[7][7];
x = center_x;
y = center_y;
width = size.x / 10;
height = size.y / 10;
x_10 = width;
y_10 = height;

    }
    
    
    @Override
protected void onSaveInstanceState(Bundle outState) {
outState.putFloat(CAM_X_KEY, cam_x);
outState.putFloat(CAM_Y_KEY, cam_y);
outState.putFloat(X_KEY, x);
outState.putFloat(Y_KEY, y);
super.onSaveInstanceState(outState);
}

@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {  
super.onRestoreInstanceState(savedInstanceState); 
cam_x = savedInstanceState.getFloat(CAM_X_KEY);
cam_y = savedInstanceState.getFloat(CAM_Y_KEY);
x = savedInstanceState.getFloat(X_KEY);
y = savedInstanceState.getFloat(Y_KEY);
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
    
    @Override
public void onBackPressed() {
    touch = false;
}

public boolean onTouch(View v, MotionEvent act){

   if (act.getAction() == MotionEvent.ACTION_DOWN) {
   touch = true;
   touch_x = act.getX();
    touch_y = act.getY();
    touch_x = touch_x - (touch_x % Texture.TOTAL_SIZE);
    touch_y = touch_y - (touch_y % Texture.TOTAL_SIZE);
   } else if (act.getAction() == MotionEvent.ACTION_UP) {
   touch = false;
   } else if (act.getAction() == MotionEvent.ACTION_MOVE) {
   touch = false;
   }
   
   
    return true;
     }
     
     public void tap() {
     
     }
    
    
    
    protected void onStart() { 
super.onStart();
Toast.makeText(getApplicationContext(), "onStart called", Toast.LENGTH_LONG).show();
}
protected void onResume() { 
super.onResume();
Toast.makeText(getApplicationContext(), "onResume called", Toast.LENGTH_LONG).show();
}
protected void onPause() { 
stop = true;
super.onPause();
Toast.makeText(getApplicationContext(), "onPause called", Toast.LENGTH_LONG).show();
}
protected void onStop() { 
stop = true;
super.onStop();
Toast.makeText(getApplicationContext(), "onStop called", Toast.LENGTH_LONG).show();
}

protected void onDestroy() { 
super.onDestroy();
Toast.makeText(getApplicationContext(), "onDestroy called", Toast.LENGTH_LONG).show();
}
protected void onRestart() { 
super.onRestart();
stop = false;
Toast.makeText(getApplicationContext(), "onRestart called", Toast.LENGTH_LONG).show();
}


}
