package com.libindev.resturants


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.QuerySnapshot

class MainActivitityViewModel : ViewModel() {
    private var resturantMenuDocmt: MutableLiveData<QuerySnapshot>? = null

    private var resturantMenuItem: MutableLiveData<QuerySnapshot>? = null

    fun getResturantMenuDocmt(): MutableLiveData<QuerySnapshot> {
        if (resturantMenuDocmt == null) {
            resturantMenuDocmt = MutableLiveData()
           // loadUsers(context)
        }
        return resturantMenuDocmt as MutableLiveData<QuerySnapshot>
    }

    fun getResturantMenuItem(): MutableLiveData<QuerySnapshot> {
        if (resturantMenuItem == null) {
            resturantMenuItem = MutableLiveData()
            // loadUsers(context)
        }
        return resturantMenuItem as MutableLiveData<QuerySnapshot>
    }//
   /* private fun loadUsers(context: Context) {
        FirebaseApp.initializeApp(context);
        val db = FirebaseFirestore.getInstance()

        db.collection("Resturant Menu")
            .orderBy("name", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->
               resturantMenuDocmt?.postValue(documents)
            }
            .addOnFailureListener { exception ->
                Log.w(MainActivity.TAG, "Error getting documents: ", exception)
            }
        // Do an asynchronous operation to fetch resturantMenuDocmt.
    }*/
}