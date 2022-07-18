package com.darkpaster.pixellife.actors.hero;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import com.darkpaster.myLibrary.utils.Physic;
import com.darkpaster.pixellife.GameActivity;
import com.darkpaster.pixellife.PlayActivity;
import com.darkpaster.pixellife.actors.Actor;
import com.darkpaster.pixellife.actors.mobs.Mob;
import com.darkpaster.pixellife.ui.Text;


public class Hero extends Actor {
public static float X = 0.0f;
public static float Y = 0.0f;
public static int hero_posX = (int) GameActivity.center_x;
public static int hero_posY = (int) GameActivity.center_y;
public static String nickname = PlayActivity.nick;
public static float HP = 40.0f;
public static float HT = 40.0f;
public static float ATK = 5.0f;
public static float DR = 1.0f;
public static float speed = 6.0f;
public static float exp = 0;
public static float lvl = 1;
public static float AS = 1.0f;
public static Mob target = null;
public static float calcSpeedX;
public static float calcSpeedY;




public boolean touch;
public float touch_x = GameActivity.center_x;
public float touch_y = GameActivity.center_y;
private float dfX = 0.0f;
private float dfY = 0.0f;
  private float bfX = 0.0f;
private float bfY = 0.0f;
private float afX = 0.0f;
private float afY = 0.0f;
public static float df = 0.0f;
public static float df2 = 0.0f;

public static float pointX = GameActivity.center_x;
public static float pointY = GameActivity.center_y;
public static boolean running = false;
public static float startX;
public static float startY;
public static Text lvlup;


public Hero(Context context, String png, Paint paint) {
super(context, png, paint);
this.name = nickname;
lvlup = new Text(Color.GREEN, 50, 2);
}



@Override
public void update() {
touch = GameActivity.touch;

float center_x = GameActivity.center_x;
float center_y = GameActivity.center_y;

startX = X;
startY = Y;

  if(touch){
running = true;
touch_x = GameActivity.touch_x;
touch_y = GameActivity.touch_y;

float difX = touch_x - center_x;
float difY = touch_y - center_y;
pointX = X + difX;
pointY = Y + difY;
}


if(running){
 float bodyX = X + size / 2;
 float bodyY = Y + size / 2;

 calcSpeedX = Physic.getSpeedX(pointX, pointY);
 calcSpeedY = Physic.getDistanceY(pointX, pointY);

if(Physic.distance(bodyX, bodyY, pointX, pointY) > 12){


    if(bodyX > pointX){
        X -= calcSpeedX;
    }else{
        X += calcSpeedX;
    }
    if(bodyY > pointY){
        Y -= calcSpeedY;
    }else{
        Y += calcSpeedY;
    }



//    X += calcSpeedX;
//    Y += calcSpeedY;

}else{
    running = false;
}

}


float aftX = X;
float aftY = Y;
df = aftX - startX;
df2 = aftY - startY;

}

public static void earnExp(Actor actor){
    exp += actor.getExpDrop();
    if(exp >= lvl * lvl + 4){
        lvlUp();
        exp = 0;
    }
}

public static void lvlUp(){
    lvl++;
    HT += 10;
    HP = HT;
    ATK++;
    AS -= 0.01f;
    if(lvl % 3 == 0){
        DR++;
    }
    lvlup.createText("Level up!", X, Y);
}

public static void setTarget(Mob mob){
    target = mob;
}
}


