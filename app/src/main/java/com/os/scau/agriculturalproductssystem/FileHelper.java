package com.os.scau.agriculturalproductssystem;

import java.io.File;

/**
 * Created by Liaowh on 2016/7/13.
 */
public class FileHelper {
    public static File createDirectory(String filePath){
       if(null == filePath){
         return null;
        }
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }
}
