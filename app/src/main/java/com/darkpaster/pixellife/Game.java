package com.darkpaster.pixellife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import com.darkpaster.myLibrary.textures.Texture;
import com.darkpaster.myLibrary.utils.Time;
import com.darkpaster.pixellife.actors.Generator;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.actors.mobs.Rabbit;
import com.darkpaster.pixellife.level.TileMap;
import com.darkpaster.pixellife.ui.Ui;

import static android.graphics.Color.WHITE;

public class Game implements Runnable{

public static final int WIDTH = TileMap.WORLD_SIZE_X;
public static final int HEIGHT = TileMap.WORLD_SIZE_Y;


public static final float UPDATE_RATE = 60.0f; // Фпс
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE; //Секунду делим на 60 (Результат в наносекундах).
    public static final long IDLE_TIME = 1; // Задержка потока в 1 миллисекунду
    private int fps;
    private int upd;
    private int fps1;
    private int upd1;
    private boolean fpsc = false;


public Thread mainThread;
private Canvas canvas;
private SurfaceHolder holder;
private boolean running = false;
private Paint paint;
private Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
private Paint pain = new Paint(Paint.ANTI_ALIAS_FLAG);
public Hero hero;
public Rabbit rabbit;
private TileMap map;
private boolean first = true;
private Ui ui;
private Generator mobs;

public static float x = 0.0f;
public static float y = 0.0f;
private float camX = 0.0f;
private float camY = 0.0f;
public static float center1 = 0.0f;
public static float center2 = 0.0f;



public Game(SurfaceHolder holder, Context context, Canvas canvas, Paint paint) {
this.canvas = canvas;
this.holder = holder;
this.paint = paint;
mainThread = new Thread(this);
map = new TileMap(context, paint);

hero = new Hero(context, "char.png", paint);
Hero.X = GameActivity.x;
Hero.Y = GameActivity.y;
x = Hero.X;
y = Hero.Y;
mobs = new Generator(context, paint, canvas);

ui = new Ui(context);

pain.setTextSize(50.0f);
pain.setColor(WHITE);
}

public void start() {
mainThread.start();
}

public void setRunning(boolean run) {
this.running = run; }

private void update(){
if(!GameActivity.stop){
hero.update();
mobs.update();
mobs.spawn();

}
}

public void clearCanvas(){canvas.drawARGB(255, 0, 0, 0); }

public void count() {if (fpsc) { fps1 = fps; upd1 = upd;
fpsc = false;
}}


public void camera(float a, float b){
canvas.translate(a, b);
}

public void render() {
if(!GameActivity.stop){
if(!first) {
float x2 = x;
float y2 = y;
GameActivity.x = Hero.X;
GameActivity.y = Hero.Y;
x = Hero.X;
y = Hero.Y;
float dif_x = x2 - x;
float dif_y = y2 - y;
GameActivity.camPosX += dif_x;
GameActivity.camPosY += dif_y;
camX = GameActivity.camPosX;
camY = GameActivity.camPosY;
} else {
center1 = GameActivity.center_x;
center2 = GameActivity.center_y;
x = Hero.X;
y = Hero.Y;
}
canvas = holder.lockCanvas();


clearCanvas();

camera(camX, camY);

map.render(canvas);
mobs.render(canvas);

camera(-camX, -camY);
canvas.drawText("FPS: " + Integer.toString(fps1), GameActivity.screenSizeX - 200.0f, GameActivity.screenSizeY, pain);
canvas.drawText("Kill count: " + Integer.toString(Statistic.killCount), 500.0f, 50.0f, pain);
canvas.drawText("lvl: " + Integer.toString((int) Hero.lvl), 50.0f, 200.0f, pain);
//canvas.drawText("X_HERO: " + Float.toString(x) + " | " + "Y_HERO: " + Float.toString(y), 200.0f, 300.0f, paint);



ui.render(Hero.HP, Hero.HT, canvas);

hero.render(canvas, center1, center2, 0, 0, Texture.SPRITE_SIZE, Texture.SPRITE_SIZE, Texture.MULT_SIZE, false);


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
            }

        }

}

}
