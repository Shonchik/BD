package com.example.numiz.ui.update

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
import com.example.numiz.databinding.FragmentUCountryBinding
import com.example.numiz.db.model.CountryDbModel
import com.example.numiz.ui.adding.viewmodel.AddCountryVM
import com.example.numiz.ui.model.Country
import com.example.numiz.ui.model.sv.SVCountryModel
import com.example.numiz.ui.model.sv.SVMintModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class UCountryFragment : Fragment() {

    private val viewModel by viewModels<AddCountryVM>()
    private lateinit var binding: FragmentUCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var countrys: List<Country> = emptyList()

        val data: SVCountryModel? = arguments?.getParcelable<SVCountryModel>("data")
        if (data != null) {
            binding.editTextTextPersonName.setText(data.name)
        }

        viewModel.money.observe(viewLifecycleOwner) {
            countrys = it
        }

        binding.button.setOnClickListener {
            try {
                if (binding.editTextTextPersonName.text.toString() != "") {
                    if (data != null) {
                        viewModel.updateCountry(CountryDbModel(data.id, binding.editTextTextPersonName.text.toString()))
                    }
                }
                viewModel.getAllCountry()
                Log.d("dbbd", countrys.toString())

                Log.d("dbbd", binding.editTextTextPersonName.text.toString())
                val navController = findNavController()
                navController.navigate(R.id.viewCountryFragment)
            } catch (e: Exception) {
            }
        }
    }

}