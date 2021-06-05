package com.example.numiz.ui.adding

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.numiz.R
import com.example.numiz.databinding.FragmentAddCountryBinding
import com.example.numiz.ui.adding.viewmodel.AddCountryVM
import com.example.numiz.ui.model.Country
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class AddCountryFragment : Fragment() {

    private val viewModel by viewModels<AddCountryVM>()
    private lateinit var binding: FragmentAddCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countrys: List<Country> = emptyList()

        viewModel.money.observe(viewLifecycleOwner) {
            countrys = it
        }

        binding.button.setOnClickListener {
            try {
                if (binding.editTextTextPersonName.text.toString() != "") {
                    viewModel.addCountry(Country(binding.editTextTextPersonName.text.toString()))
                }
                viewModel.getAllCountry()
                Log.d("dbbd", countrys.toString())

                Log.d("dbbd", binding.editTextTextPersonName.text.toString())
                val navController = findNavController()
                navController.popBackStack()
            } catch (e: Exception) {
            }
        }
    }

}