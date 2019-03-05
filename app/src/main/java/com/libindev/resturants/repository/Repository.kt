package com.libindev.resturants.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.libindev.resturants.MainActivity

object Repository{
    fun loadResturantMenu(context: Context,resturantMenuDocmt: MutableLiveData<QuerySnapshot>?) {
        FirebaseApp.initializeApp(context);

        val db = FirebaseFirestore.getInstance()

        db.collection("Resturant Menu")
            .orderBy("name", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->
                if(resturantMenuDocmt!=null) {
                    resturantMenuDocmt.postValue(documents)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(MainActivity.TAG, "Error getting documents: ", exception)
            }

    }

    fun loadResturantItem(context: Context,resturantMenuItem: MutableLiveData<QuerySnapshot>?,id:String) {


        val db = FirebaseFirestore.getInstance()

        db.collection("Resturant Menu").document(id).collection("Items")
            .orderBy("name", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    Log.d(MainActivity.TAG, document.id + " => " + document.get("name"))
                }

                if (resturantMenuItem != null) {
                    resturantMenuItem.postValue(documents)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(MainActivity.TAG, "Error getting documents: ", exception)
            }

    }


}