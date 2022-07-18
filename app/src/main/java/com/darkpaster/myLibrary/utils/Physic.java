package com.darkpaster.myLibrary.utils;

import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.pixellife.actors.Actor;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.level.TileMap;

public class Physic {


  public static float distance(float x, float y){
    return (float) Math.sqrt(((Hero.X - x) * (Hero.X - x)) + ((Hero.Y - y) * (Hero.Y - y)));
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


  public static float getSpeedX(float x, float y){
    float t = Texture.TOTAL_SIZE / 2;
    float y1 = (float) Math.sqrt((Hero.Y + t - y) * (Hero.Y + t - y));
    float x1 = (float) Math.sqrt((Hero.X + t - x) * (Hero.X + t - x));

    x1 /= (x1 + y1);

    if(x1 > 0.999f){
      x1 = 0.999f;
    }else if(x1 < 0.001f){
      x1 = 0.001f;
    }
    x1 *= Hero.speed;

    return x1;
  }

  public static float getDistanceY(float x, float y){
    float t = Texture.TOTAL_SIZE / 2;
    float y1 = (float) Math.sqrt((Hero.Y + t - y) * (Hero.Y + t - y));
    float x1 = (float) Math.sqrt((Hero.X + t - x) * (Hero.X + t - x));

      y1 /= (x1 + y1);

    if(y1 > 0.999f){
      y1 = 0.999f;
    }else if(y1 < 0.001f){
      y1 = 0.001f;
    }
      y1 *= Hero.speed;

    return y1;
  }


  public static float getDistanceX(Actor actor, float x, float y){
    float t = Texture.TOTAL_SIZE / 2;
    float y1 = (float) Math.sqrt( ( (y + t) - (Hero.Y + t) ) * ( (y + t) - (Hero.Y + t) ) );
    float x1 = (float) Math.sqrt( ( (x + t) - (Hero.X + t) ) * ( (x + t) - (Hero.X + t) ) );


    x1 /= (x1 + y1);

    if(x1 > 0.999f){
      x1 = 0.999f;
    }else if(x1 < 0.001f){
      x1 = 0.001f;
    }
    x1 *= actor.getMaxSpeed();
    return x1;
  }

  public static float getDistanceY(Actor actor, float x, float y){
    float t = Texture.TOTAL_SIZE / 2;
    float y1 = (float) Math.sqrt( ( (y + t) - (Hero.Y + t) ) * ( (y + t) - (Hero.Y + t) ) );
    float x1 = (float) Math.sqrt( ( (x + t) - (Hero.X + t) ) * ( (x + t) - (Hero.X + t) ) );

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
