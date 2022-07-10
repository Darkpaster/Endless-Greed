package com.darkpaster.pixellife.actors.mobs;

import android.content.Context;
import android.graphics.Paint;
import com.darkpaster.myLibrary.utils.Physic;
import com.darkpaster.myLibrary.utils.Random;
import com.darkpaster.pixellife.GameActivity;
import com.darkpaster.pixellife.actors.Actor;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.level.TileMap;


public class Mob extends Actor {
protected int posX = 0;
protected int posY = 0;

private float hX = 0.0f;
private float hY = 0.0f;
private float difX = 0.0f;
private float difY = 0.0f;
private int random = 2;
private float startX = 0.0f;
private float startY = 0.0f;
private float nextX = xMob;
private float nextY = yMob;
private int i1, i2 = 0;
private boolean stop, stop1, stopH = false;



public Mob(Context context, String png, Paint paint){
super(context, png, paint);
}


@Override
public void update(){
startX = xMob;
startY = yMob;
if(!waitEnemy/* || xMob % size != 0 || yMob % size != 0*/) {
hX = Hero.X;
hY = Hero.Y;
difX = hX - xMob;
difY = hY - yMob;
  if (Physic.radius(size * 4, hX, hY, xMob, yMob, size)){
  //if (Physic.radius(size * 1, hX, hY, xMob, yMob, size)){}
  
  
  if(hX > xMob && hY > yMob){
  xMob += speed;
  yMob += speed;
  }else if(hX < xMob && hY < yMob){
  xMob -= speed;
    yMob -= speed;
  }else if(hX < xMob && hY > yMob){
  xMob -= speed;
  yMob += speed;
  }else if(hX > xMob && hY < yMob){
  xMob += speed;
  yMob -= speed;
  }else if(hX > xMob && difX > difY){
  xMob += speed;
  }else if(hX < xMob && difX < difY){
  xMob -= speed;
  }else if(hY < yMob && difY < difX){
  yMob -= speed;
  }else if(hY > yMob && difY > difX){
  yMob += speed;
  }
  }else{
  switch (random){
  case 1: xMob += speed; break;
  case 2: xMob -= speed; break;
  case 3: yMob += speed; break;
  case 4: yMob -= speed; break;
  default: break;
  }
  }
  float df1 = startX - xMob;
  float df2 = startY - yMob;
  if(startX % size == 0 && startY % size == 0){
    nextX = startX - ((size / speed) * df1);
    nextY = startY - ((size / speed) * df2);
  }
  if(Hero.X % size == 0 && Hero.Y % size == 0){
    //TileMap.map_walls_x.add(TileMap.map_walls_x.size(), Hero.nextX);
    //TileMap.map_walls_y.add(TileMap.map_walls_y.size(), Hero.nextY);
  }

  for(int i = 0; i < TileMap.map_walls_y.size(); i++) {
if(Hero.nextX == TileMap.map_walls_x.get(i) && Hero.nextY == TileMap.map_walls_y.get(i)){
    GameActivity.touch = true;
    Hero.X -= Hero.df;
    Hero.Y -= Hero.df2;
}
    if (Physic.collisionM(Hero.X, Hero.Y, TileMap.map_walls_x.get(i), TileMap.map_walls_y.get(i), size, 2)) {


        if(Hero.nextX == startX && Hero.nextY == startY
                || Hero.nextX == TileMap.map_walls_x.get(i) && Hero.nextY == TileMap.map_walls_y.get(i)){
        GameActivity.touch = true;
        Hero.X -= Hero.df;
        Hero.Y -= Hero.df2;

        }
    }
  }
  if(xMob % size == 0 || yMob % size == 0){
    random = Random.IntRange(1, 8);
  }
  posX = (int) (xMob / size);
  posY = (int) (yMob / size);
}else{
        if (xMob % size != 0){
        xMob = (xMob / size) * size;
        }
        if(yMob % size != 0){
          yMob = (yMob / size) * size;
        }
}


}

}
//if(!waitEnemy/* && Hero.startX % size == 0 && Hero.startY % size == 0*/){
//        stop1 = false;
//        stopH = false;
//        nextX = startX - ((size / speed) * df1);
//        nextY = startY - ((size / speed) * df2);
//        if(nextX == Hero.nextX && nextY == Hero.nextY){
//        stop1 = true;
//        }else if(nextX == Hero.startX && nextY == Hero.startY){
//        stop1 = true;
//        }
//        stop = false;
//        i1 = 0;
//        i2 = 0;
//        for(int i = 0; i < TileMap.map_walls_y.size(); i++){
//
//        if(Physic.collisionM(xMob, yMob, TileMap.map_walls_x.get(i), TileMap.map_walls_y.get(i), size, 1)){
//
//
//        if(startX == TileMap.map_walls_x.get(i)){
//        i1 = i;
//        }
//        if(startY == TileMap.map_walls_y.get(i)){
//        i2 = i;
//        }
//
//
//        if(nextX == TileMap.map_walls_x.get(i) && nextY == TileMap.map_walls_y.get(i)){
//        stop = true;
//
//        }
//
//        }
//
//        }
//        if(stop){
//        if(i1 != 0){
//        xMob = startX;
//        }
//        if(i2 != 0){
//        yMob = startY;
//        }
//        }
//        if(stop1){
//        xMob = startX;
//        yMob = startY;
//        }
//        if(stopH){
//        Hero.X -= Hero.df;
//        Hero.Y -= Hero.df2;
//        }
//        if (Physic.collisionM(hX, hY, xMob, yMob, size, 2) && startX % size == 0 && startY % size == 0){
//
//        hX = Hero.X;
//        hY = Hero.Y;
//        if(Physic.collisionM(hX, hY, xMob, yMob, size, 1) || Physic.collisionM(Hero.nextX, Hero.nextY, xMob, yMob, size, 1)
//        && Physic.collisionM(hX, hY, xMob, yMob, size, 2)) {
//        xMob = startX;
//        yMob = startY;
//
//        if(Hero.running && Physic.collisionM(hX, hY, xMob, yMob, size, 1)){
//        GameActivity.touch = true;
//        if(Hero.nextX == startX && Hero.nextY == startY){
//        GameActivity.touch = true;
//        Hero.X -= Hero.df;
//        Hero.Y -= Hero.df2;
//        }
//        }
//        }
//
//
//        }
//
//
//        float a = xMob % size;
//        float b = yMob % size;
//        posX = (int) ((xMob + a) / size);
//
//        posY = (int) ((yMob + b) / size);
//
//        df1 = startX - xMob;
//        df2 = startY - yMob;
//
//        if (waitEnemy && df1 != 0 || df2 != 0 && waitEnemy){
//        xMob = posX * size;
//        yMob = posY * size;
//        if(xMob == Hero.X && yMob == Hero.Y){
//        xMob -= df1 * (size / speed);
//        yMob -= df2 * (size / speed);
//        }
//        }
//
//        if(waitEnemy && xMob != posX * size || waitEnemy && yMob != posY * size){
//        xMob = posX * size;
//        yMob = posY * size;
//        Hero.X += Hero.X % size;
//        Hero.Y += Hero.Y % size;
//        }
//
//
//        }