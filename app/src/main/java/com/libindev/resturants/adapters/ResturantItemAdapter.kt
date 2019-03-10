package com.libindev.resturants.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.libindev.resturants.R
import com.squareup.picasso.Picasso



class ResturantItemAdapter(val itemList: QuerySnapshot, val context: Context) : RecyclerView.Adapter<ResturantItemAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResturantItemAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.resturant_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ResturantItemAdapter.ViewHolder, position: Int) {
        holder.bindItems(itemList.documents[position])


    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return itemList.size()
    }

    //the class is hodling the list view
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user:DocumentSnapshot ) {
            val name = itemView.findViewById(R.id.item_name) as TextView
            name.text = user.getString("name")

            val price = itemView.findViewById(R.id.item_price) as Chip
            price.text = user.getString("price")

             val imageView = itemView.findViewById(R.id.imageView) as ImageView

            // chip.text = user.getString("url")
            Picasso.get().load(user.getString("url")).into(imageView)





            itemView.setOnClickListener {

            }

        }
    }


   public interface onMenuClickListener{
   public   fun onclick(id :String)

    }
}