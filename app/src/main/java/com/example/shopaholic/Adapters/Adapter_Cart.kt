package com.example.shopaholic.Adapters

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopaholic.Interface.CartInterface
import com.example.shopaholic.Models.Categories.prot
import com.example.shopaholic.R


class Adapter_Cart(var data: ArrayList<prot>, var context: Context, var iCart: CartInterface) :
    RecyclerView.Adapter<Adapter_Cart.ViewHolder>() {

    var quantity: Int = 0
    var undoOn // is undo on, you can turn it on from the toolbar menu
            = false

    private val handler: Handler = Handler() // hanlder for running delayed runnables

    var pendingRunnables: HashMap<String, Runnable> =
        HashMap() // map of items to pending runnables, so we can cancel a removal if need be


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        lateinit var image: ImageView
        lateinit var pro_Name: TextView
        lateinit var unitPrice: TextView
        lateinit var quantity: TextView
        lateinit var add: ImageView
        lateinit var delete: ImageView
        lateinit var finalPrice: TextView

        fun bind() {
            image = itemView.findViewById(R.id.image)
            pro_Name = itemView.findViewById(R.id.productName1)
            unitPrice = itemView.findViewById(R.id.unitPrice)
            delete = itemView.findViewById(R.id.delete2)
            add = itemView.findViewById(R.id.add2)
            finalPrice = itemView.findViewById(R.id.finalprice)
            quantity = itemView.findViewById(R.id.quantity)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_Cart.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_cart, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: Adapter_Cart.ViewHolder, position: Int) {
        holder.bind()

        quantity = data.get(position).quantitiy

        holder.pro_Name.text = data.get(position).name
        holder.quantity.text = data.get(position).quantitiy.toString()
        holder.unitPrice.text = "[Unit Price Rs. ${data.get(position).price}]"

        holder.finalPrice.text =
            (quantity * data.get(position).price.toInt()).toString()

        var mydata = data.get(position).image?.split('_')

        Glide.with(context)
            .load("https://asaanapps.com/new_shopaholic/public/all_products_images/" + mydata!![0])
            .placeholder(R.drawable.logo)
            .into(holder.image)

        holder.add.setOnClickListener {
            quantity++
            holder.quantity.text = quantity.toString()

            holder.finalPrice.text =
                (quantity * data.get(position).price.toInt()).toString()

            iCart.update(
                data.get(position).product_ID,
                holder.pro_Name.text.toString(),
                data.get(position).price,
                holder.quantity.text.toString(),
                data.get(position).image
            )

        }

        holder.delete.setOnClickListener {

            if (quantity <= 0) {
                holder.finalPrice.text = "0"
                holder.quantity.text = quantity.toString()
            } else {
                quantity--
                holder.quantity.text = quantity.toString()

                holder.finalPrice.text =
                    (quantity * data.get(position).price.toInt()).toString()

                iCart.update(
                    data.get(position).product_ID,
                    holder.pro_Name.text.toString(),
                    data.get(position).price,
                    holder.quantity.text.toString(),
                    data.get(position).image
                )
            }
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }





}