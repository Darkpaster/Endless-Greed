package com.darkpaster.pixellife.actors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.darkpaster.myLibrary.SpaceBody;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.myLibrary.textures.TextureAtlas;
import com.darkpaster.myLibrary.utils.Random;
import com.darkpaster.pixellife.Game;
import com.darkpaster.pixellife.GameActivity;
import com.darkpaster.pixellife.PlayActivity;
import com.darkpaster.pixellife.Statistic;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.ui.Text;
import com.darkpaster.pixellife.ui.Ui;

import static android.graphics.Color.*;


public abstract class Actor extends SpaceBody{
    private static final long serialVersionUID = 3L;
//public static boolean waitEnemy = true;
protected float size = Texture.TOTAL_SIZE;

protected String name = "Undefined";
protected String description = "None";
protected float HP = 0.0f;
protected float HT = 0.0f;
protected float ATK = 0.0f;
protected float DR = 0.0f;
protected float AS = 1.0f;
protected float expDrop = 0.0f;
protected float goldDrop = 0.0f;

public transient TextureAtlas mob = null;
protected transient Paint paint = null;
public transient Ui ui = null;
public Text enemyDamage;
public Text heroDamage;
private String png;

public Actor(Context context, String png, Paint paint){
    this.png = png;
    this.paint = paint;
mob = new TextureAtlas(context, png);
ui = new Ui(context);
enemyDamage = new Text(RED, 40, 1);
heroDamage = new Text(YELLOW, 40, 1);
}



public void render(Canvas canvas, float x, float y, int xR, int yR, int w, int h, int m, boolean c){
    if(mob == null){
        mob = new TextureAtlas(Game.context_, png);
    }
    if(ui == null){
        ui = new Ui(Game.context_);
    }
    if(paint == null){
        paint = Game.p;
        paint.setARGB(255, 100, 100, 230);
        paint.setTextSize(35.0f);
        paint.setTextAlign(Paint.Align.CENTER);
    }
  canvas.drawBitmap(mob.cut(xR, yR, w, h, m, c), x, y, paint);
canvas.drawText(name, x + 48.0f, y - 25.0f, paint);

if(!this.name.equals(PlayActivity.nick) && !this.name.equals("Безымянный")) {
    ui.enemyHPBar(x, y, HP, HT, canvas);
}
}



protected void act(){

}

protected void attack(Hero hero){
    float damage = damageRoll(this.ATK);
    damage = DR(damage, hero.getDR());
hero.HP -= damage;
if (hero.getHP() <= 0) {
    hero.HP = 0.0f;
//die();
}
int i = (int) damage;
enemyDamage.createText(Integer.toString(i), hero.getX() + Texture.TOTAL_SIZE / 2, hero.getY());
}

    protected void attack(Hero hero, Actor mob){
        float damage = damageRoll(hero.getATK());
        damage = DR(damage, hero.getTarget().DR);
        hero.getTarget().HP -= damage;
        int dam = (int) damage;
        if(Statistic.maxDealtDamage < dam) {
            Statistic.maxDealtDamage = dam;
        }
        heroDamage.createText(Integer.toString(dam), hero.getTarget().x + Texture.TOTAL_SIZE / 2, hero.getTarget().y);
        if (hero.getTarget().HP <= 0) {
            hero.getTarget().HP = 0.0f;
            die(hero);
        }

    }

protected float DR(float damage, float dr) {
return damage - dr > 0 ? damage - dr : 0;
}

protected float damageRoll(float damage){
    int i = (int) damage;
    if(damage > 1){
        return (float) Random.IntRange(i / 2, i);
    }else{
        return 1;
    }
}

protected void die(Hero hero){
    Statistic.killCount++;
    hero.earnExp(hero.getTarget());
    hero.getTarget().name = "none";
    hero.setTarget(null);
}

protected String getString(float num){
    int i = (int) num;
    return Integer.toString(i);
}


public void setHP(float hp) {HP = hp;}
public void setHT(float ht) {HT = ht;}
public void setDR(float dr) {DR = dr;}
public void setATK(float atk) {ATK = atk;}
    public void setAS(int AS){this.AS = AS;}
public void setName(String n) {name = n;}
public void setDescription(String s) {description = s;}
public float getHP() {return HP;}
public float getHT() {return HT;}
public float getDR() {return DR;}
public float getATK() {return ATK;}
    public float getAS(){return AS;}
public String getName() {return name;}
public String getDescription() {return description;}
    public float getExpDrop(){return expDrop;}

    public String getEnemyText(){
    return enemyDamage.getText();
    }
    public String getHeroText(){
        return heroDamage.getText();
    }

}
