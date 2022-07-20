package com.darkpaster.myLibrary;

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
    private static final String path = "/data/user/0/com.darkpaster.pixellife/files/";
    private static final String gameData = "gameData.dat";


    public static void saveData(Bundle bundle) throws FileNotFoundException{
        //System.out.println(context.getFilesDir());
        File file = new File(path + gameData);
        if(!file.exists()) {
            try{
                file.createNewFile();
                System.out.println("successfully");
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("created already: " + file.getName());
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put(GameActivity.HERO_KEY, bundle.getSerializable(GameActivity.HERO_KEY));
        data.put(GameActivity.MOBS_KEY, bundle.getSerializable(GameActivity.MOBS_KEY));
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
            writer.writeObject(data);
            writer.close();
            System.out.println("successfully");
        }catch(IOException e){
            e.printStackTrace();
        }
        }

    public static HashMap<String, Object> loadData() throws ClassNotFoundException{
        File file = new File(path + gameData);
        HashMap<String, Object> data = new HashMap<>();
        if(file.exists()){
            try{
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file));
                data = (HashMap<String, Object>) reader.readObject();
                reader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("save file doesnt exist");
        }

        return data;
    }



}
