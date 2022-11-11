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

import static android.graphics.Color.RED;


public class Generator implements Serializable {
    private static final long serialVersionUID = 1L;
   private List<Rabbit> rabbits = new ArrayList<Rabbit>();
    private transient Context context;
    private transient Paint paint;
    private transient Paint targeting = null;
    private transient Canvas canvas;
    private Map<MobType, ArrayList<Mob>> mobType;
    private final int radiusX = (int) GameActivity.screenSizeX / 2 / Texture.TOTAL_SIZE;
    private final int radiusY = (int) GameActivity.screenSizeY / 2 / Texture.TOTAL_SIZE;
    private int totalUnits = 3;

    public Generator(Context context, Paint p, Canvas canvas){
        mobType = new HashMap<>();
        this.canvas = canvas;
        this.context = context;
        this.paint = p;
        targeting = new Paint();
        paint.setARGB(255, 100, 100, 230);
        paint.setTextSize(35.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        targeting.setColor(RED);

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


                if(Physic.pDistanceXorY(mobType.get(MobType.getMobType(i1 + 1)).get(i).getX(),
                        hero.getX()) < Texture.TOTAL_SIZE * (radiusX + 2) &&
                        Physic.pDistanceXorY(mobType.get(MobType.getMobType(i1 + 1)).get(i).getY(),
                                hero.getY()) < Texture.TOTAL_SIZE * (radiusY + 3)) {


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
                        if(hero.getTarget() != null && !hero.getTarget().getName().equals("none")){
                            if(targeting == null){
                                targeting = new Paint();
                                targeting.setColor(RED);
                            }
                            float x = hero.getTarget().getX();
                                    float y = hero.getTarget().getY();
                            canvas.drawLine(x, y - 3, x + Texture.TOTAL_SIZE, y - 3, targeting);
                            canvas.drawLine(x - 3, y, x - 3, y + Texture.TOTAL_SIZE, targeting);
                            canvas.drawLine(x + Texture.TOTAL_SIZE, y - 3, x + Texture.TOTAL_SIZE, y + Texture.TOTAL_SIZE - 3, targeting);
                            canvas.drawLine(x, y + Texture.TOTAL_SIZE, x + Texture.TOTAL_SIZE, y + Texture.TOTAL_SIZE, targeting);
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
                if(mobType.get(MobType.getMobType(i1 + 1)).get(i).getName().equals("none")){
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
                if (Random.NormalIntRange(0, 40) == 0) {
                    mobType.get(MobType.getMobType(1)).add(new Rabbit(context, "rabbit.png", paint));
                    mobType.get(MobType.getMobType(1)).get(mobType.get(MobType.getMobType(1)).size() - 1).setXY(Random.IntRange(100, 7500),
                            Random.IntRange(100, 7500));
                    totalUnits++;
                    //System.out.println(totalUnits);
                }
                if (Random.NormalIntRange(0, 50) == 0) {
                    mobType.get(MobType.getMobType(2)).add(new Chicken(context, "velocirooster.png", paint));
                    mobType.get(MobType.getMobType(2)).get(mobType.get(MobType.getMobType(2)).size() - 1).setXY(Random.IntRange(100, 7500),
                            Random.IntRange(100, 7500));
                    totalUnits++;
                }
                if (Random.NormalIntRange(0, 60) == 0) {
                    mobType.get(MobType.getMobType(3)).add(new Bandit(context, "bandit.png", paint));
                    mobType.get(MobType.getMobType(3)).get(mobType.get(MobType.getMobType(3)).size() - 1).setXY(Random.IntRange(100, 7500),
                            Random.IntRange(100, 7500));
                    totalUnits++;
                }
            }
        }
    }


}
