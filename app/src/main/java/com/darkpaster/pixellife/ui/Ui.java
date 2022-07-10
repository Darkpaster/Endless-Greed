package com.darkpaster.pixellife.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.darkpaster.myLibrary.textures.Sprite;
import com.darkpaster.myLibrary.textures.TextureAtlas;


public class Ui {

private Paint paint;
private TextureAtlas infoAtlas;
private TextureAtlas icAtlas;
private TextureAtlas hpAtlas;
private Sprite heroInfo;
private Sprite heroIc;
private Sprite hpBar;
//96x32

public Ui(Context context){
paint = new Paint(Paint.ANTI_ALIAS_FLAG);
infoAtlas = new TextureAtlas(context, "ui_hero_info.png");
icAtlas = new TextureAtlas(context, "char.png");
hpAtlas = new TextureAtlas(context, "hp_bar.png");
heroInfo = new Sprite(paint, infoAtlas.cut(0, 0, 96, 32, 4, false));
heroIc = new Sprite(paint, icAtlas.cut(2, 0, 14, 7, 10, false));
paint.setTextSize(40.0f);
paint.setTextAlign(Paint.Align.CENTER);
}

public void render(Canvas canvas, float hp, float ht){
heroInfo.draw(0.0f, 0.0f, canvas);
heroIc.draw(0.0f, 25.0f, canvas);
hpBar(canvas, hp, ht);
}

public void hpBar(Canvas canvas, float hp, float ht){
float part = ht / 62.0f;
//62x14
float fin = hp / part;
if (fin > 62){
fin = 62.0f;
}
if(fin <= 1){
fin = 1.0f;
}else{
hpBar = new Sprite(paint, hpAtlas.cut(0, 0, (int)fin, 14, 4, false));
hpBar.draw(132.0f, 4.0f, canvas);
}
String hp1 = String.format("%.1f", hp);
String ht1 = String.format("%.1f", ht);
canvas.drawText(hp1 + "/" + ht1, 250.0f, 47.0f, paint);

//hp: " + Float.toString(hp) + "/" + Float.toString(ht)"

}
}
