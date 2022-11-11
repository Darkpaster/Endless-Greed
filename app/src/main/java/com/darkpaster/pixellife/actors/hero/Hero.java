package com.darkpaster.pixellife.actors.hero;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import com.darkpaster.myLibrary.utils.Physic;
import com.darkpaster.myLibrary.utils.Time;
import com.darkpaster.pixellife.GameActivity;
import com.darkpaster.pixellife.PlayActivity;
import com.darkpaster.pixellife.actors.Actor;
import com.darkpaster.pixellife.actors.mobs.Mob;
import com.darkpaster.pixellife.ui.Text;


public class Hero extends Actor {
    private static final long serialVersionUID = 5L;
    public float camPosX = -700.0f;
    public float camPosY = -700.0f;
protected int exp = 0;
protected int lvl = 1;
protected Mob target = null;
protected float calcSpeedX;
protected float calcSpeedY;


public boolean touch;
public float touch_x = GameActivity.center_x;
public float touch_y = GameActivity.center_y;
public float df = 0.0f;
public float df2 = 0.0f;

public float pointX = GameActivity.center_x;
public float pointY = GameActivity.center_y;
public boolean running = false;
public float startX;
public float startY;
public Text lvlup;
public Time autoAttack;
public Time press;
private boolean chasing = false;


public Hero(Context context, String png, Paint paint) {
super(context, png, paint);
autoAttack = new Time();
press = new Time();
if(PlayActivity.nick.equals("") || PlayActivity.nick.equals(" ")){
    this.name = "Безымянный";
}else {
    this.name = PlayActivity.nick;
}
lvlup = new Text(Color.GREEN, 50, 2);
this.HT = 40.0f;
this.HP = 40.0f;
this.ATK = 5.0f;
this.DR = 1.0f;
this.speed = 6.0f;
this.AS = 1.5f;
this.x = GameActivity.x;
this.y = GameActivity.y;
}



public void update() {

touch = GameActivity.touch;

if(target != null && target.getName().equals("none")){
    target = null;
}

    if(target != null && Physic.distance(x, y, target.getX(), target.getY()) > size * 7){
        target = null;
        //System.out.println("reset target because of range");
    }



float center_x = GameActivity.center_x;
float center_y = GameActivity.center_y;

startX = x;
startY = y;

  if(touch){
running = true;
touch_x = GameActivity.touch_x;
touch_y = GameActivity.touch_y;

float difX = touch_x - center_x;
float difY = touch_y - center_y;
pointX = x + difX;
pointY = y + difY;
}

    if(Physic.distance(pointX, pointY, x + size / 2, y + size / 2)
            < size && GameActivity.touch && target != null){
        target = null;
    }

    if(target != null && Physic.distance(pointX, pointY, target.getX() + size / 2, target.getY() + size / 2) < size && !target.lock && touch){
        chasing = true;
    }
    if(target == null && chasing){
        chasing = false;
        pointX = x;
        pointY = y;
    }


if(running){
 float bodyX = x + size / 2;
 float bodyY = y + size / 2;



 if(target != null && !target.getName().equals("none") && chasing){
     calcSpeedX = Physic.getSpeedX(target.getX(), target.getY(), this);
     calcSpeedY = Physic.getSpeedY(target.getX(), target.getY(), this);

     if(Physic.distance(x, y, target.getX(), target.getY()) > size) {

         if (x > target.getX() ) {
             x -= calcSpeedX;
         } else {
             x += calcSpeedX;
         }
         if (y > target.getY()) {
             y -= calcSpeedY;
         } else {
             y += calcSpeedY;
         }

     }
 }else{
     calcSpeedX = Physic.getSpeedX(pointX, pointY, this);
     calcSpeedY = Physic.getSpeedY(pointX, pointY, this);

     if(Physic.distance(bodyX, bodyY, pointX, pointY) > 12){


         if(bodyX > pointX){
             x -= calcSpeedX;
         }else{
             x += calcSpeedX;
         }
         if(bodyY > pointY){
             y -= calcSpeedY;
         }else{
             y += calcSpeedY;
         }


     }else{
         running = false;
     }

 }



}

float aftX = x;
float aftY = y;
df = aftX - startX;
df2 = aftY - startY;

}

public void earnExp(Actor actor){
    exp += actor.getExpDrop();
    if(exp >= lvl * lvl + 4){
        lvlUp();
        exp = 0;
    }else{
        lvlup.createText("+" + getString(actor.getExpDrop()) + "exp", x, y);
    }

}

public void lvlUp(){
    lvl++;
    HT += 10;
    HP = HT;
    ATK++;
    AS -= 0.01f;
    if(lvl % 3 == 0){
        DR++;
    }
    lvlup.createText("Level up!", x, y);
}

public void setTarget(Mob mob){
    this.target = mob;
}
public void setExp(int exp){this.exp = exp;}
public void setLvl(int lvl){this.lvl = lvl;}
    public int getExp(){return exp;}
public int getLvl(){return lvl;}

public Mob getTarget(){return target;}

    @Override
    public void update(Hero hero) {

    }
}