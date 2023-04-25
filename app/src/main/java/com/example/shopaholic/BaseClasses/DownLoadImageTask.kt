package com.example.shopaholic.BaseClasses

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL


public class DownLoadImageTask(var imageView: ImageView) :
    AsyncTask<String?, Void?, Bitmap?>() {

    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }

    public override fun doInBackground(vararg params: String?): Bitmap? {
        val urlOfImage = params[0]
        var logo: Bitmap? = null
        try {
            val `is` = URL(urlOfImage).openStream()
            /*
                decodeStream(InputStream is)
                    Decode an input stream into a bitmap.
             */logo = BitmapFactory.decodeStream(`is`)
        } catch (e: java.lang.Exception) { // Catch the download exception
            e.printStackTrace()
        }
        return logo
    }
}
