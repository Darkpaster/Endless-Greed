package com.darkpaster.myLibrary.utils;

import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.pixellife.Game;
import com.darkpaster.pixellife.actors.Actor;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.level.TileMap;

public class Physic {


  public static float distance(float x, float y, Hero hero){
    return (float) Math.sqrt(((hero.getX() - x) * (hero.getX() - x)) + ((hero.getY() - y) * (hero.getY() - y)));
  }

  public static float distance(float hx, float hy, float x, float y){
    return (float) Math.sqrt(((hx - x) * (hx - x)) + ((hy - y) * (hy - y)));
  }

  public static float distanceXorY(float hx, float x){
    return hx - x;
  }

  public static float pDistanceXorY(float hx, float x){
    return (float) Math.sqrt((hx - x) * (hx - x));
  }


  public static float getSpeedX(float x, float y, Hero hero){
    float t = Texture.TOTAL_SIZE / 2;
    float y1 = (float) Math.sqrt((hero.getY() + t - y) * (hero.getY() + t - y));
    float x1 = (float) Math.sqrt((hero.getX() + t - x) * (hero.getX() + t - x));

    x1 /= (x1 + y1);

    if(x1 > 0.999f){
      x1 = 0.999f;
    }else if(x1 < 0.001f){
      x1 = 0.001f;
    }
    x1 *= hero.getSpeed();

    return x1;
  }

  public static float getSpeedY(float x, float y, Hero hero){
    float t = Texture.TOTAL_SIZE / 2;
    float y1 = (float) Math.sqrt((hero.getY() + t - y) * (hero.getY() + t - y));
    float x1 = (float) Math.sqrt((hero.getX() + t - x) * (hero.getX() + t - x));

      y1 /= (x1 + y1);

    if(y1 > 0.999f){
      y1 = 0.999f;
    }else if(y1 < 0.001f){
      y1 = 0.001f;
    }
      y1 *= hero.getSpeed();

    return y1;
  }


  public static float getDistanceX(Actor actor, Hero hero, float x, float y){
    float t = Texture.TOTAL_SIZE / 2;
    float y1 = (float) Math.sqrt( ( (y + t) - (hero.getY() + t) ) * ( (y + t) - (hero.getY() + t) ) );
    float x1 = (float) Math.sqrt( ( (x + t) - (hero.getX() + t) ) * ( (x + t) - (hero.getX() + t) ) );


    x1 /= (x1 + y1);

    if(x1 > 0.999f){
      x1 = 0.999f;
    }else if(x1 < 0.001f){
      x1 = 0.001f;
    }
    x1 *= actor.getMaxSpeed();
    return x1;
  }

  public static float getDistanceY(Actor actor, Hero hero, float x, float y){
    float t = Texture.TOTAL_SIZE / 2;
    float y1 = (float) Math.sqrt( ( (y + t) - (hero.getY() + t) ) * ( (y + t) - (hero.getY() + t) ) );
    float x1 = (float) Math.sqrt( ( (x + t) - (hero.getX() + t) ) * ( (x + t) - (hero.getX() + t) ) );

    y1 /= (x1 + y1);

    if(y1 > 0.999f){
      y1 = 0.999f;
    }else if(y1 < 0.001f){
      y1 = 0.001f;
    }
    y1 *= actor.getMaxSpeed();
    return y1;
  }


  public static boolean collisionW(float hX, float hY, float x, float y, float size) {
    float hX2 = hX + size;
    float hY2 = hY + size;
    float x2 = x + size;
    float y2 = y + size;

    if (hX2 < x || hY > y2 || hY2 < y) {
      return false;
    } else {
      return true;
    }
  }
}
