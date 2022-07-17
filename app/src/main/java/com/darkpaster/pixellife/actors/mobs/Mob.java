package com.darkpaster.pixellife.actors.mobs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.myLibrary.utils.Physic;
import com.darkpaster.myLibrary.utils.Random;
import com.darkpaster.myLibrary.utils.Time;
import com.darkpaster.pixellife.GameActivity;
import com.darkpaster.pixellife.actors.Actor;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.level.TileMap;


public class Mob extends Actor {
protected boolean aggressive = true;
protected boolean warrior = true;


  private float hX = 0.0f;
  private float hY = 0.0f;
  private float difX = 0.0f;
  private float difY = 0.0f;
  private int random = 2;
  private float startX = 0.0f;
  private float startY = 0.0f;
  private float bodyMobx;
  private float bodyMoby;
  private float calcSpeedX;
  private float calcSpeedY;



  private Time cd;
  private Time cdHero;
  private Time cdWalk;
  private Time cdRun;


  public Mob(Context context, String png, Paint paint) {
    super(context, png, paint);
    cd = new Time();
    cdHero = new Time();
    cdWalk = new Time();
    cdRun = new Time();
  }


  @Override
  public void update() {
    startX = xMob;
    startY = yMob;

    hX = Hero.X + size / 2;
    hY = Hero.Y + size / 2;
    difX = hX - xMob;
    difY = hY - yMob;

    move();

    float df1 = startX - xMob;
    float df2 = startY - yMob;

    collision(df1, df2);

    if (cdWalk.cd(2.0f)) {
      random = Random.IntRange(1, 10);
    }

  }

  public void collision(float df1, float df2){
    for (int i = 0; i < TileMap.map_walls_y.size(); i++) {
      if(Physic.distance(TileMap.map_walls_x.get(i), TileMap.map_walls_y.get(i)) < Texture.TOTAL_SIZE * 1) {
        Hero.X = Hero.startX;
        Hero.Y = Hero.startY;
      }
      if(Physic.distance(xMob, yMob, TileMap.map_walls_x.get(i), TileMap.map_walls_y.get(i)) < Texture.TOTAL_SIZE * 1) {
        xMob = startX;
        yMob = startY;

        if(df1 != 0 || df2 != 0) {
          while (Physic.distance(xMob, yMob, TileMap.map_walls_x.get(i), TileMap.map_walls_y.get(i)) < Texture.TOTAL_SIZE * 1) {
            xMob += df1;
            yMob += df2;
          }
        }

      }
    }

    if(Physic.distance(xMob, yMob) < Texture.TOTAL_SIZE * 1){
      if(warrior && aggressive || this.HP > 3 && !warrior && aggressive) {
        xMob = startX;
        yMob = startY;
      }
    }

  }

  public void move() {
    if (cdRun.getStarted()) {
      calcSpeedX = Physic.getDistanceX(this, xMob, yMob);
      calcSpeedY = Physic.getDistanceY(this, xMob, yMob);
      bodyMobx = xMob + size / 2;
      bodyMoby = yMob + size / 2;

      runAway(bodyMobx, bodyMoby, calcSpeedX, calcSpeedY);
    }else{
      if (Physic.distance(xMob, yMob) < Texture.TOTAL_SIZE * 3) {

        hit();

        if (this.HP <= 3 && !warrior || !aggressive) {
          runAway(bodyMobx, bodyMoby, calcSpeedX, calcSpeedY);
        } else {
          chasing(bodyMobx, bodyMoby, calcSpeedX, calcSpeedY);
        }


      } else {
        switch (random) {
          case 1:
            xMob += speed;
            break;
          case 2:
            xMob -= speed;
            break;
          case 3:
            yMob += speed;
            break;
          case 4:
            yMob -= speed;
            break;
          default:
            break;
        }
      }
    }
  }

  public void runAway(float bodyMobx, float bodyMoby, float calcSpeedX, float calcSpeedY){
    cdRun.cd(2.0f);
    if(hX > bodyMobx){
      xMob -= calcSpeedX;
    }else{
      xMob += calcSpeedX;
    }
    if(hY > bodyMoby){
      yMob -= calcSpeedY;
    }else{
      yMob += calcSpeedY;
    }
  }

  public void chasing(float bodyMobx, float bodyMoby, float calcSpeedX, float calcSpeedY){
    if (hX > bodyMobx) {
      xMob += calcSpeedX;
    } else {
      xMob -= calcSpeedX;
    }
    if (hY > bodyMoby) {
      yMob += calcSpeedY;
    } else {
      yMob -= calcSpeedY;
    }
  }

  public void hit(){
    if (Physic.distance(xMob, yMob) < Texture.TOTAL_SIZE * 1 + 10) {
      if(Hero.target != null && Physic.distance(Hero.target.getXMobs(), Hero.target.getYMobs()) > Texture.TOTAL_SIZE * 1 + 10){
        Hero.target = null;
      }

      if (cd.cd(1.0f)) {
        if(aggressive) {
          attack();
        }
      }

      if(cdHero.cd(Hero.AS)){
        if(Hero.target == null){
          Hero.setTarget(this);
        }
        if(Hero.target == this){
          attack(this);
        }
      }
    }
  }


}