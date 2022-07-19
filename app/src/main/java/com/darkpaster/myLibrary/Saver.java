package com.darkpaster.myLibrary;

import com.darkpaster.pixellife.actors.Actor;

import java.io.*;

public class Saver {

    protected void saveData(Actor actor){
        try {
            ObjectOutputStream sad = new ObjectOutputStream(new FileOutputStream("src/main/assets/gameData.dat"));
            sad.writeObject(actor);
        }catch(IOException e){
            e.printStackTrace();
        }
        }

    protected void loadData(){

    }


//    private String serializeBundle(final Bundle bundle) {
//        String base64 = null;
//        final Parcel parcel = Parcel.obtain();
//        try {
//            parcel.writeBundle(bundle);
//            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            final GZIPOutputStream zos = new GZIPOutputStream(new BufferedOutputStream(bos));
//            zos.write(parcel.marshall());
//            zos.close();
//            base64 = Base64.encodeToString(bos.toByteArray(), 0);
//        } catch(IOException e) {
//            e.printStackTrace();
//            base64 = null;
//        } finally {
//            parcel.recycle();
//        }
//        return base64;
//    }
//
//    private Bundle deserializeBundle(final String base64) {
//        Bundle bundle = null;
//        final Parcel parcel = Parcel.obtain();
//        try {
//            final ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
//            final byte[] buffer = new byte[1024];
//            final GZIPInputStream zis = new GZIPInputStream(new ByteArrayInputStream(Base64.decode(base64, 0)));
//            int len = 0;
//            while ((len = zis.read(buffer)) != -1) {
//                byteBuffer.write(buffer, 0, len);
//            }
//            zis.close();
//            parcel.unmarshall(byteBuffer.toByteArray(), 0, byteBuffer.size());
//            parcel.setDataPosition(0);
//            bundle = parcel.readBundle();
//        } catch (IOException e) {
//            e.printStackTrace();
//            bundle = null;
//        }  finally {
//            parcel.recycle();
//        }
//
//        return bundle;
//    }

}
