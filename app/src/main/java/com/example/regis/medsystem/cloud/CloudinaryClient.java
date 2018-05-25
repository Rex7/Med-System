package com.example.regis.medsystem.cloud;





import com.cloudinary.Cloudinary;
import com.cloudinary.android.MediaManager;

import java.io.IOException;
import java.util.Map;


public class CloudinaryClient {
    Cloudinary cloudinary=new Cloudinary(CloudinaryConf.getConfig());
    public String uploadImage(String path, String fileName){

           // return  cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
        return     MediaManager.get().upload(path)

                .unsigned("y8lrpnyg")
                .option("public_id",fileName)
                .dispatch();




    }
}
