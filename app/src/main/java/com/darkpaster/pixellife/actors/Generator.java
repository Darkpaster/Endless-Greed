package com.darkpaster.pixellife.actors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.myLibrary.utils.Physic;
import com.darkpaster.myLibrary.utils.Random;
import com.darkpaster.pixellife.GameActivity;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.actors.mobs.*;

import java.io.Serializable;
import java.util.*;



public class Generator implements Serializable {
   private List<Rabbit> rabbits = new ArrayList<Rabbit>();
    private transient Context context;
    private transient Paint paint;
    private transient Canvas canvas;
    private Map<MobType, ArrayList<Mob>> mobType;
    private int radius = (int) GameActivity.screenSizeY / 2 / Texture.TOTAL_SIZE;
    private int totalUnits = 3;

    public Generator(Context context, Paint p, Canvas canvas){
        mobType = new HashMap<>();
        this.canvas = canvas;
        this.context = context;
        this.paint = p;
        paint.setARGB(255, 100, 100, 230);
        paint.setTextSize(35.0f);
        paint.setTextAlign(Paint.Align.CENTER);

        mobType.put(MobType.RABBIT, new ArrayList<Mob>());
        mobType.put(MobType.CHICKEN, new ArrayList<Mob>());
        mobType.put(MobType.BANDIT, new ArrayList<Mob>());

        mobType.get(MobType.getMobType(1)).add(new Rabbit(context, "rabbit.png", paint));
        mobType.get(MobType.getMobType(2)).add(new Chicken(context, "velocirooster.png", paint));
        mobType.get(MobType.getMobType(3)).add(new Bandit(context, "bandit.png", paint));
        mobType.get(MobType.getMobType(1)).get(0).setXY(Random.Int(7000), Random.Int(7000));
        mobType.get(MobType.getMobType(2)).get(0).setXY(Random.Int(7000), Random.Int(7000));
        mobType.get(MobType.getMobType(3)).get(0).setXY(Random.Int(7000), Random.Int(7000));
    }


    public void render(Canvas canvas, Hero hero) {

        for (int i1 = 0; i1 < mobType.size(); i1++) {
            for (int i = 0; i < mobType.get(MobType.getMobType(i1 + 1)).size(); i++) {


                if(Physic.distance(mobType.get(MobType.getMobType(i1 + 1)).get(i).getX(),
                        mobType.get(MobType.getMobType(i1 + 1)).get(i).getY(), hero) < Texture.TOTAL_SIZE * (radius + 4)) {


                    if (!mobType.get(MobType.getMobType(i1 + 1)).get(i).name.equals("none")) {
                        if(!mobType.get(MobType.getMobType(i1 + 1)).get(i).getName().equals("Бандит")) {
                            mobType.get(MobType.getMobType(i1 + 1)).get(i).render(canvas, mobType.get(MobType.getMobType(i1 + 1)).get(i).getX(),
                                    mobType.get(MobType.getMobType(i1 + 1)).get(i).getY(), 0, 0,
                                    Texture.SPRITE_SIZE, Texture.SPRITE_SIZE, Texture.MULT_SIZE, false);
                        }else{
                            mobType.get(MobType.getMobType(i1 + 1)).get(i).render(canvas, mobType.get(MobType.getMobType(i1 + 1)).get(i).getX(),
                                    mobType.get(MobType.getMobType(i1 + 1)).get(i).getY(), 0, 0,
                                    Texture.SPRITE_SIZE - 4, Texture.SPRITE_SIZE, Texture.MULT_SIZE, false);
                        }
                    }
                    if (!mobType.get(MobType.getMobType(i1 + 1)).get(i).getEnemyText().equals("")) {
                        mobType.get(MobType.getMobType(i1 + 1)).get(i).enemyDamage.render(canvas);
                        mobType.get(MobType.getMobType(i1 + 1)).get(i).enemyDamage.decreaseY(3.0f);
                        mobType.get(MobType.getMobType(i1 + 1)).get(i).enemyDamage.removeText();
                    }
                    if (!mobType.get(MobType.getMobType(i1 + 1)).get(i).getHeroText().equals("")) {
                        mobType.get(MobType.getMobType(i1 + 1)).get(i).heroDamage.render(canvas);
                        mobType.get(MobType.getMobType(i1 + 1)).get(i).heroDamage.decreaseY(3.0f);
                        mobType.get(MobType.getMobType(i1 + 1)).get(i).heroDamage.removeText();
                    }
                }

            }
        }
        if(!hero.lvlup.getText().equals("")) {
            hero.lvlup.render(canvas);
            hero.lvlup.decreaseY(3.0f);
            hero.lvlup.removeText();
        }
    }



    public  void update(Hero hero) {
        for (int i1 = 0; i1 < mobType.size(); i1++) {
            for (int i = 0; i < mobType.get(MobType.getMobType(i1 + 1)).size(); i++) {
                if(mobType.get(MobType.getMobType(i1 + 1)).get(i).getName().equals("")){
                    mobType.get(MobType.getMobType(i1 + 1)).remove(i);
                }else{
                    mobType.get(MobType.getMobType(i1 + 1)).get(i).update(hero);
                }
            }
        }
    }


    public void spawn() {
        if (totalUnits < 100) {
            for (int i = 0; i < mobType.size(); i++) {
                if (Random.NormalIntRange(0, 50) == 0) {
                    mobType.get(MobType.getMobType(1)).add(new Rabbit(context, "rabbit.png", paint));
                    mobType.get(MobType.getMobType(1)).get(mobType.get(MobType.getMobType(1)).size() - 1).setXY(Random.Int(7000),
                            Random.Int(7000));
                    totalUnits++;
                    //System.out.println(totalUnits);
                }
                if (Random.NormalIntRange(0, 50) == 0) {
                    mobType.get(MobType.getMobType(2)).add(new Chicken(context, "velocirooster.png", paint));
                    mobType.get(MobType.getMobType(2)).get(mobType.get(MobType.getMobType(2)).size() - 1).setXY(Random.Int(7000),
                            Random.Int(7000));
                    totalUnits++;
                }
                if (Random.NormalIntRange(0, 50) == 0) {
                    mobType.get(MobType.getMobType(3)).add(new Bandit(context, "bandit.png", paint));
                    mobType.get(MobType.getMobType(3)).get(mobType.get(MobType.getMobType(3)).size() - 1).setXY(Random.Int(7000),
                            Random.Int(7000));
                    totalUnits++;
                }
            }
        }
    }


}
