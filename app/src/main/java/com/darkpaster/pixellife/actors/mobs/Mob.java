package com.darkpaster.pixellife.actors.mobs;

import android.content.Context;
import android.graphics.Paint;
import com.darkpaster.myLibrary.utils.Physic;
import com.darkpaster.myLibrary.utils.Random;
import com.darkpaster.myLibrary.utils.Time;
import com.darkpaster.pixellife.Game;
import com.darkpaster.pixellife.actors.Actor;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.level.TileMap;

//import static com.darkpaster.myLibrary.utils.Physic.game;


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



  private transient Time cd;
  private transient Time cdHero;
  private transient Time cdWalk;
  private transient Time cdRun;
  private transient Time test;


  public Mob(Context context, String png, Paint paint) {
    super(context, png, paint);
    cd = new Time();
    cdHero = new Time();
    cdWalk = new Time();
    cdRun = new Time();
    test = new Time();

  }


  @Override
  public void update(Hero hero) {
//    if(aggressive && shy){
//      shy = false;
//    }
//    if(shy && aggressive || shy && warrior){
//      aggressive = false;
//      warrior = false;
//    }
//    if(warrior && shy){
//      shy = false;
//

    startX = x;
    startY = y;

    hX = hero.getX() + size / 2;
    hY = hero.getY() + size / 2;
    difX = hX - x;
    difY = hY - y;

    move(hero);

    float df1 = startX - x;
    float df2 = startY - y;

    collision(df1, df2, hero);

    if (cdWalk.cd(2.0f)) {
      random = Random.IntRange(1, 10);
    }

  }


  public void collision(float df1, float df2, Hero hero){

    for (int i = 0; i < TileMap.map_walls_y.size(); i++) {
      float beyondX = Physic.pDistanceXorY(hero.getX(), TileMap.map_walls_x.get(i));
      float beyondY = Physic.pDistanceXorY(hero.getY(), TileMap.map_walls_y.get(i));

      //float beyondY = (float) Math.sqrt((Hero.Y - TileMap.map_walls_y.get(i)) * (Hero.Y - TileMap.map_walls_y.get(i)));
      if(beyondX < size || beyondY < size) {


        if(beyondX < size && beyondY < beyondX && hero.getX() < TileMap.map_walls_x.get(i)){
          hero.setX(hero.getX() - (size - beyondX));
          //Hero.Y = Hero.startY;
        }else if(beyondX < size && beyondY < beyondX && hero.getX() > TileMap.map_walls_x.get(i)){
          hero.setX(hero.getX() + (size - beyondX));
          //Hero.Y = Hero.startY;
        }


          if (beyondY < size && beyondX < beyondY && hero.getY() < TileMap.map_walls_y.get(i)) {
            hero.setY(hero.getY() - (size - beyondY));
            //Hero.X = Hero.startX;
          } else if (beyondY < size && beyondX < beyondY && hero.getY() > TileMap.map_walls_y.get(i)) {
            hero.setY(hero.getY() + (size - beyondY));
            //Hero.X = Hero.startX;
          }


      }
      float beyondXmob = Physic.pDistanceXorY(x, TileMap.map_walls_x.get(i));
      float beyondYmob = Physic.pDistanceXorY(y, TileMap.map_walls_y.get(i));
      if(beyondXmob < size || beyondYmob < size) {

        if(beyondXmob < size && beyondYmob < beyondXmob && x < TileMap.map_walls_x.get(i)){
          x -= (size - beyondXmob);
          //Hero.Y = Hero.startY;
        }else if(beyondXmob < size && beyondYmob < beyondXmob && x > TileMap.map_walls_x.get(i)){
          x += (size - beyondXmob);
          //Hero.Y = Hero.startY;
        }


        if (beyondYmob < size && beyondXmob < beyondYmob && y < TileMap.map_walls_y.get(i)) {
          y -= (size - beyondYmob);
          //Hero.X = Hero.startX;
        } else if (beyondYmob < size && beyondXmob < beyondYmob && y > TileMap.map_walls_y.get(i)) {
          y += (size - beyondYmob);
          //Hero.X = Hero.startX;
        }

          while (Physic.distance(x, y, TileMap.map_walls_x.get(i), TileMap.map_walls_y.get(i)) < size) {
            x += speed;
            y += speed;
        }

      }
    }

    if(Physic.distance(x, y, hero) < size * 1){
      if(warrior && aggressive || this.HP > 3 && !warrior && aggressive) {
        x = startX;
        y = startY;
      }
    }

  }

  public void move(Hero hero) {

    if (cdRun.getStarted()) {
      hit(hero);
      calcSpeedX = Physic.getDistanceX( this, hero, x, y);
      calcSpeedY = Physic.getDistanceY( this, hero, x, y);
      bodyMobx = x + size / 2;
      bodyMoby = y + size / 2;

      runAway(bodyMobx, bodyMoby, calcSpeedX, calcSpeedY);
    }else{
      if (Physic.distance(x, y, hero) < size * 3) {

        hit(hero);

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
      x -= calcSpeedX;
    }else{
      x += calcSpeedX;
    }
    if(hY > bodyMoby){
      y -= calcSpeedY;
    }else{
      y += calcSpeedY;
    }
  }

  public void defaultMove(){
    switch (random) {
      case 1:
        x += speed;
        break;
      case 2:
        x -= speed;
        break;
      case 3:
        y += speed;
        break;
      case 4:
        y -= speed;
        break;
      default:
        break;
    }
  }

  public void chasing(float bodyMobx, float bodyMoby, float calcSpeedX, float calcSpeedY){
    //System.out.println("chasing");
    if (hX > bodyMobx) {
      x += calcSpeedX;
    } else {
      x -= calcSpeedX;
    }
    if (hY > bodyMoby) {
      y += calcSpeedY;
    } else {
      y -= calcSpeedY;
    }
  }

  public void hit(Hero hero){
    if (Physic.distance(x, y, hero) < size * 1 + 10) {
      if(hero.getTarget() != null && Physic.distance(hero.getTarget().getX(), hero.getTarget().getY(), hero) > size * 1 + 10){
        hero.setTarget(null);
      }
      //System.out.println("hit");

      if (cd.cd(this.AS)) {
        if(aggressive) {
          attack(hero);
        }
      }

      if(cdHero.cd(hero.getAS()) && !this.name.equals("none")){
        if(hero.getTarget() == null){
          hero.setTarget(this);
        }
        if(hero.getTarget() == this){
          attack(hero, this);
          if(!this.shy && !aggressive){
            runAway(bodyMobx, bodyMoby, calcSpeedX, calcSpeedY);
          }
        }
      }
    }
  }


}