package com.example.regis.medsystem.cloud;





import android.util.Log;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;


public class CloudinaryClient {
    Cloudinary cloudinary=new Cloudinary(CloudinaryConf.getConfig());
    public String uploadImage(String path, String fileName){
        Log.v("public id ","FileName"+fileName);

           // return  cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
        return     MediaManager.get().upload(path)

                .unsigned("y8lrpnyg")
                .option("public_id",fileName.trim())
                .dispatch();




    }
    public String getImage(String name){
        return MediaManager.get().url().generate(name);
    }
}
