package com.darkpaster.pixellife.actors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.darkpaster.myLibrary.SpaceBody;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.myLibrary.textures.TextureAtlas;
import com.darkpaster.pixellife.actors.hero.Hero;


public class Actor extends SpaceBody{
public final float tick = 1.0f;
public static float time = 0.0f;
public static boolean waitEnemy = true;
private float spended = 0.0f;
protected float xMob = 0.0f;
protected float yMob = 0.0f;
protected float size = Texture.TOTAL_SIZE;

protected String name = "Undefined";
protected String description = "None";
protected float HP = 0.0f;
protected float HT = 0.0f;
protected float ATK = 0.0f;
protected float DR = 0.0f;
protected float realSpeed = 1.0f;

protected TextureAtlas mob;
protected Paint paint;

public Actor(Context context, String png, Paint paint){
this.paint = paint;
mob = new TextureAtlas(context, png);
}



public void render(Canvas canvas, float x, float y, int xR, int yR, int w, int h, int m, boolean c){
  canvas.drawBitmap(mob.cut(xR, yR, w, h, m, c), x, y, paint);
canvas.drawText(name, x + 48.0f, y - 25.0f, paint);
}


protected void spend(float time){
this.time += time;
}

public static void spendAndNext(boolean enemy){
waitEnemy = enemy;
}

protected void act(){
update();

}

protected void attack(float hp, float damage){
hp -= DR(damage, Hero.DR);
hp--;
if (hp <= 0) {
hp = 0.0f;
}
}

protected float DR(float damage, float dr) {
return damage - dr > 0 ? damage - dr : 0;
}


public void setRealSpeed(float s) {realSpeed = s;}
public void setHP(float hp) {HP = hp;}
public void setHT(float ht) {HT = ht;}
public void setDR(float dr) {DR = dr;}
public void setATK(float atk) {ATK = atk;}
public void setName(String n) {name = n;}
public void setDescription(String s) {description = s;}
public float getHP() {return HP;}
public float getHT() {return HT;}
public float getDR() {return DR;}
public float getRealSpeed() {return realSpeed;}
public float getATK() {return ATK;}
public String getName() {return name;}
public String getDescription() {return description;}
public float getXMobs() {return xMob;}
public float getYMobs() {return yMob;}
public void setXMobs(float x) {xMob = x;}
public void setYMobs(float y) {yMob = y;}

}
