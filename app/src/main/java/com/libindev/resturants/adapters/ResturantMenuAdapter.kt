package com.libindev.resturants.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.libindev.resturants.R
class ResturantMenuAdapter(val userList: QuerySnapshot,val context: Context) : RecyclerView.Adapter<ResturantMenuAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResturantMenuAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.chips, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ResturantMenuAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList.documents[position])


    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size()
    }

    //the class is hodling the list view
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user:DocumentSnapshot ) {
            val chip = itemView.findViewById(R.id.chip) as Chip
            chip.text = user.getString("name")


            chip.setOnClickListener {
                (context as onMenuClickListener).onclick(user.id)

            }

        }
    }


   public interface onMenuClickListener{
   public   fun onclick(id :String)

    }
}