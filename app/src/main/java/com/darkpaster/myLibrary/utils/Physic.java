package com.darkpaster.myLibrary.utils;

public class Physic {
  private float side1;
  private float side2;
  private float side3;
  private float side4;
  private float side5;
  private float side6;
  private float side7;
  private float side8;

  /*else {
  if(hX2 >= x && hY2 <= y || hX2 >= x && hY >= y2 || hY2 >= y && hX <= x2 || hY >= y2 && hX >= x2){
    return true;
    }
    if(hX <= x2 && hY <= y2 || hX <= x2 && hY2 >= y || hX2 >= x && hY2 >= y || hX2 >= x && hY <= y2){
    return false;
    }else {*/

  // if (hX > x2 || hX2 < x || hY > y2 || hY2 < y

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
  
  public static boolean collisionM(float hX, float hY, float x, float y, float size, int mul) {
    float hX2 = hX + size;
    float hY2 = hY + size;
    float x2 = x + size;
    float y2 = y + size;
    
    float r1 = x + (size * mul);
    float r2 = y + (size * mul);
    float r3 = x - (size * mul);
    float r4 = y - (size * mul);
    

    if (hX >= r1 || hX <= r3 || hY >= r2 || hY <= r4) {
      return false;
    } else {
      return true;
    }
  }

  public static boolean collisionH(float hX, float hY, float x, float y, float size) {
    float hX2 = hX + size;
    float hY2 = hY + size;
    float x2 = x + size;
    float y2 = y + size;

    if(hX < x2 && hY != y){
    return true;
    }else if(hX2 > x && hY != y){
    return true;
    }else if(hY < y2 && hX != x){
    return true;
    }else if(hY2 > y && hX != x){
    return true;
    }else if (hX < x2 && hY == y) {
      return true;
    } else if (hX2 > x && hY == y) {
      return true;
    } else if (hY < y2 && hX == x) {
      return true;
    } else if (hY2 > y && hX == x) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean radius(float r, float hX, float hY, float x, float y, float size) {
    float side1 = x + r;
    float side2 = y + r;
    float side3 = x - r;
    float side4 = y - r;

    float side5 = side1 - size;
    float side6 = side2 - size;
    float side7 = side3 + size;
    float side8 = side4 + size;

    float r4 = r / 2;

    float side9 = calculate(x, -r4, 1);
    float side10 = calculate(y, -r4, 1);
    float side11 = calculate(x, r4, 1);
    float side12 = calculate(y, r4, 1);
    // не готово ещё
    if (hX >= side1 && hY >= side10 && hY <= side12
        || hX + size <= side3 && hY >= side10 && hY <= side12
        || hY >= side2 && hX >= side9 && hX <= side11
        || hY + size <= side4 && hX >= side9 && hX <= side11
        || hX <= side9 && hY <= side10
        || hX >= side11 && hY >= side12
        || hX <= side9 && hY >= side12
        || hX >= side11 && hY <= side10) {
      return false;
    } else {
      return true;
    }
  }

  public static float calculate(float xy, float size, float r4, int mul) {
    return xy + (r4 * mul);
  }

  public static float calculate(float xy, float r4, int mul) {
    return xy + (r4 * mul);
  }
}
