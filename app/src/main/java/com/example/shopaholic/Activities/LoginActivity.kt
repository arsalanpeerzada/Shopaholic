package com.example.shopaholic.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.shopaholic.Fragments.Login.login
import com.example.shopaholic.R

class LoginActivity : AppCompatActivity() {

    val transaction = supportFragmentManager.beginTransaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loadFragment(login())

    }
    private fun loadFragment(fragment: Fragment) {

        transaction.replace(R.id.backgroundLayout, fragment)
        transaction.addToBackStack("loginActivity")
        transaction.commit()
    }

    override fun onBackPressed() {
        myBackPressed()

    }

    private fun myBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack("loginActivity", 0)
    }
}