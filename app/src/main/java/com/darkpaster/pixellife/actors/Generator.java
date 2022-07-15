package com.darkpaster.pixellife.actors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.myLibrary.utils.Random;
import com.darkpaster.pixellife.actors.mobs.Rabbit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generator {
    private  int addit = 1;
    private  Rabbit rabbit;
    private  List<Rabbit> rabbits = new ArrayList<Rabbit>();
    private Context context;
    private Paint paint;
    private Canvas canvas;

    public Generator(Context context, Paint p, Canvas canvas){
        this.canvas = canvas;
        rabbit = new Rabbit(context, "rabbit.png", p);
        rabbits.add(rabbit);
        rabbit.setXMobs(Texture.TOTAL_SIZE * 4);
        rabbit.setYMobs(Texture.TOTAL_SIZE * 3);
        this.context = context;
        this.paint = p;
        paint.setARGB(255, 100, 100, 230);
        paint.setTextSize(35.0f);
        paint.setTextAlign(Paint.Align.CENTER);
    }


    public  void render(Canvas canvas) {

        for (Rabbit value : rabbits) {
            if(!value.name.equals("none")) {
                value.render(canvas, value.getXMobs(), value.getYMobs(), 0, 0,
                        Texture.SPRITE_SIZE, Texture.SPRITE_SIZE, Texture.MULT_SIZE, false);
            }
            if(!value.enemyDamage.getText().equals("")){
             canvas.drawText(value.enemyDamage.getText(), value.enemyDamage.getX(), value.enemyDamage.getY(), value.enemyDamage.getPaint());
             value.enemyDamage.decreaseY(3.0f);
             if(System.currentTimeMillis() - value.enemyDamage.getStartTime() > value.enemyDamage.getFadeTime()){
                 value.enemyDamage.removeText();
             }
            }
            if(!value.heroDamage.getText().equals("")){
                canvas.drawText(value.heroDamage.getText(), value.heroDamage.getX(), value.heroDamage.getY(), value.heroDamage.getPaint());
                value.heroDamage.decreaseY(3.0f);
                if(System.currentTimeMillis() - value.heroDamage.getStartTime() > value.heroDamage.getFadeTime()){
                    value.heroDamage.removeText();
                }
            }
        }
    }

    public  void update(){
        for (int i = 0; i < rabbits.size(); i++) {
            if(rabbits.get(i).name.equals("none")) {
                    rabbits.remove(i);
            }else{
                rabbits.get(i).update();
            }
        }
    }


  /*private Map<MobType, Object> mobs;
  private MobType type;
  private int nMobs[] = {1, 1};


  public Generator(){
  mobs = new HashMap<MobType, Object>();
  mobs.put(type.RABBIT, new Rabbit());
  }

  public void spawn(){
  for (int j = 0; j < nMobs.getLenght; j++){
  if (Random.IntRange(10) == 0){
  mobs.get(nMobs[j]);
  }
  }

  }*/

    public void spawn(){
        if(Random.NormalIntRange(0, 20) == 0){
            //addit += Texture.SPRITE_SIZE;
            rabbits.add(new Rabbit(context, "rabbit.png", paint));
            rabbits.get(rabbits.size() - 1).setXMobs(Random.Int(7000));
            rabbits.get(rabbits.size() - 1).setYMobs(Random.Int(7000));
        }
    }


}
