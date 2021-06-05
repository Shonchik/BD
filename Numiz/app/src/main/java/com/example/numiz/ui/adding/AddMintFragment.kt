package com.example.numiz.ui.adding

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.numiz.R
import com.example.numiz.databinding.FragmentAddMintBinding
import com.example.numiz.ui.adding.viewmodel.AddMintVM
import com.example.numiz.ui.model.Country
import com.example.numiz.ui.model.Mint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMintFragment : Fragment() {

    private val viewModel by viewModels<AddMintVM>()
    private lateinit var binding: FragmentAddMintBinding
    var country: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mints: List<Mint> = emptyList()

        viewModel.money.observe(viewLifecycleOwner) {
            mints = it
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
                    viewModel.addMint(Mint(binding.editTextTextPersonName.text.toString(), country))
                }
                viewModel.getAllMint()
                Log.d("dbbd", mints.toString())

                Log.d("dbbd", binding.editTextTextPersonName.text.toString())
                val navController = findNavController()
                navController.popBackStack()
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