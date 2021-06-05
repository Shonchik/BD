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
import com.example.numiz.databinding.FragmentAddMintBinding
import com.example.numiz.ui.adding.viewmodel.AddCoinVM
import com.example.numiz.ui.adding.viewmodel.AddMintVM
import com.example.numiz.ui.model.Coin
import com.example.numiz.ui.model.CoinDescr
import com.example.numiz.ui.model.Country
import com.example.numiz.ui.model.Mint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCoinFragment : Fragment() {

    private val viewModel by viewModels<AddCoinVM>()
    private lateinit var binding: FragmentAddCoinBinding
    var mint: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mints: List<Mint> = emptyList()

        viewModel.mints.observe(viewLifecycleOwner) {
            mints = it
        }

        var coins: List<Coin> = emptyList()

        viewModel.money.observe(viewLifecycleOwner) {
            coins = it
        }
        viewModel.getAllMint()
        viewModel.getAllCoin()

        binding.button2.setOnClickListener {
            viewModel.getAllCoin()
            withItems(mints)
        }

        binding.button.setOnClickListener {
            Log.d("dbbd", coins.size.toString())
            viewModel.getAllCoin()
            try {
                if (binding.editTextTextPersonName2.text.toString() != "" && mint != -1) {
                    viewModel.addCoinDescr(CoinDescr(binding.editTextTextPersonName4.text.toString()))
                    viewModel.addCoin(
                        Coin(
                            binding.editTextTextPersonName2.text.toString(),
                            binding.editTextTextPersonName3.text.toString().toInt(),
                            binding.editTextTextPersonName.text.toString(),
                            mint,
                            coins.size + 1
                        )
                    )

                }
                viewModel.getAllCoin()
                Log.d("dbbd", coins.toString())

                Log.d("dbbd", binding.editTextTextPersonName.text.toString())

                val navController = findNavController()
                navController.popBackStack()
            } catch (e: Exception) {
            }
        }

    }

    fun withItems(mints: List<Mint>) {
        val c = Array(mints.size) {
            mints[it].name
        }
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("Select Mint")
            setItems(c) { dialog, which ->
                mint = which + 1
                binding.textView.text = c[which]
            }
            setPositiveButton("NEW MINT") { dialogInterface, i ->
                go()
            }
            show()
        }
    }

    fun go() {
        val navController = findNavController()
        navController.navigate(R.id.addMintFragment)
    }

}