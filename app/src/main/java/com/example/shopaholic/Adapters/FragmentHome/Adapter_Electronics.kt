package com.example.shopaholic.Adapters.FragmentHome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopaholic.Models.HomeScreen.Get_Daily_Essential
import com.example.shopaholic.R

class Adapter_Electronics(
    var essentiallist: ArrayList<Get_Daily_Essential>,
    var _context: Context
) :
    RecyclerView.Adapter<Adapter_Electronics.ViewHolder>() {


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        lateinit var titleImage: ImageView
        lateinit var first: ImageView
        lateinit var second: ImageView
        lateinit var third: ImageView
        lateinit var forth: ImageView

        lateinit var titleImageText: TextView
        lateinit var firstText: TextView
        lateinit var secondText: TextView
        lateinit var thirdText: TextView
        lateinit var forthText: TextView


        fun bind() {
            titleImage = itemView.findViewById(R.id.mainimage)
            first = itemView.findViewById(R.id.first)
            second = itemView.findViewById(R.id.second)
            third = itemView.findViewById(R.id.third)
            forth = itemView.findViewById(R.id.forth)


            titleImageText = itemView.findViewById(R.id.mainText)
            firstText = itemView.findViewById(R.id.firstText)
            secondText = itemView.findViewById(R.id.secondText)
            thirdText = itemView.findViewById(R.id.thirdText)
            forthText = itemView.findViewById(R.id.forthText)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_Electronics.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_electronicitems, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: Adapter_Electronics.ViewHolder, position: Int) {
        holder.bind()

        Glide.with(_context)
            .load(
                "https://asaanapps.com/new_shopaholic/public/daily_item_images/" +
                        essentiallist.get(position).main_title_image
            )
            .placeholder(R.drawable.logo)
            .into(holder.titleImage)

        Glide.with(_context)
            .load(
                "https://asaanapps.com/new_shopaholic/public/daily_item_images/" +
                        essentiallist.get(position).image_one
            )
            .placeholder(R.drawable.logo)
            .into(holder.first)

        Glide.with(_context)
            .load(
                "https://asaanapps.com/new_shopaholic/public/daily_item_images/" +
                        essentiallist.get(position).image_two
            )
            .placeholder(R.drawable.logo)
            .into(holder.second)

        Glide.with(_context)
            .load(
                "https://asaanapps.com/new_shopaholic/public/daily_item_images/" +
                        essentiallist.get(position).image_three
            )
            .placeholder(R.drawable.logo)
            .into(holder.third)

        Glide.with(_context)
            .load(
                "https://asaanapps.com/new_shopaholic/public/daily_item_images/" +
                        essentiallist.get(position).image_four
            )
            .placeholder(R.drawable.logo)
            .into(holder.forth)


//        DownLoadImageTask(holder.titleImage).execute(
//            "https://asaanapps.com/new_shopaholic/public/daily_item_images/" +
//                    essentiallist.get(position).main_title_image
//        )
//        DownLoadImageTask(holder.first).execute(
//            "https://asaanapps.com/new_shopaholic/public/daily_item_images/" +
//                    essentiallist.get(position).image_one
//        )
//        DownLoadImageTask(holder.second).execute(
//            "https://asaanapps.com/new_shopaholic/public/daily_item_images/" +
//                    essentiallist.get(position).image_two
//        )
//        DownLoadImageTask(holder.third).execute(
//            "https://asaanapps.com/new_shopaholic/public/daily_item_images/" +
//                    essentiallist.get(position).image_three
//        )
//        DownLoadImageTask(holder.forth).execute(
//            "https://asaanapps.com/new_shopaholic/public/daily_item_images/" +
//                    essentiallist.get(position).image_four
//        )

        holder.titleImageText.text = essentiallist.get(position).main_title
        holder.firstText.text = essentiallist.get(position).title_one
        holder.secondText.text = essentiallist.get(position).title_two
        holder.thirdText.text = essentiallist.get(position).title_three
        holder.forthText.text = essentiallist.get(position).title_four

    }

    override fun getItemCount(): Int {
        return essentiallist.size
    }


}