package com.darkpaster.pixellife.actors.mobs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.myLibrary.utils.Physic;
import com.darkpaster.myLibrary.utils.Random;
import com.darkpaster.pixellife.GameActivity;
import com.darkpaster.pixellife.actors.Actor;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.level.TileMap;


public class Mob extends Actor {

  protected long cd = 0;


  private float hX = 0.0f;
  private float hY = 0.0f;
  private float difX = 0.0f;
  private float difY = 0.0f;
  private int random = 2;
  private float startX = 0.0f;
  private float startY = 0.0f;
  private float nextX = xMob;
  private float nextY = yMob;

  private long time;


  public Mob(Context context, String png, Paint paint) {
    super(context, png, paint);
  }


  @Override
  public void update() {
    startX = xMob;
    startY = yMob;

    hX = Hero.X;
    hY = Hero.Y;
    difX = hX - xMob;
    difY = hY - yMob;
    if (Physic.distance(xMob, yMob) < Texture.TOTAL_SIZE * 3) {
      if (Physic.distance(xMob, yMob) < Texture.TOTAL_SIZE * 1 + 10) {
        if (cd == 0) {
          cd = System.currentTimeMillis();
        }
        if (cd != 0 && System.currentTimeMillis() - cd > 1000) {
          attack(this);
          attack();
          cd = 0;
        }
      }
      //if (Physic.radius(size * 1, hX, hY, xMob, yMob, size)){}


      if (hX > xMob && hY > yMob) {
        xMob += maxSpeed;
        yMob += maxSpeed;
      } else if (hX < xMob && hY < yMob) {
        xMob -= maxSpeed;
        yMob -= maxSpeed;
      } else if (hX < xMob && hY > yMob) {
        xMob -= maxSpeed;
        yMob += maxSpeed;
      } else if (hX > xMob && hY < yMob) {
        xMob += maxSpeed;
        yMob -= maxSpeed;
      } else if (hX > xMob && difX > difY) {
        xMob += maxSpeed;
      } else if (hX < xMob && difX < difY) {
        xMob -= maxSpeed;
      } else if (hY < yMob && difY < difX) {
        yMob -= maxSpeed;
      } else if (hY > yMob && difY > difX) {
        yMob += maxSpeed;
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
    float df1 = startX - xMob;
    float df2 = startY - yMob;


    for (int i = 0; i < TileMap.map_walls_y.size(); i++) {
      if (Physic.collisionW(Hero.X, Hero.Y, TileMap.map_walls_x.get(i), TileMap.map_walls_y.get(1), size)) {
        Hero.X = Hero.startX;
        Hero.Y = Hero.startY;
      }
    }

if(Physic.distance(xMob, yMob) < Texture.TOTAL_SIZE * 1){
  xMob = startX;
  yMob = startY;
}

//    for (int i = 0; i < TileMap.map_walls_y.size(); i++) {
//
//      if (Physic.collisionW(Hero.X, Hero.Y, TileMap.map_walls_x.get(i), TileMap.map_walls_y.get(i), size)) {
//        GameActivity.touch = true;
//        Hero.X -= Hero.startX;
//        Hero.Y -= Hero.startY;
//        System.out.println("hero collision");
//      }
//      if (Physic.collisionW(xMob, yMob, TileMap.map_walls_x.get(i), TileMap.map_walls_y.get(i), size)) {
//        xMob = startX;
//        yMob = startY;
//        System.out.println("mob collision");
//      }
//    }
    if (time == 0) {
      time = System.currentTimeMillis();
    }
    if (time != 0 && System.currentTimeMillis() - time > 2000) {
      random = Random.IntRange(1, 10);
      time = 0;
    }
    //posX = (int) (xMob / size);
    //posY = (int) (yMob / size);


  }

  private boolean collision() {
    return false;
  }

}