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
import com.example.numiz.databinding.FragmentUCoinBinding
import com.example.numiz.databinding.FragmentUMintBinding
import com.example.numiz.db.model.CoinDbModel
import com.example.numiz.db.model.CoinDescrDbModel
import com.example.numiz.ui.adding.viewmodel.AddCoinVM
import com.example.numiz.ui.adding.viewmodel.AddMintVM
import com.example.numiz.ui.model.Coin
import com.example.numiz.ui.model.CoinDescr
import com.example.numiz.ui.model.Mint
import com.example.numiz.ui.model.sv.SVCoinModel
import com.example.numiz.ui.model.sv.SVMintModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UCoinFragment : Fragment() {

    private val viewModel by viewModels<AddCoinVM>()
    private lateinit var binding: FragmentUCoinBinding
    var mint: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mints: List<Mint> = emptyList()

        viewModel.mints.observe(viewLifecycleOwner) {
            mints = it
        }

        val data: SVCoinModel? = arguments?.getParcelable<SVCoinModel>("data")
        if (data != null) {
            binding.editTextTextPersonName.setText(data.year)
            binding.editTextTextPersonName2.setText(data.name)
            binding.editTextTextPersonName3.setText(data.amount.toString())
            binding.editTextTextPersonName4.setText(data.description)
            binding.textView.text = data.mint
            mint = data.mintId
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
                    if (data != null) {
                        viewModel.updateCoinDescr(
                            CoinDescrDbModel(
                                data.id,
                                binding.editTextTextPersonName4.text.toString()
                            )
                        )

                        viewModel.updateCoin(
                            CoinDbModel(data.id,
                                binding.editTextTextPersonName2.text.toString(),
                                binding.editTextTextPersonName3.text.toString().toInt(),
                                binding.editTextTextPersonName.text.toString(),
                                mint,
                                data.id
                            )
                        )
                    }

                }
                viewModel.getAllCoin()
                Log.d("dbbd", coins.toString())

                Log.d("dbbd", binding.editTextTextPersonName.text.toString())

                val navController = findNavController()
                navController.navigate(R.id.viewCoinFragment)
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