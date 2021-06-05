package com.example.numiz.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.numiz.R
import com.example.numiz.databinding.FragmentSVMintBinding
import com.example.numiz.databinding.FragmentUMintBinding
import com.example.numiz.db.model.MintDbModel
import com.example.numiz.ui.adding.viewmodel.AddMintVM
import com.example.numiz.ui.model.Country
import com.example.numiz.ui.model.Mint
import com.example.numiz.ui.model.sv.SVMintModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UMintFragment : Fragment() {

    private val viewModel by viewModels<AddMintVM>()
    private lateinit var binding: FragmentUMintBinding
    var country: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUMintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: SVMintModel? = arguments?.getParcelable<SVMintModel>("data")
        if (data != null) {
            binding.editTextTextPersonName.setText(data.name)
            binding.textView.text = data.country
            country = data.countryId
        }

        var countrys: List<Country> = emptyList()

        viewModel.countrys.observe(viewLifecycleOwner) {
            countrys = it
        }
        viewModel.getAllCountry()

        binding.button2.setOnClickListener {
            viewModel.getAllCountry()
            withItems(countrys)
        }

        binding.button.setOnClickListener {
            try {
                if (binding.editTextTextPersonName.text.toString() != "" && country != -1) {
                    if (data != null) {
                        viewModel.updateMint(MintDbModel(data.id, binding.editTextTextPersonName.text.toString(), country))
                    }
                }
                val navController = findNavController()
                navController.navigate(R.id.viewMintFragment)
            } catch (e: Exception) {
            }
        }

    }

    fun withItems(countrys: List<Country>) {
        val c = Array(countrys.size) {
            countrys[it].name
        }
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("Select Country")
            setItems(c) { dialog, which ->
                country = which + 1
                binding.textView.text = c[which]
            }
            setPositiveButton("NEW COUNTRY") { dialogInterface, i ->
                go()
            }
            show()
        }
    }

    fun go() {
        val navController = findNavController()
        navController.navigate(R.id.addCountryFragment)
    }

}