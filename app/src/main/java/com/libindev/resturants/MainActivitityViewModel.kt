package com.libindev.resturants


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.QuerySnapshot
import com.libindev.resturants.repository.Repository

class MainActivitityViewModel: ViewModel() {
    private  val _repository:Repository by lazy{
        // runs on first access
        Repository
    }

    private var resturantMenu: MutableLiveData<QuerySnapshot>? = null
    private var resturantMenuItem: MutableLiveData<QuerySnapshot>? = null

    fun getResturantMenu(): MutableLiveData<QuerySnapshot> {
        if (resturantMenu == null) {
            resturantMenu = MutableLiveData()



        }
        return resturantMenu as MutableLiveData<QuerySnapshot>
    }

    fun getResturantMenuItem(): MutableLiveData<QuerySnapshot> {

        if (resturantMenuItem == null) {
            resturantMenuItem = MutableLiveData()


        }

        return resturantMenuItem as MutableLiveData<QuerySnapshot>
    }

   fun loadRestaurantItem(context: Context, menu_id:String){

        _repository.loadResturantItem(context,resturantMenuItem,menu_id )
    }

    fun loadRestaurantMenu(context: Context){
        _repository.loadResturantMenu(context,resturantMenu )
    }

}