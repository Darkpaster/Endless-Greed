package com.darkpaster.myLibrary.utils;

public class Time {
    public static final long SECOND = 1000000000l;
    public static long time = 0;

    public static long get() {
        return System.nanoTime();
    }

//    public static synchronized boolean spend(float seconds){
//        if(seconds == 0){return true;}
//        if(time != 0) {
//            return false;
//        }
//        if(time - System.currentTimeMillis() < seconds * 1000){
//
//        }
//        time = System.currentTimeMillis();
//
//    }
}
