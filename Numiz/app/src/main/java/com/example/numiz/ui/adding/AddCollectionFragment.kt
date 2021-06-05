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
import com.example.numiz.databinding.FragmentAddCollectionBinding
import com.example.numiz.databinding.FragmentAddThemeBinding
import com.example.numiz.ui.adding.viewmodel.AddCollectionVM
import com.example.numiz.ui.adding.viewmodel.AddThemeVM
import com.example.numiz.ui.model.*
import com.example.numiz.ui.model.Collection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCollectionFragment : Fragment() {

    private val viewModel by viewModels<AddCollectionVM>()
    private lateinit var binding: FragmentAddCollectionBinding
    var globalTheme: Int = -1
    var theme: Int = -1
    var coin: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var collection: List<Collection> = emptyList()

        viewModel.collection.observe(viewLifecycleOwner) {
            collection = it
        }

        var coins: List<Coin> = emptyList()

        viewModel.coin.observe(viewLifecycleOwner) {
            coins = it
        }

        var themes: List<Theme> = emptyList()

        viewModel.themes.observe(viewLifecycleOwner) {
            themes = it
        }

        var globalThemes: List<GlobalTheme> = emptyList()

        viewModel.globalThemes.observe(viewLifecycleOwner) {
            globalThemes = it
        }

        viewModel.getAllGlobalTheme()
        viewModel.getAllTheme()
        viewModel.getAllCoin()
        viewModel.getAllCollection()

        binding.button2.setOnClickListener {
            viewModel.getAllGlobalTheme()
            withItemsGT(globalThemes)
        }

        binding.button3.setOnClickListener {
            viewModel.getAllTheme()
            withItemsT(themes)
        }

        binding.button4.setOnClickListener {
            viewModel.getAllCoin()
            withMultiChoiceList(coins)
        }

        binding.button.setOnClickListener {
            try {
                viewModel.getAllCollection()
                if (binding.editTextTextPersonName2.text.toString() != "" && globalTheme != -1 && theme != -1) {
                    viewModel.addCollectionDescr(CollectionDescr(binding.editTextTextPersonName4.text.toString()))
                    viewModel.addCollection(
                        Collection(
                            binding.editTextTextPersonName2.text.toString(),
                            theme,
                            globalTheme,
                            collection.size+1,
                            coin
                        )
                    )

                }
                viewModel.getAllCollection()
                Log.d("dbbd", collection.toString())

                val navController = findNavController()
                navController.popBackStack()
            } catch (e: Exception) {
            }
        }

    }

    fun withMultiChoiceList(coins: List<Coin>) {

        val c = Array(coins.size) {
            coins[it].name
        }
        val selectedList = ArrayList<Int>()
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Select coins")
        builder.setMultiChoiceItems(c, null
        ) { dialog, which, isChecked ->
            if (isChecked) {
                selectedList.add(which)
            } else if (selectedList.contains(which)) {
                selectedList.remove(Integer.valueOf(which))
            }
        }

        builder.setPositiveButton("DONE") { dialogInterface, i ->
            coin = List(selectedList.size){coins[selectedList[it]].name}.joinToString(",,,")
        }

        builder.show()

    }

    fun withItemsGT(globalThemes: List<GlobalTheme>) {
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
                goGT()
            }
            show()
        }
    }

    fun withItemsT(themes: List<Theme>) {
        val c = Array(themes.size) {
            themes[it].name
        }
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("Select Theme")
            setItems(c) { dialog, which ->
                theme = which + 1
                binding.textView2.text = c[which]
            }
            setPositiveButton("NEW THEME") { dialogInterface, i ->
                goT()
            }
            show()
        }
    }

    fun goT() {
        val navController = findNavController()
        navController.navigate(R.id.addThemeFragment)
    }

    fun goGT() {
        val navController = findNavController()
        navController.navigate(R.id.addGlobalThemeFragment)
    }

}