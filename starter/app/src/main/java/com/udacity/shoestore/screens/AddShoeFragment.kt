package com.udacity.shoestore.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAddShoeBinding

class AddShoeFragment : Fragment() {

    private lateinit var binding: FragmentAddShoeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoe, container, false)

        binding.buttonCancel.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_addShoeFragment_to_shoeListFragment)
        )

        binding.buttonSave.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_addShoeFragment_to_shoeListFragment)
        )

        return binding.root
    }
}