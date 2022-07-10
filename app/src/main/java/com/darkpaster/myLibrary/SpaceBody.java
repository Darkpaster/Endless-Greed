package com.darkpaster.myLibrary;

public class SpaceBody {

  protected float x;
  protected float y;
  protected float speed = 6.0f;

  public void update() {}

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public float getSpeed() {
    return speed;
  }
}
