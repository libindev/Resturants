package com.libindev.resturants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.libindev.resturants.BR
import com.libindev.resturants.R
import com.libindev.resturants.data.models.MenuItems
import com.libindev.resturants.databinding.LayoutMenuItemBinding

class MenuItemAdapter(private var menuItemList: List<MenuItems>) :
    RecyclerView.Adapter<MenuItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutMenuItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_menu_item, parent, false
        )
        return ViewHolder(binding)
    }

    fun setMenuItemList(menuItemList: List<MenuItems>) {
        this.menuItemList = menuItemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuItemList[position])
    }

    override fun getItemCount(): Int {
        return menuItemList.size
    }

    inner class ViewHolder(var itemRowBinding: LayoutMenuItemBinding) : RecyclerView.ViewHolder(
        itemRowBinding.root
    ) {
        fun bind(obj: MenuItems) {
            itemRowBinding.setVariable(BR.menu_items, obj)
            itemRowBinding.executePendingBindings()
        }
    }
}
