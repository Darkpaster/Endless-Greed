package com.darkpaster.myLibrary.textures;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Sprite {
  private Bitmap bitmap;
  private Paint paint;

  public Sprite(Paint paint, Bitmap bitmap) {
    this.paint = paint;
    this.bitmap = bitmap;
  }

  public void draw(float x, float y, Canvas canvas) {
    canvas.drawBitmap(bitmap, x, y, paint);
  }
}
