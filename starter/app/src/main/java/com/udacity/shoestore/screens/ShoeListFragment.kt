package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.viewmodel.ShoesViewModel
import kotlinx.android.synthetic.main.fragment_add_shoe.view.*

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val shoeViewModel by activityViewModels<ShoesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)


        binding.floatButtonAddShoe.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoeListFragment_to_addShoeFragment)
        )
        binding.lifecycleOwner = this

        shoeViewModel.shoesList.observe(viewLifecycleOwner) { newShoe ->
            newShoe.forEach { shoe ->
                val shoeItem: ShoeItemBinding =
                    DataBindingUtil.inflate(inflater, R.layout.shoe_item, container, false)

                shoeItem.itemShoeName.text = shoe.name
                shoeItem.itemShoeBrand.text = shoe.company
                shoeItem.itemShoeSize.text = shoe.size
                shoeItem.itemShoeDescription.text = shoe.description
                shoeItem.executePendingBindings()

                binding.shoeList.addView(shoeItem.root)
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}