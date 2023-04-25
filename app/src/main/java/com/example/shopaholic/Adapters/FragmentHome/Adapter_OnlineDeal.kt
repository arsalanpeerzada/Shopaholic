package com.example.shopaholic.Adapters.FragmentHome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopaholic.BaseClasses.DownLoadImageTask
import com.example.shopaholic.Models.HomeScreen.BannerModel
import com.example.shopaholic.R

class Adapter_OnlineDeal(var card: ArrayList<BannerModel>, var context: Context) :
    RecyclerView.Adapter<Adapter_OnlineDeal.ViewHolder>() {
    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        lateinit var banner: ImageView

        fun viewHolder(itemview1: View) {
            banner = itemview1.findViewById(R.id.banner)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onlinedeals, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return card.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewHolder(holder.itemView)

        DownLoadImageTask(holder.banner).execute(
            "https://asaanapps.com/new_shopaholic/public/banner_images/" +
                    card.get(position).banner_img
        )

        Glide.with(context)
            .load(
                "https://asaanapps.com/new_shopaholic/public/banner_images/" +
                        card.get(position).banner_img
            )
            .placeholder(R.drawable.logo)
            .into(holder.banner)
    }


}