package com.example.shopaholic.Adapters.FragmentCategory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopaholic.DB.DBHelper
import com.example.shopaholic.Models.Categories.ProductModel
import com.example.shopaholic.Models.Categories.prot
import com.example.shopaholic.R
import com.mukesh.tinydb.TinyDB

class Adapter_Category_more(
    var productList: ArrayList<ProductModel>,
    var productId: Int,
    var context: Context
) : RecyclerView.Adapter<Adapter_Category_more.ViewHolder>() {


    var tinyDB: TinyDB = TinyDB(context)
    val dbHelper: DBHelper = DBHelper(context)

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        lateinit var pro_Image: ImageView
        lateinit var pro_Name: TextView
        lateinit var pro_price: TextView
        lateinit var addtoCart: Button
        lateinit var addlayout: RelativeLayout
        lateinit var add: ImageView
        lateinit var delete: ImageView
        lateinit var quantitytext: TextView
        fun bind() {

            pro_Image = itemView.findViewById(R.id.logo)
            pro_Name = itemView.findViewById(R.id.productName)
            pro_price = itemView.findViewById(R.id.pro_Price)
            addtoCart = itemView.findViewById(R.id.addtocart)
            addlayout = itemView.findViewById(R.id.addlayout)
            add = itemView.findViewById(R.id.add)
            delete = itemView.findViewById(R.id.delete)
            quantitytext = itemView.findViewById(R.id.quantity)

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_Category_more.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_more, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: Adapter_Category_more.ViewHolder, position: Int) {
        holder.bind()
        holder.pro_Name.text = productList.get(position).pro_name
        holder.pro_price.text = productList.get(position).pro_price


        var data = productList.get(position).pro_image?.split('_')

        var quantity: Int = 0

        Glide.with(context)
            .load("https://asaanapps.com/new_shopaholic/public/all_products_images/" + data!![0])
            .placeholder(R.drawable.logo)
            .into(holder.pro_Image)

        holder.addtoCart?.setOnClickListener {
            holder.addtoCart?.visibility = View.GONE
            holder.addlayout?.visibility = View.VISIBLE
            holder.quantitytext?.setText("1")
            quantity++

            addtoList(position, quantity)

        }
        holder.add?.setOnClickListener {
            quantity++
            holder.quantitytext?.setText(quantity.toString())
            addtoList(position, quantity)

        }
        holder.delete?.setOnClickListener {
            if (quantity < 1) {
                holder.addtoCart?.visibility = View.VISIBLE
                holder.addlayout?.visibility = View.GONE
                quantity--
                holder.quantitytext?.setText("0")
            } else {
                quantity--
                holder.quantitytext?.setText(quantity.toString())

            }
            addtoList(position, quantity)
        }


    }

    fun addtoList(position: Int, _quantity: Int) {

        if (_quantity <= 1) {
            dbHelper.addProductToCart(
                prot(
                    productList.get(position).id!!,
                    productList.get(position).pro_name!!,
                    productList.get(position).pro_price!!,
                    productList.get(position).pro_image!!,
                    _quantity,
                    position
                )
            )
        } else {
            dbHelper.update(
                prot(
                    productList.get(position).id!!,
                    productList.get(position).pro_name!!,
                    productList.get(position).pro_price!!,
                    productList.get(position).pro_image!!,
                    _quantity,
                    position
                )
            )
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun removeAt(index: Int) {
        productList.removeAt(index)   // items is a MutableList
        notifyItemRemoved(index)
    }


}