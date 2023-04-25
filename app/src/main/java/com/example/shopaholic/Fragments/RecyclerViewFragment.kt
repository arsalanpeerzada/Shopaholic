package com.example.shopaholic.Fragments

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopaholic.Adapters.FragmentCategory.Adapter_Category_Product
import com.example.shopaholic.Models.Categories.ProductModel
import com.example.shopaholic.Models.Categories.ProductModelResource
import com.example.shopaholic.Models.Categories.SubCategoryModel
import com.example.shopaholic.Models.Categories.SubCategoryModelResourse
import com.example.shopaholic.R
import com.mrusmansiddique.gpt_android.api.Retrofit
import com.mrusmansiddique.gpt_android.api.WebService
import net.cachapa.expandablelayout.ExpandableLayout
import net.cachapa.expandablelayout.ExpandableLayout.OnExpansionUpdateListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerViewFragment(contentLayoutId: Int, _context: Context) :
    Fragment(contentLayoutId) {


    val webservice: WebService? get() = Retrofit.instance?.create(WebService::class.java)
    public lateinit var subcategoryList: ArrayList<SubCategoryModel>
    public lateinit var productList: ArrayList<ProductModel>
    public lateinit var recyclerView: RecyclerView

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_sub_category, container, false)


        recyclerView = rootView.findViewById(R.id.rv_expandableRecyclerview)

        subcategoryList = ArrayList()
        productList = ArrayList()

        getSubcategory(rootView.context)


        return rootView
    }

    private class SimpleAdapter(
        private val recyclerView: RecyclerView,
        var sub: ArrayList<SubCategoryModel>,
        var pro: ArrayList<ProductModel>,
        var _context: Context

    ) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

        private var selectedItem = UNSELECTED
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_list, parent, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(sub, pro, _context)
        }

        override fun getItemCount(): Int {
            return sub.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener, OnExpansionUpdateListener {
            private val expandableLayout: ExpandableLayout
            private val expandButton: TextView
            private lateinit var parentRecycler: RecyclerView
            var productlist: ArrayList<ProductModel> = ArrayList()
            fun bind(
                sub: ArrayList<SubCategoryModel>,
                pro: ArrayList<ProductModel>,
                _context: Context
            ) {
                val position = adapterPosition
                val isSelected = position == selectedItem
                expandButton.text = sub.get(position).category_Name
                expandButton.isSelected = isSelected
                expandableLayout.setExpanded(isSelected, false)
                productlist = pro


                expandableLayout.setOnClickListener {

                }
            }

            override fun onClick(view: View) {
                val holder =
                    recyclerView.findViewHolderForAdapterPosition(selectedItem) as ViewHolder?

                if (holder != null) {
                    holder.expandButton.isSelected = false
                    holder.expandableLayout.collapse()
                }
                val position = adapterPosition

                if (position == selectedItem) {
                    selectedItem = UNSELECTED
                } else {
                    expandButton.isSelected = true
                    expandableLayout.expand()
                    selectedItem = position

                    expandButton.setTextColor(
                        ContextCompat.getColor(
                            _context,
                            R.color.red
                        )
                    );

                    expandButton.setTypeface(null, Typeface.BOLD);


                    var adapterCategoryProduct: Adapter_Category_Product
                    adapterCategoryProduct = Adapter_Category_Product(pro, position, _context)

                    parentRecycler.layoutManager = GridLayoutManager(_context, 4)
                    parentRecycler.adapter = adapterCategoryProduct
                }
            }

            override fun onExpansionUpdate(expansionFraction: Float, state: Int) {
                Log.d("ExpandableLayout", "State: $state")
                if (state == ExpandableLayout.State.EXPANDING) {
                    recyclerView.smoothScrollToPosition(adapterPosition)
                }
            }

            init {
                expandableLayout = itemView.findViewById(R.id.expandable_layout)
                expandableLayout.setInterpolator(OvershootInterpolator())
                expandableLayout.setOnExpansionUpdateListener(this)
                expandButton = itemView.findViewById(R.id.expand_button)
                parentRecycler = itemView.findViewById(R.id.childRecyclerView)
                expandButton.setOnClickListener(this)
            }
        }

        companion object {
            private const val UNSELECTED = -1
        }
    }

    var getsubcategory: Call<SubCategoryModelResourse>? = null
    fun getSubcategory(_context: Context) {
        getsubcategory = webservice?.get_SubCategory()
        getsubcategory?.enqueue(object : Callback<SubCategoryModelResourse> {
            override fun onResponse(
                call: Call<SubCategoryModelResourse>,
                response: Response<SubCategoryModelResourse>
            ) {
                try {
                    subcategoryList = response.body()?.getsubCategoryModel!!
                    getProduct(_context, subcategoryList)
                } catch (nullException: NullPointerException) {

                }
            }

            override fun onFailure(call: Call<SubCategoryModelResourse>, t: Throwable) {
                if (_context != null) {
                    Toast.makeText(_context, "No Data", Toast.LENGTH_SHORT)
                }
            }

        })
    }

    var getproduct: Call<ProductModelResource>? = null
    fun getProduct(_context: Context, sub: ArrayList<SubCategoryModel>) {
        getproduct = webservice?.get_Product()
        getproduct?.enqueue(object : Callback<ProductModelResource> {
            override fun onResponse(
                call: Call<ProductModelResource>,
                response: Response<ProductModelResource>
            ) {
                if (response.isSuccessful) {

                    productList = response.body()?.productList!!
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.setAdapter(
                        SimpleAdapter(
                            recyclerView,
                            sub,
                            productList,
                            _context

                        )
                    )

                } else {
                    if (_context != null) {
                        Toast.makeText(_context, "No Data", Toast.LENGTH_SHORT)
                    }

                }
            }

            override fun onFailure(call: Call<ProductModelResource>, t: Throwable) {
                if (_context != null) {
                    Toast.makeText(_context, "No Data", Toast.LENGTH_SHORT)
                }
            }

        })

    }

}