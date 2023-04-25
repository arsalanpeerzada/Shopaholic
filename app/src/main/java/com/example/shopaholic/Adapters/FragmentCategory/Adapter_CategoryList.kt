package com.example.shopaholic.Adapters.FragmentCategory

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.shopaholic.Fragments.RecyclerViewFragment
import com.example.shopaholic.Models.Categories.CategoryModel
import com.example.shopaholic.R

class Adapter_CategoryList(
    var context: Context,
    var categoryList: ArrayList<CategoryModel>
) : RecyclerView.Adapter<Adapter_CategoryList.ViewHolder>() {

    private var selectedItemPosition: Int = 0
    lateinit var itemview: View
    var layoutInflater: LayoutInflater? = null

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var name: TextView? = null
        var titleview: RelativeLayout? = null
        var selected: View? = null

        fun myViewHolder(itemview: View) {
            name = itemview.findViewById(R.id.categorytext)
            titleview = itemview.findViewById(R.id.titleview)
            selected = itemview.findViewById(R.id.selected)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemview =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categories_title, parent, false)



        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.myViewHolder(holder.itemView)
        //holder.imageView?.setImageResource(IMAGES[position])
        holder.name?.setText(categoryList.get(position).category)

        holder.itemView.setOnClickListener {
            // openFragment(SubCategory())
            selectedItemPosition = position
            notifyDataSetChanged()
            openFragment(RecyclerViewFragment(10, context))
        }

        if (selectedItemPosition == position) {
            holder.titleview?.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
            holder.selected?.visibility = View.VISIBLE
            holder.name?.setTextColor(ContextCompat.getColor(context, R.color.red))
        } else {
            holder.titleview?.setBackgroundColor(Color.parseColor("#EEEEEE"))
            holder.selected?.visibility = View.INVISIBLE
        }
//        openFragment(SubCategory())
        openFragment(RecyclerViewFragment(10, context))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun openFragment(fragment: Fragment?) {
        val transaction = (context as AppCompatActivity).supportFragmentManager
        transaction.beginTransaction()
            .replace(R.id.categoryframelayout, fragment!!)
            .commit()

    }
}