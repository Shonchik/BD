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
import com.example.numiz.databinding.FragmentAddThemeBinding
import com.example.numiz.databinding.FragmentUThemeBinding
import com.example.numiz.db.model.ThemeDbModel
import com.example.numiz.db.model.ThemeDescrDbModel
import com.example.numiz.ui.adding.viewmodel.AddThemeVM
import com.example.numiz.ui.model.GlobalTheme
import com.example.numiz.ui.model.Theme
import com.example.numiz.ui.model.ThemeDescr
import com.example.numiz.ui.model.sv.SVCoinModel
import com.example.numiz.ui.model.sv.SVThemeModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UThemeFragment : Fragment() {

    private val viewModel by viewModels<AddThemeVM>()
    private lateinit var binding: FragmentUThemeBinding
    var globalTheme: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var globalThemes: List<GlobalTheme> = emptyList()

        viewModel.globalThemes.observe(viewLifecycleOwner) {
            globalThemes = it
        }

        var theme: List<Theme> = emptyList()

        val data: SVThemeModel? = arguments?.getParcelable<SVThemeModel>("data")
        if (data != null) {
            binding.editTextTextPersonName2.setText(data.name)
            binding.editTextTextPersonName4.setText(data.description)
            binding.textView.text = data.globalTheme
            globalTheme = data.globalThemeId
        }

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
            try {
                viewModel.getAllCoin()
                if (binding.editTextTextPersonName2.text.toString() != "" && globalTheme != -1) {
                    if (data != null) {
                        viewModel.updateThemDescre(
                            ThemeDescrDbModel(
                                data.id,
                                binding.editTextTextPersonName4.text.toString()
                            )
                        )

                        viewModel.updateTheme(
                            ThemeDbModel(data.id,
                                binding.editTextTextPersonName2.text.toString(),
                                globalTheme,
                                data.id
                            )
                        )
                    }

                }
                viewModel.getAllCoin()
                Log.d("dbbd", theme.toString())

                val navController = findNavController()
                navController.navigate(R.id.viewThemeFragment)
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