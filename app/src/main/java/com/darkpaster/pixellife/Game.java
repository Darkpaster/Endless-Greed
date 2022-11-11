package com.darkpaster.pixellife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import com.darkpaster.myLibrary.Saver;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.myLibrary.utils.Physic;
import com.darkpaster.myLibrary.utils.Time;
import com.darkpaster.pixellife.actors.Generator;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.actors.mobs.Rabbit;
import com.darkpaster.pixellife.level.TileMap;
import com.darkpaster.pixellife.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import static android.graphics.Color.WHITE;

public class Game implements Runnable{

//public static final int WIDTH = TileMap.WORLD_SIZE_X;
//public static final int HEIGHT = TileMap.WORLD_SIZE_Y;




public static final float UPDATE_RATE = 60.0f; // Фпс
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE; //Секунду делим на 60 (Результат в наносекундах).
    public static final long IDLE_TIME = 1; // Задержка потока в 1 миллисекунду
    private int fps;
    private int upd;
    private int fps1;
    private boolean fpsc = false;


public Thread mainThread;
private Canvas canvas;
private final SurfaceHolder holder;
private boolean running = false;
private final Paint paint;
public static Paint p = null;
private final Paint pain = new Paint(Paint.ANTI_ALIAS_FLAG);
public Hero hero = null;
//public static Hero defaultHero = null;
//public Rabbit rabbit;
public TileMap map;
private boolean first = true;
//private boolean saved = false;
private final Ui ui;
public Generator mobs = null;
//public static Generator defaultGenerator = null;

public static float x = 0.0f;
public static float y = 0.0f;
private float camX = 0.0f;
private float camY = 0.0f;
public static float centerX = 0.0f;
public static float centerY = 0.0f;
public static Context context_;

private int test = 0;


private HashMap<String, Object> savedData = new HashMap<>();


public Game(SurfaceHolder holder, Context context, Canvas canvas, Paint paint) {
    context_ = context;
this.canvas = canvas;
this.holder = holder;
this.paint = paint;
p = paint;
mainThread = new Thread(this);
map = new TileMap(context, paint);

    init(context);
ui = new Ui(context);

pain.setTextSize(40.0f);
pain.setColor(WHITE);
}

public void init(Context context) {
//    int i = 1;
//    if(i == 0){


        if(hero == null && mobs == null){
            if(!GameActivity.myBundle.isEmpty()){
                hero = (Hero) GameActivity.myBundle.getSerializable(GameActivity.HERO_KEY);
                mobs = (Generator) GameActivity.myBundle.getSerializable(GameActivity.MOBS_KEY);
                System.out.println("Game has been restored from bundle");
            }else{
                try {
                    savedData = Saver.loadData();
                }catch (ClassNotFoundException e){
                    System.out.println("suicide");
                    e.printStackTrace();
                }
                if(savedData != null && savedData.size() > 0){
                    hero = (Hero) savedData.get(GameActivity.HERO_KEY);
                    mobs = (Generator) savedData.get(GameActivity.MOBS_KEY);
                    System.out.println("Saved game has been loaded");
                    GameActivity.myBundle = new Bundle();
                    GameActivity.myBundle.putSerializable(GameActivity.HERO_KEY, hero);
                    GameActivity.myBundle.putSerializable(GameActivity.MOBS_KEY, mobs);
                    try{
                        Saver.saveData(GameActivity.myBundle);
                        System.out.println("Saved");
                    }catch (IOException e){
                        System.out.println("help");
                        e.printStackTrace();
                    }
                    System.out.println(Saver.getFileSize());
                }else{
                    hero = new Hero(context, "char.png", paint);
                    mobs = new Generator(context, paint, canvas);
                    System.out.println("Created new game");
                    GameActivity.myBundle = new Bundle();
                    GameActivity.myBundle.putSerializable(GameActivity.HERO_KEY, hero);
                    GameActivity.myBundle.putSerializable(GameActivity.MOBS_KEY, mobs);
                    try{
                        Saver.saveData(GameActivity.myBundle);
                        System.out.println("Saved");
                    }catch (IOException e){
                        System.out.println("help");
                        e.printStackTrace();
                    }
                    System.out.println(Saver.getFileSize());
                }
            }

        }else{

                if(GameActivity.myBundle == null){
                    GameActivity.myBundle = new Bundle();
                }
                    GameActivity.myBundle.putSerializable(GameActivity.HERO_KEY, hero);
                    GameActivity.myBundle.putSerializable(GameActivity.MOBS_KEY, mobs);

                try{
                    Saver.saveData(GameActivity.myBundle);
                    System.out.println("Saved");
                }catch (IOException e){
                    System.out.println("help");
                    e.printStackTrace();
                }


        }


//    }else{
//
//
//        if(!GameActivity.myBundle.isEmpty()) {
//            hero = (Hero) GameActivity.myBundle.getSerializable(GameActivity.HERO_KEY);
//            mobs = (Generator) GameActivity.myBundle.getSerializable(GameActivity.MOBS_KEY);
//            //System.out.println("1!");
//            try {
//                Saver.saveData(GameActivity.myBundle);
//                System.out.println("ok");
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }else{
//            //System.out.println("2!");
//            hero = new Hero(context, "char.png", paint);
//            mobs = new Generator(context, paint, canvas);
//        }
//
//    }



}

public void start() {
mainThread.start();
}

public void setRunning(boolean run) {
this.running = run; }



private void update(){
if(!GameActivity.stop){
hero.update();
mobs.update(hero);
mobs.spawn();
    //System.out.println(Saver.checkGameState());

}
}

public void clearCanvas(){canvas.drawARGB(255, 0, 0, 0); }

public void count() {if (fpsc) { fps1 = fps;
    int upd1 = upd;
fpsc = false;
}}


    public void adjust(){
    if(x + camX != centerX){
        if(x + camX < centerX) {
            float diff = centerX - (x + camX);
            camX += diff;
        }else{
            float diff = (x + camX) - centerX;
            camX -= diff;
        }
    }
        if(y + camY != centerY){
            if(y + camY < centerY) {
                float diff = centerY - (y + camY);
                camY += diff;
            }else{
                float diff = (y + camY) - centerY;
                camY -= diff;
            }
        }
    }


public void camera(float a, float b){

canvas.translate(a, b);
}

public void render() {
if(!GameActivity.stop){

if(!first) {
    centerX = GameActivity.center_x;
    centerY = GameActivity.center_y;
float x2 = x;
float y2 = y;
GameActivity.x = hero.getX();
GameActivity.y = hero.getY();
x = hero.getX();
y = hero.getY();
float dif_x = x2 - x;
float dif_y = y2 - y;
hero.camPosX += dif_x;
hero.camPosY += dif_y;
camX = hero.camPosX;
camY = hero.camPosY;
} else {
centerX = GameActivity.center_x;
centerY = GameActivity.center_y;
    x = hero.getX();
    y = hero.getY();
    camX = hero.camPosX;
    camY = hero.camPosY;
}

adjust();

canvas = holder.lockCanvas();


clearCanvas();

camera(camX, camY);

map.render(canvas, hero);
mobs.render(canvas, hero);

camera(-camX, -camY);
canvas.drawText("FPS: " + Integer.toString(fps1), GameActivity.screenSizeX - 200.0f, GameActivity.screenSizeY, pain);
canvas.drawText("Kill count: " + Integer.toString(Statistic.killCount), 600.0f, 50.0f, pain);
canvas.drawText("lvl: " + Integer.toString((int) hero.getLvl()), 50.0f, 300.0f, pain);
//canvas.drawText("" + Float.toString(x) + " | " + "Y_HERO: " + Float.toString(y), 250.0f, 500.0f, pain);
//    canvas.drawText("error1: " + Saver.error, 200.0f, 500.0f, pain);
//    canvas.drawText("error2: " + Saver.error2, 200.0f, 600.0f, pain);
//    canvas.drawText("error3: " + Saver.error3, 200.0f, 700.0f, pain);
//    canvas.drawText("error4: " + Saver.error4, 200.0f, 800.0f, pain);
//canvas.drawText("X_diff: " + Float.toString(camX + x) + " | " + "Y_diff: " +
       // Float.toString(camY + y), 250.0f, 400.0f, pain);

    //canvas.drawText("camX: " + Float.toString(camX) + " | " + "camY: " +
            //Float.toString(camY), 250.0f, 600.0f, pain);



ui.render(hero.getHP(), hero.getHT(), canvas);

hero.render(canvas, centerX, centerY, 0, 0, Texture.SPRITE_SIZE, Texture.SPRITE_SIZE, Texture.MULT_SIZE, false);


holder.unlockCanvasAndPost(canvas);
first = false;
}
}


    public void run() {

        fps = 0; // Счётчик фпс, будет увеличиваться до 60.
        upd = 0; // Счётчик обновления координат, должен быть примерно равен фпс.
        int updl = 0; // Счётчик задержки (Необязательно).

        long count = 0; // Просто для подсчёта времени в наносекундах.

        float delta = 0; // Для контроля цикла.

        long lastTime = Time.get(); // Отмеряем стартовое время.
        
        while (running) { 
            long now = Time.get(); // Инициализируем теперешнее время.
            long elapsedTime = now - lastTime; // Получаем разницу во времени (Между началом цикла и концом).
            lastTime = now; // Приравниваем стартовое время для последующих вычислений.

            count += elapsedTime; // Счётчик времени (Для определения что прошла секунда в цикле).

            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL); // Время, прошедшее за полную итерацию цикла делится на одну шестидесятую часть секунды.
            while (delta > 1) { // Когда проходит одна шестидесятая секунды дельта становится 1.
                update(); // Обновление координат текстур.
                upd++; // Счётчик обновлений.
                delta--; // Заново будем считать 1/60 секунды.
                if (render) { // Проверяем первый ли раз запустился этот цикл. 
                    updl++;
                } else {
                    render = true;
                }
            } //Если джвм долго читает верхний код, то предыдущий цикл будет повторяться несколько раз, что означает медленную работу, потерю фпс.
			// Другими словами чем больше становится дельта на момент начал цикла, тем труднее справляется машина и сильнее падает фпс.

            if (render) { // Запустится когда отлагает предыдущий цикл (Если больше чем одна итерация в прошлом цикле то это лаги).
                render(); // Отрисовка всех текстур.
                fps++; // Подсчёт фреймов за каждую отрисовку.
            } else {
                try {
                    mainThread.sleep(IDLE_TIME); // Если срабатывает else, то это значит, что цикл сбился и ждём 1 милисек чтобы привести его в порядок.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (count >= Time.SECOND) {
                fpsc = true;
               count();
                upd = 0;
                fps = 0; 
                updl = 0;
                count = 0;
                test++;
                if(test >= 5){
                    init(context_);
                    System.out.println(Saver.getFileSize());
                    test = 0;
                }
            }

        }

}

}
