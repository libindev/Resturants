package com.libindev.resturants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.libindev.resturants.BR
import com.libindev.resturants.R
import com.libindev.resturants.data.models.Menu
import com.libindev.resturants.databinding.LayoutMenuBinding

class MenuAdapter(private var dataModelList: List<Menu>, private var onClick: (Menu) -> Unit) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: LayoutMenuBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_menu, parent, false
        )
        return ViewHolder(binding)
    }

    fun setMenuList(dataModelList: List<Menu>) {
        this.dataModelList = dataModelList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataModelList[position])
        holder.itemRowBinding.setItemClickListener {
            onClick.invoke(it)
        }
    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }

    inner class ViewHolder(var itemRowBinding: LayoutMenuBinding) : RecyclerView.ViewHolder(
        itemRowBinding.root
    ) {
        fun bind(obj: Menu) {
            itemRowBinding.setVariable(BR.menu, obj)
            itemRowBinding.executePendingBindings()
        }
    }
}
