package com.example.shopaholic.Fragments.BottomBar

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopaholic.Adapters.FragmentCategory.Adapter_Category_more
import com.example.shopaholic.Models.Categories.ProductModel
import com.example.shopaholic.R

class CategoryListScreen(
    var _productlist: ArrayList<ProductModel>,
    var _productId: Int,
    var _context: Context
) : Fragment() {

    lateinit var myview: View
    lateinit var categorylist: RecyclerView
    lateinit var adapter_Category_Product: Adapter_Category_more


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_category_list_screen, container, false)
        categorylist = myview.findViewById(R.id.categorylist)
        adapter_Category_Product =
            Adapter_Category_more(_productlist, _productId, _context)



        categorylist.layoutManager =
            LinearLayoutManager(_context, RecyclerView.VERTICAL, false)
        categorylist.adapter = adapter_Category_Product



        return myview
    }




}