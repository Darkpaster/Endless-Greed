package com.darkpaster.pixellife.ui;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Text {
    private Paint paint;
    private long startTime = 0;
    private String text = "";
    private float x = 0;
    private float y = 0;
    private int fadeTime = 0;

    public Text(int red, int a, int green, int blue, int size){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setARGB(a, red, green, blue);
        paint.setTextSize(size);
    }
    public Text(int color, int size){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setColor(color);
        this.paint.setTextSize(size);
    }

    public Text(int color, int size, int fade){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setColor(color);
        this.paint.setTextSize(size);
        this.fadeTime = fade * 1000;
    }


    public void createText(String text, float x, float y){
        startTime = System.currentTimeMillis();
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public void removeText(){
        this.text = "";
        this.x = 0;
        this.y = 0;
        this.startTime = 0;
    }

    public float getX(){return x;}
    public float getY(){return y;}
    public int getFadeTime(){return fadeTime;}
    public Long getStartTime(){return startTime;}
    public String getText(){return text;}
    public Paint getPaint(){return paint;}

    public void decreaseXY(float x, float y){
        this.x -= x;
        this.y -= y;
    }

    public void decreaseY(float y){
        this.y -= y;
    }
}
