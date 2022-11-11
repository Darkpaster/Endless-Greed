package com.darkpaster.pixellife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.darkpaster.myLibrary.Saver;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.myLibrary.textures.TextureAtlas;

public class PlayActivity extends Activity {

private TextureAtlas patrik;
public static String nick = " ";
private EditText nickName;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        //w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        
w.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);

    Button newGame = (Button) findViewById(R.id.start_game_button);
        Button savedGame = (Button) findViewById(R.id.start_saved_game_button);

        if(Saver.checkGameState()){
            newGame.setX(-300);
            savedGame.setX(300);
            //System.out.println("ssssssssss");
        }else{
            savedGame.setVisibility(View.GONE);
            //System.out.println("aaaaaaaaaaaaaaa");
        }
       
        
        ImageView character = (ImageView) findViewById(R.id.char_image);
        patrik = new TextureAtlas(this, "char.png");
        character.setImageBitmap(patrik.cut(0, 0, Texture.SPRITE_SIZE, Texture.SPRITE_SIZE, 10, false));
        nickName = (EditText) findViewById(R.id.edit_nickName);
        
    nickName.setCursorVisible(false);
    
    nickName.setOnClickListener(new OnClickListener()
{
    @Override
    public void onClick(View v)
    {
nickName.setCursorVisible(true);
    }
});



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
nickName.setCursorVisible(false);
    Intent intent = new Intent(this, MainActivity.class);
startActivity(intent);
finish();
}
    
    public void menu(View v) {
    Intent intent = new Intent(this, MainActivity.class);
startActivity(intent);
finish();
    }
    
    public void previous(View v){
      Toast.makeText(getApplicationContext(), "Пока только один", Toast.LENGTH_LONG).show();
    }
    
    public void next(View v){
    Context context = v.getContext();
        System.out.println(context.getFilesDir().getAbsoluteFile() + " 1");
        System.out.println(context.getFilesDir().getPath() + " 2");
        System.out.println(context.getFilesDir().toString() + " 3");
        System.out.println(context.getFilesDir().getName() + " 4");
      Toast.makeText(getApplicationContext(), "Пока только один", Toast.LENGTH_LONG).show();
    }
    
    public void startGame(View v) {
        Context context = v.getContext();
        Saver.setPath(context.getFilesDir().getPath());
    if(Saver.delete()){
        nick = nickName.getText().toString();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    }

    public void startSavedGame(View v) {
        Context context = v.getContext();
        Saver.setPath(context.getFilesDir().getPath());
        //nick = nickName.getText().toString();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }
    
    public void emptyTap(View v) {
   View view = this.getCurrentFocus();
if (view != null) {  
    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
} 
nickName.setCursorVisible(false);
    }
    
    protected void onStart() { 
super.onStart();
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
