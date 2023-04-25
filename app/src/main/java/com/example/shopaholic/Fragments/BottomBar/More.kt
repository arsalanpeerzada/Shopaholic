package com.example.shopaholic.Fragments.BottomBar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.shopaholic.Activities.LoginActivity
import com.example.shopaholic.Activities.MoreActivities.AboutUs
import com.example.shopaholic.Activities.MoreActivities.BuyAgain
import com.example.shopaholic.Activities.MoreActivities.ContactUs
import com.example.shopaholic.R
import com.mukesh.tinydb.TinyDB

class More(var _context: Context) : Fragment() {

    lateinit var myview: View
    lateinit var buyAgain: LinearLayout
    lateinit var contactUs: LinearLayout
    lateinit var aboutus: LinearLayout
    lateinit var Logout: LinearLayout
    lateinit var tinyDB: TinyDB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_more, container, false)

        buyAgain = myview.findViewById(R.id.buyagain)
        contactUs = myview.findViewById(R.id.contactUs)
        aboutus = myview.findViewById(R.id.aboutUs)
        Logout = myview.findViewById(R.id.logout)
        tinyDB = TinyDB(_context)


        buyAgain.setOnClickListener {
            _context.startActivity(Intent(_context, BuyAgain::class.java))
        }

        contactUs.setOnClickListener {
            _context.startActivity(Intent(_context, ContactUs::class.java))
        }

        aboutus.setOnClickListener {
            _context.startActivity(Intent(_context, AboutUs::class.java))
        }
        Logout.setOnClickListener {
            val builder = AlertDialog.Builder(_context)
            //set title for alert dialog
            builder.setTitle(R.string.dialogTitle)
            //set message for alert dialog
            builder.setMessage(R.string.dialogMessage)
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes") { dialogInterface, which ->
                tinyDB.clear()
                startActivity(Intent(_context, LoginActivity::class.java))
            }
            //performing cancel action
            builder.setNeutralButton("Cancel") { dialogInterface, which ->

            }
            //performing negative action
            builder.setNegativeButton("No") { dialogInterface, which ->

            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

        return myview
    }


}