package com.libindev.resturants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.libindev.resturants.adapters.MenuAdapter
import com.libindev.resturants.adapters.MenuItemAdapter
import com.libindev.resturants.databinding.MenuListingFragmentBinding
import com.libindev.resturants.databinding.MenuListingFragmentBinding.inflate
import com.libindev.resturants.viewmodels.MenuListingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuListingFragment : Fragment() {
    lateinit var binding: MenuListingFragmentBinding
    lateinit var menuAdapter: MenuAdapter
    lateinit var menuItemAdapter: MenuItemAdapter
    private val viewModel: MenuListingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, container, false)
        viewModel.loadMenu()
        viewModel.menuList.observe(viewLifecycleOwner) { result ->
            menuAdapter.setMenuList(result)
        }
        viewModel.menuItemList.observe(viewLifecycleOwner) { result ->
            menuItemAdapter.setMenuItemList(result)
        }

        menuAdapter = MenuAdapter(arrayListOf()) {
            viewModel.loadMenuItems(it.id)
        }
        menuItemAdapter = MenuItemAdapter(arrayListOf())

        binding.recyclerMenu.adapter = menuAdapter
        binding.recyclerMenuItem.adapter = menuItemAdapter

        return binding.root
    }
}
