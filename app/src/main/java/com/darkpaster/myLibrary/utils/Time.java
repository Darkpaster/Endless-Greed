package com.darkpaster.myLibrary.utils;

import java.io.Serializable;

public class Time implements Serializable {
    private static final long serialVersionUID = 9L;
    public static final long SECOND = 1000000000l;
    public static long time = 0;

    private long startTime = 0;
    private float cdTime = 0;
    private boolean started = false;


    public static long get() {
        return System.nanoTime();
    }

public boolean cd(float cd){
        if(started){
            if(pass()){
                started = false;
                startTime = 0;
                cdTime = 0;
                return true;
            }else{
                return false;
            }

        }else{
            started = true;
            cdTime = cd;
            startTime = System.currentTimeMillis();
            return false;
        }
}

public boolean pass(){
        if(System.currentTimeMillis() - startTime > cdTime * 1000){
            return true;
        }else{
            return false;
        }
}

public boolean getStarted(){
        return started;
}

}
