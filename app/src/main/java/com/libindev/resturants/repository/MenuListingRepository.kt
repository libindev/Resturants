package com.libindev.resturants.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.libindev.resturants.State
import com.libindev.resturants.data.models.Menu
import com.libindev.resturants.data.models.MenuItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MenuListingRepository @Inject constructor(private val db: FirebaseFirestore) {

    fun loadRestaurantMenu() = flow<State<MutableList<Menu>>> {
        val documents = db.collection("Resturant Menu")
            .orderBy("name", Query.Direction.ASCENDING)
            .get()
            .await()
        if (documents != null) {
            emit(State.Success(documents.toObjects(Menu::class.java)))
        }
    }.catch { exception ->
        emit(State.Failed(exception.toString()))
    }.flowOn(Dispatchers.IO)

    fun loadRestaurantItem(id: String) = flow<State<MutableList<MenuItems>>> {
        val db = FirebaseFirestore.getInstance()
        val documents = db.collection("Resturant Menu").document(id).collection("Items")
            .get()
            .await()
        if (documents != null) {
            emit(State.Success(documents.toObjects(MenuItems::class.java)))
        }
    }.catch { exception ->
        emit(State.Failed(exception.toString()))
    }.flowOn(Dispatchers.IO)
}
