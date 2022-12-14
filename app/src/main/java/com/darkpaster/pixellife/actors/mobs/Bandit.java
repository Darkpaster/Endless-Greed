package com.darkpaster.pixellife.actors.mobs;

import android.content.Context;
import android.graphics.Paint;

public class Bandit extends Mob{
    private static final long serialVersionUID = 6L;

    public Bandit(Context context, String png, Paint paint){
        super(context, png, paint);
        this.shy = false;
        this.aggressive = true;
        this.warrior = false;
        this.name = "Бандит";
        this.expDrop = 2.0f;
        this.HP = 20;
        this.HT = 20;
        this.DR = 1;
        this.ATK = 3;
        this.AS = 1.0f;
        this.speed = 2.0f;
        this.maxSpeed = 4.0f;
    }

}
