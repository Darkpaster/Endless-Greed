package com.darkpaster.pixellife.ui;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.Serializable;

public class Text implements Serializable{
    private static final long serialVersionUID = 10L;
    private transient Paint paint = null;
    private long startTime = 0;
    private String text = "";
    private float x = 0;
    private float y = 0;
    private int fadeTime = 0;

    private int color1 = -1, color2 = -1, color3 = -1, colorA = -1, size = -1, color = -1;


    public Text(int red, int a, int green, int blue, int size){
        this.color1 = red;
        this.color2 = green;
        this.color3 = blue;
        this.colorA = a;
        this.size = size;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setARGB(a, red, green, blue);
        paint.setTextSize(size);
    }
    public Text(int color, int size){
        this.color = color;
        this.size = size;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setColor(color);
        this.paint.setTextSize(size);
    }

    public Text(int color, int size, int fade){
        this.color = color;
        this.size = size;
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
        if(System.currentTimeMillis() - this.getStartTime() > this.getFadeTime()){
            this.text = "";
            this.x = 0;
            this.y = 0;
            this.startTime = 0;
        }
    }

    public void render(Canvas canvas){
        if(paint == null){
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            if(color != -1){
                paint.setColor(color);
                paint.setTextSize(size);
            }
            if(color2 != -1){
                paint.setARGB(colorA, color1, color2, color3);
                paint.setTextSize(size);
            }
        }
        canvas.drawText(this.text, this.x, this.y, this.paint);
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
