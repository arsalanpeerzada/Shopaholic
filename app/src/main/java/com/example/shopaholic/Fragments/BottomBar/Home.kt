package com.example.shopaholic.Fragments.BottomBar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopaholic.Activities.ProductActivity
import com.example.shopaholic.Adapters.FragmentHome.Adapter_Electronics
import com.example.shopaholic.Adapters.FragmentHome.Adapter_OnlineDeal
import com.example.shopaholic.Adapters.FragmentHome.Adapter_TrendingBrands
import com.example.shopaholic.Adapters.FragmentHome.Adapter_newArrivals
import com.example.shopaholic.BaseClasses.DownLoadImageTask
import com.example.shopaholic.Models.Categories.ProductModel
import com.example.shopaholic.Models.HomeScreen.*
import com.example.shopaholic.R
import com.mrusmansiddique.gpt_android.api.Retrofit
import com.mrusmansiddique.gpt_android.api.WebService
import com.mukesh.tinydb.TinyDB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Home(var _context: Context) : Fragment() {
    lateinit var myview: View
    lateinit var rv_OnlineDeals: RecyclerView
    lateinit var rv_Electronic: RecyclerView
    lateinit var rv_newArrivals: RecyclerView
    lateinit var rv_trending: RecyclerView
    lateinit var banner: RelativeLayout
    lateinit var homeScroll: ScrollView

    lateinit var mainPortfolio: ImageView
    lateinit var portfolio1: ImageView
    lateinit var portfolio2: ImageView
    lateinit var portfolio3: ImageView
    lateinit var portfolio4: ImageView

    // private lateinit var progressDialog: ProgressDialog
    private var loadingCount: Int = 0
    lateinit var adapter_onlinedeal: Adapter_OnlineDeal
    lateinit var adapter_Electronic: Adapter_Electronics
    lateinit var adapter_newArrivals: Adapter_newArrivals
    lateinit var adapter_trending: Adapter_TrendingBrands
    val webservice: WebService? get() = Retrofit.instance?.create(WebService::class.java)
    lateinit var tinyDB: TinyDB
    lateinit var productList: ArrayList<ProductModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_home, container, false)
        initRecyclerView()

//        progressDialog = ProgressDialog(requireActivity())
//
//        progressDialog.setMessage("Loading ...!")
//
//        // Set Current Progress
//        progressDialog.setProgress(0)
//        progressDialog.setCancelable(false)
//
//        progressDialog.show()


        return myview
    }

    //initialized Adapters , RecyclerViews and API Methods
    private fun initRecyclerView() {

        tinyDB = TinyDB(_context)
        rv_OnlineDeals = myview.findViewById(R.id.rv_onlinedeals)
        rv_Electronic = myview.findViewById(R.id.rv_Electronic)
        rv_newArrivals = myview.findViewById(R.id.rv_newArrival)
        rv_trending = myview.findViewById(R.id.rv_brands)
        productList = ArrayList()
        homeScroll = myview.findViewById(R.id.homescroll)

        get_BannerModel()
        get_portfolio()
        fun_essential()
        get_DiscountedProducts()
        get_brandsModel()


//        Handler(Looper.getMainLooper()).postDelayed({
//            progressDialog.setMessage("Loading ...! 100% ")
//            progressDialog.dismiss()
//        }, 10000)


        mainPortfolio = myview.findViewById(R.id.portfoliomain)
        portfolio1 = myview.findViewById(R.id.portfolio1)
        portfolio2 = myview.findViewById(R.id.portfolio2)
        portfolio3 = myview.findViewById(R.id.portfolio3)
        portfolio4 = myview.findViewById(R.id.portfolio4)


        banner = myview.findViewById(R.id.banner)


        banner.setOnClickListener {
            _context.startActivity(Intent(_context, ProductActivity::class.java))
        }

        homeScroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

        }
    }

    var BannerModel: Call<Array_BannerModel>? = null
    fun get_BannerModel() {
        BannerModel = webservice?.getBanners()
        BannerModel?.enqueue(object : Callback<Array_BannerModel> {
            override fun onResponse(
                call: Call<Array_BannerModel>,
                response: Response<Array_BannerModel>
            ) {
                if (response.isSuccessful) {
                    var bannerModel: ArrayList<BannerModel> = response.body()?.banner!!

                    adapter_onlinedeal = Adapter_OnlineDeal(bannerModel,_context)
                    rv_OnlineDeals.layoutManager =
                        LinearLayoutManager(_context, RecyclerView.HORIZONTAL, false)
                    rv_OnlineDeals.adapter = adapter_onlinedeal
                } else {
                    Toast.makeText(_context, "No Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Array_BannerModel>, t: Throwable) {
                Log.d("Home", t.message.toString())
            }

        })
    }

    var Brands: Call<Array_Brand_Model>? = null
    fun get_brandsModel() {
        Brands = webservice?.get_brands()
        Brands?.enqueue(object : Callback<Array_Brand_Model> {
            override fun onResponse(
                call: Call<Array_Brand_Model>,
                response: Response<Array_Brand_Model>
            ) {
                if (response.isSuccessful) {
                    var brandslist: ArrayList<Brand_Model> = response.body()?.get_brands!!

                    adapter_trending = Adapter_TrendingBrands(brandslist,_context)
                    rv_trending.layoutManager =
                        LinearLayoutManager(_context, RecyclerView.HORIZONTAL, false)
                    rv_trending.adapter = adapter_trending
                } else {
                    Toast.makeText(_context, "No Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Array_Brand_Model>, t: Throwable) {
                Log.d("Home", t.message.toString())
            }

        })
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
                    adapter_newArrivals = Adapter_newArrivals(dis_Products,_context)

                    rv_newArrivals.layoutManager =
                        LinearLayoutManager(_context, RecyclerView.HORIZONTAL, false)
                    rv_newArrivals.adapter = adapter_newArrivals
                } else {

                    Toast.makeText(_context, "No Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Array_Get_Dis_ProductModel>, t: Throwable) {

            }

        })
    }

    var portfolio: Call<Array_Get_PortfolioModel>? = null
    fun get_portfolio() {
        portfolio = webservice?.get_product_portfolio()
        portfolio?.enqueue(object : Callback<Array_Get_PortfolioModel> {
            override fun onResponse(
                call: Call<Array_Get_PortfolioModel>,
                response: Response<Array_Get_PortfolioModel>
            ) {
                if (response.isSuccessful) {
                    var arrayGetPortfoliomodel: Get_PortfolioModel =
                        response.body()?.get_product_portfolio!!


                    DownLoadImageTask(mainPortfolio).execute(
                        "https://asaanapps.com/new_shopaholic/public/product_portfolio/" +
                                arrayGetPortfoliomodel.product_one_image
                    )
                    DownLoadImageTask(portfolio1).execute(
                        "https://asaanapps.com/new_shopaholic/public/product_portfolio/" +
                                arrayGetPortfoliomodel.product_two_image
                    )
                    DownLoadImageTask(portfolio2).execute(
                        "https://asaanapps.com/new_shopaholic/public/product_portfolio/" +
                                arrayGetPortfoliomodel.product_three_image
                    )
                    DownLoadImageTask(portfolio3).execute(
                        "https://asaanapps.com/new_shopaholic/public/product_portfolio/" +
                                arrayGetPortfoliomodel.product_four_image
                    )
                    DownLoadImageTask(portfolio4).execute(
                        "https://asaanapps.com/new_shopaholic/public/product_portfolio/" +
                                arrayGetPortfoliomodel.product_five_image
                    )
                }
            }

            override fun onFailure(call: Call<Array_Get_PortfolioModel>, t: Throwable) {
                Log.d("Home", t.message.toString())
            }

        })
    }

    var essential: Call<Array_Get_Daily_Essential>? = null
    fun fun_essential() {
        essential = webservice?.get_daily_essential()
        essential?.enqueue(object : Callback<Array_Get_Daily_Essential> {
            override fun onResponse(
                call: Call<Array_Get_Daily_Essential>,
                response: Response<Array_Get_Daily_Essential>
            ) {
                if (response.isSuccessful) {
                    var essentiallist: ArrayList<Get_Daily_Essential> =
                        response.body()?.get_daily_essentails!!

                    adapter_Electronic = Adapter_Electronics(essentiallist,_context)

                    rv_Electronic.layoutManager =
                        LinearLayoutManager(_context, RecyclerView.HORIZONTAL, false)
                    rv_Electronic.adapter = adapter_Electronic


                }
            }

            override fun onFailure(call: Call<Array_Get_Daily_Essential>, t: Throwable) {
                Log.d("Home", t.message.toString())
            }

        })
    }


}