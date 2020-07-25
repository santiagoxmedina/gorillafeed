package com.sanmed.gorillabook.view.binding;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;

public class BindingAdapter {

    @androidx.databinding.BindingAdapter("setImage")
    public void setImage(ImageView imageView,String imageUrl){
        File imgFile;
        imgFile = new  File(imageUrl);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);

        }
    }
}
