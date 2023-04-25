package com.example.shopaholic.Adapters.FragmentHome

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopaholic.BaseClasses.DownLoadImageTask
import com.example.shopaholic.Models.HomeScreen.Get_Dis_ProductModel
import com.example.shopaholic.R
import com.mukesh.tinydb.TinyDB

class Adapter_newArrivals(
    var discountProducts: ArrayList<Get_Dis_ProductModel>,
    var _context: Context
) : RecyclerView.Adapter<Adapter_newArrivals.ViewHolder>() {

    var quantity: Int = 0
    lateinit var tinyDB: TinyDB

    data class CartModel(
        var name: String,
        var image: String,
        var price: String,
        var price2: String
    )

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var product: ImageView? = null
        var name: TextView? = null
        var price: TextView? = null
        var secondPrice: TextView? = null
        var addtoCart: Button? = null
        var addlayout: RelativeLayout? = null
        var delete: ImageView? = null
        var add: ImageView? = null
        var quantity: TextView? = null
        var discount: TextView? = null
        var size: TextView? = null


        fun myViewHolder(itemview: View) {
            product = itemview.findViewById(R.id.product_Image)
            name = itemview.findViewById(R.id.productName)
            price = itemview.findViewById(R.id.product_price)
            secondPrice = itemview.findViewById(R.id.product_price2)
            addtoCart = itemview.findViewById(R.id.addtocart)
            addlayout = itemview.findViewById(R.id.addlayout)
            delete = itemview.findViewById(R.id.delete)
            add = itemview.findViewById(R.id.add)
            quantity = itemview.findViewById(R.id.quantity)
            discount = itemview.findViewById(R.id.discount)
            size = itemview.findViewById(R.id.product_Size)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_newArrivals.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_newarrivals, parent, false)
        tinyDB = TinyDB(parent.context)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return discountProducts.size
    }

    override fun onBindViewHolder(holder: Adapter_newArrivals.ViewHolder, position: Int) {
        holder.myViewHolder(holder.itemView)
        holder.name?.setText(discountProducts.get(position).pro_title)
//        DownLoadImageTask(holder.product!!).execute(
//            "https://asaanapps.com/new_shopaholic/public/product_images/" +
//                    discountProducts.get(position).pro_image
//        )

        Glide.with(_context)
            .load(
                "https://asaanapps.com/new_shopaholic/public/product_images/" +
                        discountProducts.get(position).pro_image
            )
            .placeholder(R.drawable.logo)
            .into(holder.product!!)
        //holder.product?.setImageResource(discountProducts[position])
        holder.price?.setText("Rs: " + discountProducts[position].pro_after_dis)
        holder.discount?.setText("${discountProducts.get(position).pro_dis_per}% Discount")
        holder.size?.setText("${discountProducts.get(position).pro_size}")
        holder.secondPrice?.setText("Rs:" + discountProducts[position].pro_before_dis)

        holder.secondPrice?.setPaintFlags(holder.secondPrice?.getPaintFlags()!! or Paint.STRIKE_THRU_TEXT_FLAG)

        holder.addtoCart?.setOnClickListener {
            holder.addtoCart?.visibility = View.GONE
            holder.addlayout?.visibility = View.VISIBLE
            holder.quantity?.setText("1")
            quantity++

        }
        holder.add?.setOnClickListener {
            quantity++;
            holder.quantity?.setText(quantity.toString())


        }
        holder.delete?.setOnClickListener {
            if (quantity == 1) {
                holder.addtoCart?.visibility = View.VISIBLE
                holder.addlayout?.visibility = View.GONE
                quantity--
                holder.quantity?.setText("0")
            } else {
                quantity--
                holder.quantity?.setText(quantity.toString())
            }
        }

    }


}