package com.darkpaster.myLibrary.textures;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.darkpaster.pixellife.Game;

import static android.os.Environment.getExternalStorageState;

public class Texture extends SurfaceView implements SurfaceHolder.Callback {
  public static final int SPRITE_SIZE = 16;
  public static final int MULT_SIZE = 6;
  public static final int TOTAL_SIZE = SPRITE_SIZE * MULT_SIZE;

  private SurfaceHolder surfaceHolder;
  private Bitmap bitmapSourse;
  private Bitmap bitmap;
  private int bitmapId;
  public Canvas cnv;
  private Context context;

  private Paint paint; // = new Paint(Paint.ANTI_ALIAS_FLAG);
  public Game gameThread;
  private Sprite sprite;

  public Texture(Context context) {
    super(context);
    getHolder().addCallback(this);
    paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(Color.WHITE);
    paint.setTextSize(50.0f);
    this.context = context;
    surfaceHolder = getHolder();
    System.out.println("Каталог, в который ваше приложение должно записывать файлы: " + context.getFilesDir());
    //System.out.println("Если вы хотите записать файл в память телефона, используйте: " + context.openFileOutput());
    System.out.println("Если вам нужен путь к SDCard, используйте: " + Environment.getExternalStorageDirectory());
    System.out.println("Если вы собираетесь записывать на SDCard, вам также необходимо проверить ее состояние с помощью: "
            + getExternalStorageState());
    //Если вы храните небольшие файлы для своего приложения, они могут попасть в память телефона,
    // а не на SD-карту, поэтому используйте методы Context.openFileOutput()и .Context.openFileInput()
    //OutputStream os = openFileOutput("samplefile.txt", MODE_PRIVATE);
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    // Ui ui = new Ui(context);
    gameThread = new Game(getHolder(), context, cnv, paint);

    gameThread.setRunning(true);
    gameThread.start();
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
destroy();
    boolean retry = true;
    // завершаем работу потока
    gameThread.setRunning(false);
    while (retry) {
      try {
        gameThread.mainThread.join();
        retry = false;
      } catch (InterruptedException e) {
        // если не получилось, то будем пытаться еще и еще
      }
    }
  }

  public void destroy() {
    boolean retry = true;
    // завершаем работу потока
    gameThread.setRunning(false);
    while (retry) {
      try {
        gameThread.mainThread.join();
        retry = false;
      } catch (InterruptedException e) {
        // если не получилось, то будем пытаться еще и еще
      }
    }
  }


}
