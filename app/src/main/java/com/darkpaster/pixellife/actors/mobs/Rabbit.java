package com.darkpaster.pixellife.actors.mobs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Rabbit extends Mob {


public Rabbit(Context context, String png, Paint paint){
super(context, png, paint);
this.shy = true;
this.aggressive = false;
this.warrior = false;
this.name = "Кролик";
this.expDrop = 1.0f;
this.HP = 10;
this.HT = 10;
this.DR = 1;
this.ATK = 2;
this.speed = 1.0f;
}

}