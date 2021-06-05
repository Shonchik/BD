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
import com.example.numiz.databinding.FragmentAddGlobalThemeBinding
import com.example.numiz.databinding.FragmentUGlobalThemeBinding
import com.example.numiz.db.model.GlobalThemeDbModel
import com.example.numiz.ui.adding.viewmodel.AddGlobalThemeVM
import com.example.numiz.ui.model.GlobalTheme
import com.example.numiz.ui.model.sv.SVCountryModel
import com.example.numiz.ui.model.sv.SVGlobalThemeModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class UGlobalThemeFragment : Fragment() {

    private val viewModel by viewModels<AddGlobalThemeVM>()
    private lateinit var binding: FragmentUGlobalThemeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUGlobalThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var globalThemes: List<GlobalTheme> = emptyList()

        viewModel.money.observe(viewLifecycleOwner) {
            globalThemes = it
        }

        val data: SVGlobalThemeModel? = arguments?.getParcelable<SVGlobalThemeModel>("data")
        if (data != null) {
            binding.editTextTextPersonName.setText(data.name)
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
                    if (data != null) {
                        viewModel.updateGlobalTheme(GlobalThemeDbModel(data.id,binding.editTextTextPersonName.text.toString()))
                    }
                }
                viewModel.getAllGlobalTheme()
                Log.d("dbbd", globalThemes.toString())
                Log.d("dbbd", global.toString())

                Log.d("dbbd", binding.editTextTextPersonName.text.toString())
                val navController = findNavController()
                navController.navigate(R.id.viewGlobalThemeFragment)
            } catch (e: Exception) {
            }
        }
    }

}