package com.example.shopaholic.Fragments.BottomBar

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopaholic.Adapters.Adapter_Cart
import com.example.shopaholic.DB.DBHelper
import com.example.shopaholic.Interface.CartInterface
import com.example.shopaholic.Models.Categories.ProductModel
import com.example.shopaholic.Models.Categories.prot
import com.example.shopaholic.R
import com.mrusmansiddique.gpt_android.api.Retrofit
import com.mrusmansiddique.gpt_android.api.WebService
import com.mukesh.tinydb.TinyDB


class Cart(var _context: Context) : Fragment() {

    private lateinit var progressDialog: ProgressDialog
    lateinit var adapter_Cart: Adapter_Cart
    lateinit var rv_cart: RecyclerView
    lateinit var myview: View
    public lateinit var productList: ArrayList<ProductModel>
    lateinit var tinyDB: TinyDB
    val webservice: WebService? get() = Retrofit.instance?.create(WebService::class.java)
    lateinit var data: ArrayList<prot>
    lateinit var dbHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_cart, container, false)
        rv_cart = myview.findViewById(R.id.rv_cart)



        dbHelper = DBHelper(context)
        data = dbHelper.getProt()


        adapter_Cart = Adapter_Cart(data, _context, iCart)
        rv_cart.layoutManager =
            LinearLayoutManager(_context, RecyclerView.VERTICAL, false)
        rv_cart.adapter = adapter_Cart
        // ViewCompat.setNestedScrollingEnabled(rv_cart, false);

        return myview
    }


    val iCart = object : CartInterface {
        override fun update(
            pID: String,
            pName: String,
            pPrice: String,
            pQuantity: String,
            pImage: String
        ) {
            dbHelper.update(
                prot(
                    pID,
                    pName,
                    pPrice,
                    pImage,
                    pQuantity.toInt(),
                    0
                )
            )
        }
    }



}