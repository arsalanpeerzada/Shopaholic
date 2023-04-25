package com.example.shopaholic.Fragments.BottomBar

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopaholic.Adapters.FragmentHome.Adapter_newArrivals
import com.example.shopaholic.Models.HomeScreen.Array_Get_Dis_ProductModel
import com.example.shopaholic.Models.HomeScreen.Get_Dis_ProductModel
import com.example.shopaholic.R
import com.mrusmansiddique.gpt_android.api.Retrofit
import com.mrusmansiddique.gpt_android.api.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Deals(var _context: Context) : Fragment() {
 //   private lateinit var progressDialog: ProgressDialog
    lateinit var myview: View
    lateinit var rv_deals: RecyclerView
    lateinit var adapterNewarrivals: Adapter_newArrivals
    val webservice: WebService? get() = Retrofit.instance?.create(WebService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_deals, container, false)
        rv_deals = myview.findViewById(R.id.rv_Deals)
        init()
        get_DiscountedProducts()



        return myview
    }

    private fun init() {


    }

    var discount_Products: Call<Array_Get_Dis_ProductModel>? = null
    fun get_DiscountedProducts() {
        discount_Products = webservice?.get_discount_product()
        discount_Products?.enqueue(object : Callback<Array_Get_Dis_ProductModel> {
            override fun onResponse(
                call: Call<Array_Get_Dis_ProductModel>,
                response: Response<Array_Get_Dis_ProductModel>
            ) {
                if (response.isSuccessful) {

                    var dis_Products: ArrayList<Get_Dis_ProductModel> =
                        response.body()?.get_discount_product!!
                    adapterNewarrivals = Adapter_newArrivals(dis_Products,_context)

                    rv_deals.layoutManager = GridLayoutManager(_context, 2)
                    rv_deals.adapter = adapterNewarrivals
                } else {

                    Toast.makeText(_context, "No Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Array_Get_Dis_ProductModel>, t: Throwable) {

            }

        })
    }


}