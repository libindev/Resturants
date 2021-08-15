package com.libindev.resturants.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.libindev.resturants.State
import com.libindev.resturants.State.Failed
import com.libindev.resturants.State.Success
import com.libindev.resturants.data.models.Menu
import com.libindev.resturants.data.models.MenuItems
import com.libindev.resturants.repository.MenuListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuListingViewModel @Inject constructor(private val menuListingRepository: MenuListingRepository) :
    ViewModel() {

    val menuList: MutableLiveData<List<Menu>> by lazy {
        MutableLiveData()
    }
    val menuItemList: MutableLiveData<List<MenuItems>> by lazy {
        MutableLiveData()
    }

    fun loadMenu() {
        viewModelScope.launch {
            menuListingRepository.loadRestaurantMenu().collect { its ->
                when (its) {
                    is Failed -> {
                    }
                    is Success -> {
                        menuList.value = its.data
                    }
                    is State.Loading -> {
                    }
                }
            }
        }
    }

    fun loadMenuItems(menuId: String) {
        viewModelScope.launch {
            menuListingRepository.loadRestaurantItem(menuId).collect { its ->
                when (its) {
                    is Failed -> {
                    }
                    is Success -> {
                        menuItemList.value = its.data
                    }
                    is State.Loading -> {
                    }
                }
            }
        }
    }
}
