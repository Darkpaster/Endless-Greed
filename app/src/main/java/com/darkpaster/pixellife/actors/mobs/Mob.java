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
protected boolean shy = false;


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
  private Time test;


  public Mob(Context context, String png, Paint paint) {
    super(context, png, paint);
    cd = new Time();
    cdHero = new Time();
    cdWalk = new Time();
    cdRun = new Time();
    test = new Time();
  }


  @Override
  public void update() {
//    if(aggressive && shy){
//      shy = false;
//    }
//    if(shy && aggressive || shy && warrior){
//      aggressive = false;
//      warrior = false;
//    }
//    if(warrior && shy){
//      shy = false;
//    }

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
      float beyondX = Physic.pDistanceXorY(Hero.X, TileMap.map_walls_x.get(i));
      float beyondY = Physic.pDistanceXorY(Hero.Y, TileMap.map_walls_y.get(i));

      //float beyondY = (float) Math.sqrt((Hero.Y - TileMap.map_walls_y.get(i)) * (Hero.Y - TileMap.map_walls_y.get(i)));
      if(beyondX < size || beyondY < size) {


        if(beyondX < size && beyondY < beyondX && Hero.X < TileMap.map_walls_x.get(i)){
          Hero.X -= (size - beyondX);
          //Hero.Y = Hero.startY;
        }else if(beyondX < size && beyondY < beyondX && Hero.X > TileMap.map_walls_x.get(i)){
          Hero.X += (size - beyondX);
          //Hero.Y = Hero.startY;
        }


          if (beyondY < size && beyondX < beyondY && Hero.Y < TileMap.map_walls_y.get(i)) {
            Hero.Y -= (size - beyondY);
            //Hero.X = Hero.startX;
          } else if (beyondY < size && beyondX < beyondY && Hero.Y > TileMap.map_walls_y.get(i)) {
            Hero.Y += (size - beyondY);
            //Hero.X = Hero.startX;
          }


      }
      float beyondXmob = Physic.pDistanceXorY(xMob, TileMap.map_walls_x.get(i));
      float beyondYmob = Physic.pDistanceXorY(yMob, TileMap.map_walls_y.get(i));
      if(beyondXmob < size || beyondYmob < size) {

        if(beyondXmob < size && beyondYmob < beyondXmob && xMob < TileMap.map_walls_x.get(i)){
          xMob -= (size - beyondXmob);
          //Hero.Y = Hero.startY;
        }else if(beyondXmob < size && beyondYmob < beyondXmob && xMob > TileMap.map_walls_x.get(i)){
          xMob += (size - beyondXmob);
          //Hero.Y = Hero.startY;
        }


        if (beyondYmob < size && beyondXmob < beyondYmob && yMob < TileMap.map_walls_y.get(i)) {
          yMob -= (size - beyondYmob);
          //Hero.X = Hero.startX;
        } else if (beyondYmob < size && beyondXmob < beyondYmob && yMob > TileMap.map_walls_y.get(i)) {
          yMob += (size - beyondYmob);
          //Hero.X = Hero.startX;
        }

          while (Physic.distance(xMob, yMob, TileMap.map_walls_x.get(i), TileMap.map_walls_y.get(i)) < size) {
            xMob += speed;
            yMob += speed;
        }

      }
    }

    if(Physic.distance(xMob, yMob) < size * 1){
      if(warrior && aggressive || this.HP > 3 && !warrior && aggressive) {
        xMob = startX;
        yMob = startY;
      }
    }

  }

  public void move() {

    if (cdRun.getStarted()) {
      hit();
      calcSpeedX = Physic.getDistanceX(this, xMob, yMob);
      calcSpeedY = Physic.getDistanceY(this, xMob, yMob);
      bodyMobx = xMob + size / 2;
      bodyMoby = yMob + size / 2;

      runAway(bodyMobx, bodyMoby, calcSpeedX, calcSpeedY);
    }else{
      if (Physic.distance(xMob, yMob) < size * 3) {

        hit();

        if (this.HP <= this.HT / 4 + 1 && !warrior || shy && !aggressive) {
          runAway(bodyMobx, bodyMoby, calcSpeedX, calcSpeedY);
        } else {
          if(aggressive) {
            chasing(bodyMobx, bodyMoby, calcSpeedX, calcSpeedY);
          }else{
            defaultMove();
          }
        }

      } else {
        defaultMove();
      }
    }
  }

  public void runAway(float bodyMobx, float bodyMoby, float calcSpeedX, float calcSpeedY){
    //System.out.println("runAway");
    if(shy){
      cdRun.cd(3.0f);
    }else{
      if(!shy && !aggressive){
        cdRun.cd(4.0f);
      }else{
        cdRun.cd(2.0f);
      }

    }
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

  public void defaultMove(){
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

  public void chasing(float bodyMobx, float bodyMoby, float calcSpeedX, float calcSpeedY){
    //System.out.println("chasing");
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
    if (Physic.distance(xMob, yMob) < size * 1 + 10) {
      if(Hero.target != null && Physic.distance(Hero.target.getXMobs(), Hero.target.getYMobs()) > size * 1 + 10){
        Hero.target = null;
      }
      //System.out.println("hit");

      if (cd.cd(1.0f)) {
        if(aggressive) {
          attack();
        }
      }

      if(cdHero.cd(Hero.AS) && !this.name.equals("none")){
        if(Hero.target == null){
          Hero.setTarget(this);
        }
        if(Hero.target == this){
          attack(this);
          if(!this.shy && !aggressive){
            runAway(bodyMobx, bodyMoby, calcSpeedX, calcSpeedY);
          }
        }
      }
    }
  }


}