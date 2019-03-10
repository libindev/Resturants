package com.libindev.resturants

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.firestore.QuerySnapshot
import com.libindev.resturants.adapters.ResturantItemAdapter
import com.libindev.resturants.adapters.ResturantMenuAdapter



class MainActivity : AppCompatActivity(),ResturantMenuAdapter.onMenuClickListener {

    lateinit   var   restaurantMenu :MutableLiveData<QuerySnapshot>
    lateinit   var   restaurantItem :MutableLiveData<QuerySnapshot>
    lateinit   var   model: MainActivitityViewModel

    companion object {
        val TAG:String="MAIN"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById(R.id.recycler) as RecyclerView
        val recyclerViewItem = findViewById(R.id.recycler_item) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerViewItem.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
          model = ViewModelProviders.of(this).get(MainActivitityViewModel::class.java)
         restaurantMenu=  model.getResturantMenu()
         restaurantMenu.observe(this, Observer<QuerySnapshot>{ resturantMenuDocmt ->
            if (resturantMenuDocmt != null) {

                    val adapter = ResturantMenuAdapter(resturantMenuDocmt,this)
                    recyclerView.adapter = adapter


            }
        })

        restaurantItem=  model.getResturantMenuItem()
        restaurantItem.observe(this, Observer<QuerySnapshot>{ resturantItemDoc  ->
            if (resturantItemDoc != null) {
                for (document in resturantItemDoc) {
                    Log.d(TAG, document.id + " => " + document.get("name"))
                }
                val adapter = ResturantItemAdapter(resturantItemDoc ,this)
                recyclerViewItem.adapter = adapter


            }
        })
        model.loadRestaurantMenu(this@MainActivity)


    }

     override fun onclick(id:String) {
         model.loadRestaurantItem(this@MainActivity,id)

     }
}
