package com.darkpaster.myLibrary.textures;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.IOException;
import java.io.InputStream;

public class TextureAtlas {

  private Bitmap bitmap;
  private Bitmap sourse;
  private Bitmap bm;
  private Matrix matrix;
  private String png;
  private Context context;
  private InputStream ims;

  private float width;
  private float height;

  public TextureAtlas(Context context, String file) {
    this.context = context;
    sourse = loadImageFromAsset(file);
  }

  public Bitmap loadImageFromAsset(String file) {
    try {
      ims = context.getAssets().open(file);
      bm = BitmapFactory.decodeStream(ims);

    } catch (IOException ex) {
      return null;
    }
    return bm;
  }

  public Bitmap cut(int x, int y, int w, int h) {
    bitmap = Bitmap.createBitmap(sourse, x, y, w, h);
    return bitmap;
  }

  public Bitmap cut(int x, int y, int w, int h, int mul, boolean b) {
    matrix = new Matrix();
    width = (float) sourse.getWidth();
    height = (float) sourse.getHeight();
    matrix.postScale(mul, mul);
    bitmap = Bitmap.createBitmap(sourse, x, y, w, h, matrix, b);
    return bitmap;
  }

  public void bitmapRecycle() {
    bitmap = null;
    matrix = null;
  }
}
