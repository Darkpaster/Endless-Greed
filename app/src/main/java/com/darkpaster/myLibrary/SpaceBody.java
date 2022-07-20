package com.darkpaster.myLibrary;

import com.darkpaster.pixellife.actors.hero.Hero;

import java.io.Serializable;

public abstract class SpaceBody implements Serializable {
  private static final long serialVersionUID = 2L;

  protected float x;
  protected float y;
  protected float speed = 2.0f;
  protected float maxSpeed = 4.0f;

  public abstract void update(Hero hero);

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setX(float x) {
    this.x = x;
  }
  public void setXY(float x, float y){
    this.x = x;
    this.y = y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }
  public void setMaxSpeed(float speed) {
    this.maxSpeed = speed;
  }

  public float getSpeed() {
    return speed;
  }
  public float getMaxSpeed(){return maxSpeed;}
}
