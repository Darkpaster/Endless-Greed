package com.darkpaster.myLibrary;

public abstract class SpaceBody {

  protected float x;
  protected float y;
  protected float speed = 2.0f;
  protected float maxSpeed = 3.0f;

  public abstract void update();

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
