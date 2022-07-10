package com.darkpaster.pixellife.actors.mobs;

import android.content.Context;
import android.graphics.Paint;


public class Rabbit extends Mob {


public Rabbit(Context context, String png, Paint paint){
super(context, png, paint);
this.name = "Кролик";
this.HP = 10;
this.HT = 10;
this.DR = 1;
this.ATK = 2;
}

}