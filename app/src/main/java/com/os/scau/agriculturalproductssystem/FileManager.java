package com.os.scau.agriculturalproductssystem;

import android.os.Environment;

/**
 * Created by Liaowh on 2016/7/9.
 */
public class FileManager {
    /**
     * 保存文件的根目录
     */
    public final static String PATH_ROOT = "AgriculturalProductsSystem";
    /**
     * 保存图片的缓存目录
     */
    public final static String PATH_CACHE = "Cache";

    public static String getSaveFilePath(String filePath){
        String dirPath = "";
        if(hasExternalSDCard()){
            dirPath = getRootFilePath() + PATH_ROOT +"/"+filePath;
        }
        return dirPath;
    }
     public static boolean hasExternalSDCard(){
         String status =  Environment.getExternalStorageState();
         if(!status.equals(Environment.MEDIA_MOUNTED)){
             return false;
         }
         return true;
     }
    public static String getRootFilePath(){
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    }
}

