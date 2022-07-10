package com.darkpaster.pixellife.actors.mobs;

public enum MobType {
NONE(0), RABBIT(1);

private int n;
MobType(int n){
this.n = n;
}

public int getN(){return n;}

public MobType getMobType(int n){
switch (n){
case 1:
return RABBIT;
default:
return NONE;


}
}
}
