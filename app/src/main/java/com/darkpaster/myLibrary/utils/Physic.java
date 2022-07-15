package com.darkpaster.myLibrary.utils;

import com.darkpaster.pixellife.actors.hero.Hero;

public class Physic {


  public static float distance(float x, float y){
    return (float) Math.sqrt(((Hero.X - x) * (Hero.X - x)) + ((Hero.Y - y) * (Hero.Y - y)));
  }
  public static float distance(float hx, float hy, float x, float y){
    return (float) Math.sqrt(((hx - x) * (hx - x)) + ((hy - y) * (hy - y)));
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
