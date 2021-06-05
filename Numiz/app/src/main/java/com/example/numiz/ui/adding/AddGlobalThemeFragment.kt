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
import com.example.numiz.databinding.FragmentAddGlobalThemeBinding
import com.example.numiz.db.model.GlobalThemeDbModel
import com.example.numiz.ui.adding.viewmodel.AddCountryVM
import com.example.numiz.ui.adding.viewmodel.AddGlobalThemeVM
import com.example.numiz.ui.model.Country
import com.example.numiz.ui.model.GlobalTheme
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class AddGlobalThemeFragment : Fragment() {

    private val viewModel by viewModels<AddGlobalThemeVM>()
    private lateinit var binding: FragmentAddGlobalThemeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGlobalThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var globalThemes: List<GlobalTheme> = emptyList()

        viewModel.money.observe(viewLifecycleOwner) {
            globalThemes = it
        }

        var global: List<GlobalThemeDbModel> = emptyList()

        viewModel.mon.observe(viewLifecycleOwner) {
            global = it
        }
        viewModel.getAllGlobal()

        binding.button.setOnClickListener {
            viewModel.getAllGlobal()
            try {
                if (binding.editTextTextPersonName.text.toString() != "") {
                    viewModel.addGlobalTheme(GlobalTheme(binding.editTextTextPersonName.text.toString()))
                }
                viewModel.getAllGlobalTheme()
                Log.d("dbbd", globalThemes.toString())
                Log.d("dbbd", global.toString())

                Log.d("dbbd", binding.editTextTextPersonName.text.toString())
                val navController = findNavController()
                navController.popBackStack()
            } catch (e: Exception) {
            }
        }
    }

}