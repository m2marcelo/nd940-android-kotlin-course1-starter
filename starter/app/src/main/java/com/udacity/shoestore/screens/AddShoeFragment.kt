package com.udacity.shoestore.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoesViewModel

class AddShoeFragment : Fragment() {

    private lateinit var binding: FragmentAddShoeBinding
    private val shoeViewModel by activityViewModels<ShoesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_shoe, container, false)
        binding.shoe = Shoe("", "", "", "")

        val shoes = binding.shoe!!

        binding.buttonCancel.setOnClickListener{
            it.findNavController().navigate(R.id.action_addShoeFragment_to_shoeListFragment)
        }

        binding.buttonSave.setOnClickListener{
            if (validShoeParams()) {
                shoeViewModel.saveData(shoes)
                it.findNavController().navigate(R.id.action_addShoeFragment_to_shoeListFragment)
            } else {
                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun validShoeParams() : Boolean {
        if (binding.inputShoeName.text.isEmpty() ||
            binding.inputBrand.text.isEmpty() ||
            binding.inputShoeSize.text.isEmpty()) {
            return false
        }

        return true
    }
}