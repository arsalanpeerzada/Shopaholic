package com.example.shopaholic.Activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.eightbitlab.bottomnavigationbar.BottomBarItem
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar
import com.example.shopaholic.Adapters.FragmentCategory.Categories
import com.example.shopaholic.Fragments.BottomBar.Cart
import com.example.shopaholic.Fragments.BottomBar.Deals
import com.example.shopaholic.Fragments.BottomBar.Home
import com.example.shopaholic.Fragments.BottomBar.More
import com.example.shopaholic.R
import com.google.android.material.navigation.NavigationView
import com.mukesh.tinydb.TinyDB

class HomeActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var tinyDB: TinyDB
    lateinit var menu: ImageView
    lateinit var bottomNavigationBar: BottomNavigationBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNavigationBar = findViewById<View>(R.id.bottom_bar) as BottomNavigationBar
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        menu = findViewById(R.id.menu)
        tinyDB = TinyDB(this@HomeActivity)


        setUpNavigationView()

        val item1 = BottomBarItem(R.drawable.home, R.string.home)
        val item2 = BottomBarItem(R.drawable.categories, R.string.categories)
        val item3 = BottomBarItem(R.drawable.deals, R.string.deals)
        val item4 = BottomBarItem(R.drawable.cart, R.string.cart)
        val item5 = BottomBarItem(R.drawable.others, R.string.others)

        bottomNavigationBar.addTab(item1)
        bottomNavigationBar.addTab(item2)
        bottomNavigationBar.addTab(item3)
        bottomNavigationBar.addTab(item4)
        bottomNavigationBar.addTab(item5)

        bottomNavigationBar.setOnSelectListener { position ->
            when (position) {
                0 -> openFragment(Home(this@HomeActivity))
                1 -> openFragment(Categories(this@HomeActivity))
                2 -> openFragment(Deals(this@HomeActivity))
                3 -> openFragment(Cart(this@HomeActivity))
                4 -> openFragment(More(this@HomeActivity))
            }
        }

        drawerLayout.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        menu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        openFragment(Home(this@HomeActivity))
    }

    private fun setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    openFragment(Home(this@HomeActivity))
                    bottomNavigationBar.selectTab(0, true)
                }
                R.id.nav_cateogry -> {
                    openFragment(Categories(this@HomeActivity))
                    bottomNavigationBar.selectTab(1, true)
                }
                R.id.Deals -> {
                    openFragment(Deals(this@HomeActivity))
                    bottomNavigationBar.selectTab(2, true)
                }
                R.id.Cart -> {
                    openFragment(Cart(this@HomeActivity))
                    bottomNavigationBar.selectTab(3, true)
                }
                R.id.More -> {
                    openFragment(More(this@HomeActivity))
                    bottomNavigationBar.selectTab(4, true)
                }

                else -> {
                    drawerLayout.closeDrawers()
                }
            }
            true
        }
    }

    fun openFragment(fragment: Fragment?) {

        val transaction = supportFragmentManager
        transaction.beginTransaction()
            .replace(R.id.bottomnav_framelayout, fragment!!)
            .commit()
    }

    override fun onBackPressed() {

    }
}