package com.darkpaster.pixellife.actors.mobs;

public enum MobType {
NONE(0), RABBIT(1), CHICKEN(2);

private int n;
MobType(int n){
this.n = n;
}

public int getN(){return n;}

public static MobType getMobType(int n){
switch (n){
case 1:
return RABBIT;
    case 2:
        return CHICKEN;
default:
return NONE;


}
}
}
