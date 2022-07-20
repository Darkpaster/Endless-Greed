package com.darkpaster.pixellife.actors.mobs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Rabbit extends Mob {
    private static final long serialVersionUID = 8L;


public Rabbit(Context context, String png, Paint paint){
super(context, png, paint);
this.shy = true;
this.aggressive = false;
this.warrior = false;
this.name = "Кролик";
this.expDrop = 1.0f;
this.HP = 8;
this.HT = 8;
this.DR = 0;
this.ATK = 1;
this.speed = 1.0f;
}

}