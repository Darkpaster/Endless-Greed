package com.darkpaster.myLibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import com.darkpaster.pixellife.GameActivity;
import com.darkpaster.pixellife.actors.Actor;
import com.darkpaster.pixellife.actors.Generator;
import com.darkpaster.pixellife.actors.hero.Hero;
import com.darkpaster.pixellife.actors.mobs.Mob;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Saver {
    @SuppressLint("SdCardPath")
    private static String path = "/data/user/0/com.darkpaster.pixellife/files/";
    private static final String gameData = "gameData.dat";
    private static final String deletedFile = "gameData.txt";
    public static String error = "";
    public static String error2 = "";
    public static String error3 = "";
    public static String error4 = "";


    public static void saveData(Bundle bundle) throws FileNotFoundException{
//        File deleted = new File(path + deletedFile);
//        if(deleted.exists()){
//            if(deleted.delete()){
//                System.out.println("deleted");
//            }else{
//                System.out.println("not deleted");
//            }
//        }
        //System.out.println(context.getFilesDir());
        if(bundle.isEmpty()){
            System.out.println("empty bundle");
        }

        System.out.println("Bundle size: " + bundle.size());
        File file = new File(path + gameData);

        System.out.println(bundle);

//        if(!file.exists()) {
//            try{
//                file.createNewFile();
//                System.out.println("successfully");
//            }catch (IOException e){
//                e.printStackTrace();
//                error2 = e.toString();
//            }
//        }else{
//            //System.out.println("created already: " + file.getName());
//        }

        HashMap<String, Object> data = new HashMap<>();
        data.put(GameActivity.HERO_KEY, bundle.getSerializable(GameActivity.HERO_KEY));
        data.put(GameActivity.MOBS_KEY, bundle.getSerializable(GameActivity.MOBS_KEY));
        System.out.println("Saving HashMap length: " + data.size());
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
            writer.writeObject(data);
            writer.flush();
            writer.close();
            //System.out.println("successfully");
        }catch(IOException e){
            e.printStackTrace();
            error3 = e.toString();
        }
        if(file.length() == 0){
            System.out.println("Empty file after saving");
        }

        if(file.length() < 20){
            System.out.println(data);
            //System.out.println(data.size());
            System.out.println(data.get(GameActivity.HERO_KEY));
            System.out.println(data.get(GameActivity.MOBS_KEY));
        }

        }

    public static HashMap<String, Object> loadData() throws ClassNotFoundException{
        File file = new File(path + gameData);
        HashMap<String, Object> data = null;
        if(!file.exists()) {
            System.out.println("file doesnt exist");
            return data;
        }
        if(file.length() == 0){
            System.out.println("Empty file during loading");
        }

                try{
                    //System.out.println(file.length());
                    ObjectInputStream reader = new ObjectInputStream(new  FileInputStream(file));
                    //Object test = reader.readObject();
                    //System.out.println(test);
                    data = (HashMap) reader.readObject();
                    reader.close();
                    //reader.close();
                }catch (IOException e){
                    //GameActivity.stop = true;
                    e.printStackTrace();
                    e.getCause();
                    e.getLocalizedMessage();
                    error = e.toString();
                }
        System.out.println("File size during loading: " + getFileSize());


        return data;
    }

    public static boolean checkGameState(){
        File file = new File(path + gameData);
        if(file.exists() && file.length() != 0) {
            return true;
        }else{
            System.out.println("File exists: " + file.exists());
            System.out.println(file.length());
            return false;
        }
    }

    public static long getFileSize(){
        File file = new File(path + gameData);
        return file.length();
    }

    public static void setPath(String p){
        path = p;
    }

    public static boolean delete(){
        File file = new File(path + gameData);
        if(file.exists()){
            return file.delete();
        }else{
            return true;
        }
    }



}
