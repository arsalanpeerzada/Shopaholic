package com.example.shopaholic.Fragments.Login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shopaholic.Activities.HomeActivity
import com.example.shopaholic.R


class Password : Fragment() {

    lateinit var myview: View
    lateinit var proceed: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_password, container, false)
        proceed = myview.findViewById(R.id.proceed)

        proceed.setOnClickListener {
            requireContext().startActivity(
                Intent(
                    requireContext(),
                    HomeActivity::class.java
                ).putExtra("key", "1")
            )
            Toast.makeText(requireContext(), "Successfull", Toast.LENGTH_SHORT).show()
            requireActivity().finish()
        }
        return myview

    }
}