package com.darkpaster.pixellife.actors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.myLibrary.utils.Random;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.actors.mobs.Chicken;
import com.darkpaster.pixellife.actors.mobs.Mob;
import com.darkpaster.pixellife.actors.mobs.MobType;
import com.darkpaster.pixellife.actors.mobs.Rabbit;

import java.util.*;

public class Generator {
   private List<Rabbit> rabbits = new ArrayList<Rabbit>();
    private Context context;
    private Paint paint;
    private Canvas canvas;
    private Map<MobType, ArrayList<Mob>> mobType;

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

        mobType.get(MobType.getMobType(1)).add(new Rabbit(context, "rabbit.png", paint));
        mobType.get(MobType.getMobType(2)).add(new Chicken(context, "velocirooster.png", paint));
        mobType.get(MobType.getMobType(1)).get(0).setXYMobs(Random.Int(7000), Random.Int(7000));
        mobType.get(MobType.getMobType(2)).get(0).setXYMobs(Random.Int(7000), Random.Int(7000));
    }


    public void render(Canvas canvas) {

        for (int i1 = 0; i1 < mobType.size(); i1++) {
            for (int i = 0; i < mobType.get(MobType.getMobType(i1 + 1)).size(); i++) {


                if (!mobType.get(MobType.getMobType(i1 + 1)).get(i).name.equals("none")) {
                    mobType.get(MobType.getMobType(i1 + 1)).get(i).render(canvas, mobType.get(MobType.getMobType(i1 + 1)).get(i).getXMobs(),
                            mobType.get(MobType.getMobType(i1 + 1)).get(i).getYMobs(), 0, 0,
                            Texture.SPRITE_SIZE, Texture.SPRITE_SIZE, Texture.MULT_SIZE, false);
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
        if(!Hero.lvlup.getText().equals("")) {
            Hero.lvlup.render(canvas);
            Hero.lvlup.decreaseY(3.0f);
            Hero.lvlup.removeText();
        }
    }



    public  void update() {
        for (int i1 = 0; i1 < mobType.size(); i1++) {
            for (int i = 0; i < mobType.get(MobType.getMobType(i1 + 1)).size(); i++) {
                if(mobType.get(MobType.getMobType(i1 + 1)).get(i).getName().equals("")){
                    mobType.get(MobType.getMobType(i1 + 1)).remove(i);
                }else{
                    mobType.get(MobType.getMobType(i1 + 1)).get(i).update();
                }
            }
        }
    }


    public void spawn(){
        for (int i = 0; i < mobType.size(); i++) {
            if (Random.NormalIntRange(0, 20) == 0) {
                mobType.get(MobType.getMobType(1)).add(new Rabbit(context, "rabbit.png", paint));
                mobType.get(MobType.getMobType(2)).add(new Chicken(context, "velocirooster.png", paint));
                mobType.get(MobType.getMobType(1)).get(mobType.get(MobType.getMobType(1)).size() - 1).setXYMobs(Random.Int(7000), Random.Int(7000));
                mobType.get(MobType.getMobType(2)).get(mobType.get(MobType.getMobType(2)).size() - 1).setXYMobs(Random.Int(7000), Random.Int(7000));
            }
        }
    }


}
