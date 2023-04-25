package com.example.shopaholic.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.shopaholic.R
import com.mukesh.tinydb.TinyDB

class Splash : AppCompatActivity() {

    lateinit var tinyDB: TinyDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tinyDB = TinyDB(this@Splash)

        val handler = Handler()
        handler.postDelayed({
            var userid = tinyDB.getString("userid")
            if (userid.isNullOrEmpty()) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            this.finish()
        }, 3000)
    }
}