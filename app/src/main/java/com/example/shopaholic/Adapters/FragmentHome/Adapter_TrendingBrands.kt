package com.example.shopaholic.Adapters.FragmentHome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopaholic.Models.HomeScreen.Brand_Model
import com.example.shopaholic.R

class Adapter_TrendingBrands(var brandlist: ArrayList<Brand_Model>, var _context: Context) :
    RecyclerView.Adapter<Adapter_TrendingBrands.ViewHolder>() {


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        lateinit var brands: ImageView
        fun bind() {
            brands = itemView.findViewById(R.id.mainimage)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_TrendingBrands.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trendingbrands, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: Adapter_TrendingBrands.ViewHolder, position: Int) {

        holder.bind()
//        DownLoadImageTask(holder.brands).execute(
//            "https://asaanapps.com/new_shopaholic/public/banner_images/" +
//                    brandlist.get(position).brands_image
//        )

        Glide.with(_context)
            .load(
                "https://asaanapps.com/new_shopaholic/public/banner_images/" +
                        brandlist.get(position).brands_image
            )
            .placeholder(R.drawable.logo)
            .into(holder.brands)
    }

    override fun getItemCount(): Int {
        return brandlist.size
    }


}