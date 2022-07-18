package com.darkpaster.pixellife.actors.mobs;

import android.content.Context;
import android.graphics.Paint;

public class Chicken extends Mob {

    public Chicken(Context context, String png, Paint paint){
        super(context, png, paint);
        this.shy = false;
        this.aggressive = false;
        this.warrior = false;
        this.name = "Курица";
        this.expDrop = 1.0f;
        this.HP = 6;
        this.HT = 6;
        this.DR = 0;
        this.ATK = 1;
        this.maxSpeed = 3.0f;
        this.speed = 1.0f;
    }
}
