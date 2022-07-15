package com.darkpaster.pixellife.actors.hero;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.darkpaster.myLibrary.utils.Physic;
import com.darkpaster.pixellife.GameActivity;
import com.darkpaster.pixellife.PlayActivity;
import com.darkpaster.pixellife.actors.Actor;


public class Hero extends Actor {
public static float X = 0.0f;
public static float Y = 0.0f;
public static int hero_posX = (int) GameActivity.center_x;
public static int hero_posY = (int) GameActivity.center_y;
public static String nickname = PlayActivity.nick;
public static float HP = 100.0f;
public static float HT = 100.0f;
public static float ATK = 5.0f;
public static float DR = 1.0f;
public static float speed = 6.0f;




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


public Hero(Context context, String png, Paint paint) {
super(context, png, paint);
this.name = nickname;
}



@Override
public void update() {
touch = GameActivity.touch;

float center_x = GameActivity.center_x;
float center_y = GameActivity.center_y;

//if (X % size == 0){
//  int posX_before = (int) (X / size);
//}
//if (Y % size == 0){
//  int posY_before = (int) (Y / size);
//}



startX = X;
startY = Y;

//if(touch && center_x != GameActivity.touch_x || center_y != GameActivity.touch_y && touch){
//GameActivity.touch = false;
//if(running){
//running = false;
//}else{

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

if(bodyX < pointX && bodyY < pointY){
X += speed;
Y += speed;
}else if(bodyX > pointX && bodyY > pointY){
X -= speed;
Y -= speed;
}else if(bodyX > pointX && bodyY < pointY){
X -= speed;
Y += speed;
}else if(bodyX < pointX && bodyY > pointY){
X += speed;
Y -= speed;
}else if(bodyX < pointX){
X += speed;
}else if(bodyX > pointX){
X -= speed;
}else if(bodyY < pointY){
Y += speed;
}else if(bodyY > pointY){
Y -= speed;
}

if(Physic.distance(bodyX, bodyY, pointX, pointY) < 5){
  running = false;
}
}


float aftX = X;
float aftY = Y;
df = aftX - startX;
df2 = aftY - startY;

}
}


