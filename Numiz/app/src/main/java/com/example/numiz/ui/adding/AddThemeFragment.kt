package com.example.numiz.ui.adding

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
import com.example.numiz.databinding.FragmentAddCoinBinding
import com.example.numiz.databinding.FragmentAddThemeBinding
import com.example.numiz.ui.adding.viewmodel.AddCoinVM
import com.example.numiz.ui.adding.viewmodel.AddThemeVM
import com.example.numiz.ui.model.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddThemeFragment : Fragment() {

    private val viewModel by viewModels<AddThemeVM>()
    private lateinit var binding: FragmentAddThemeBinding
    var globalTheme: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var globalThemes: List<GlobalTheme> = emptyList()

        viewModel.globalThemes.observe(viewLifecycleOwner) {
            globalThemes = it
        }

        var theme: List<Theme> = emptyList()

        viewModel.theme.observe(viewLifecycleOwner) {
            theme = it
        }
        viewModel.getAllGlobalTheme()
        viewModel.getAllCoin()

        binding.button2.setOnClickListener {
            viewModel.getAllCoin()
            withItems(globalThemes)
        }

        binding.button.setOnClickListener {
            viewModel.getAllCoin()
            try {
                if (binding.editTextTextPersonName2.text.toString() != "" && globalTheme != -1) {

                    Log.d("dbbd", theme.toString())
                    viewModel.addThemeDescr(ThemeDescr(binding.editTextTextPersonName4.text.toString()))
                    viewModel.addTheme(
                        Theme(
                            binding.editTextTextPersonName2.text.toString(),
                            globalTheme,
                            theme.size+1
                        )
                    )

                }
                viewModel.getAllCoin()
                Log.d("dbbd", theme.toString())

                val navController = findNavController()
                navController.popBackStack()
            } catch (e: Exception) {
            }
        }

    }

    fun withItems(globalThemes: List<GlobalTheme>) {
        val c = Array(globalThemes.size) {
            globalThemes[it].name
        }
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("Select Global Theme")
            setItems(c) { dialog, which ->
                globalTheme = which + 1
                binding.textView.text = c[which]
            }
            setPositiveButton("NEW GLOBAL THEME") { dialogInterface, i ->
                go()
            }
            show()
        }
    }

    fun go() {
        val navController = findNavController()
        navController.navigate(R.id.addGlobalThemeFragment)
    }

}