package com.example.shopaholic.Adapters.FragmentCategory

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopaholic.Models.Categories.CategoryModel
import com.example.shopaholic.Models.Categories.CategoryModelResourse
import com.example.shopaholic.R
import com.mrusmansiddique.gpt_android.api.Retrofit
import com.mrusmansiddique.gpt_android.api.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Categories(var _context: Context) : Fragment() {

    // private lateinit var progressDialog: ProgressDialog
    lateinit var myview: View
    var categorieslist: RecyclerView? = null
    var categorieslistAdapter: Adapter_CategoryList? = null
    private lateinit var names: Array<String>
    lateinit var categorylist: ArrayList<CategoryModel>
    val webservice: WebService? get() = Retrofit.instance?.create(WebService::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_category, container, false)
        categorieslist = myview.findViewById<RecyclerView>(R.id.categorieslist)
        categorylist = ArrayList()

        getCategory()


        return myview
    }

    var getcategory: Call<CategoryModelResourse>? = null
    fun getCategory() {
        getcategory = webservice?.getCategory()

        getcategory?.enqueue(object : Callback<CategoryModelResourse> {
            override fun onResponse(
                call: Call<CategoryModelResourse>,
                response: Response<CategoryModelResourse>
            ) {

                if (!response.body()?.categoryList.isNullOrEmpty()) {
                    categorylist = response.body()?.categoryList!!

                    val linearLayoutManager = LinearLayoutManager(
                        _context,
                        LinearLayoutManager.VERTICAL, false
                    )
                    categorieslist!!.layoutManager = linearLayoutManager
                    categorieslistAdapter = Adapter_CategoryList(_context, categorylist)
                    categorieslist!!.itemAnimator = DefaultItemAnimator()
                    categorieslist!!.adapter = categorieslistAdapter

                }
            }

            override fun onFailure(call: Call<CategoryModelResourse>, t: Throwable) {
                Toast.makeText(_context, "Category Empty", Toast.LENGTH_SHORT)

            }

        })
    }


}