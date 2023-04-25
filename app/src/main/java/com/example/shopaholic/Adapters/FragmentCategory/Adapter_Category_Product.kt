package com.example.shopaholic.Adapters.FragmentCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopaholic.Fragments.BottomBar.CategoryListScreen
import com.example.shopaholic.Models.Categories.ProductModel
import com.example.shopaholic.R

class Adapter_Category_Product(
    var productlist: ArrayList<ProductModel>,
    var productId: Int,
    var context: Context
) : RecyclerView.Adapter<Adapter_Category_Product.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var image: ImageView
        lateinit var productName: TextView

        fun bind() {
            image = itemView.findViewById(R.id.image)
            productName = itemView.findViewById(R.id.categorytext)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_product, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()


        var data = productlist.get(position).pro_image?.split('_')
        Glide.with(context)
            .load("https://asaanapps.com/new_shopaholic/public/all_products_images/" + data!![0])
            .placeholder(R.drawable.logo)
            .into(holder.image)
        holder.productName.text = productlist.get(position).pro_name

        holder.itemView.setOnClickListener {
            openFragment(CategoryListScreen(productlist, productId, _context = context))
        }

    }

    override fun getItemCount(): Int {
        return productlist.size
    }

    fun openFragment(fragment: Fragment?) {
        val manager = (context as AppCompatActivity).supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.bottomnav_framelayout, fragment!!).commit()
    }
}