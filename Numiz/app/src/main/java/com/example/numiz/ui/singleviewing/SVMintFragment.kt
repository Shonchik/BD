package com.example.numiz.ui.singleviewing

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
import com.example.numiz.db.model.supmodel.MintModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.model.sv.SVMintModel
import com.example.numiz.ui.singleviewing.viemodel.SVVM
import com.example.numiz.ui.viewing.viewmodel.ViewMintVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SVMintFragment : Fragment() {

    private lateinit var binding: FragmentSVMintBinding
    private val viewModel by viewModels<SVVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSVMintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: SVMintModel? = arguments?.getParcelable<SVMintModel>("data")
        if (data != null) {
            binding.mint.text = data.name
            binding.country.text = data.country
            binding.del.setOnClickListener { basicAlert(data.id) }
        }

        binding.upd.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("data", data)
            val navController = findNavController()
            navController.navigate(R.id.UMintFragment, bundle)
        }


    }

    fun basicAlert(item: Int){

        val builder = AlertDialog.Builder(requireContext())

        with(builder)
        {
            setTitle("Alert!!!")
            setMessage("Are you sure?")
            setPositiveButton("YES") { dialogInterface, i ->
                viewModel.delMint(item)
                val navController = findNavController()
                navController.popBackStack()
            }
            setNegativeButton("NO") { dialogInterface, i ->
                Log.d("dbbd", "close")
            }
            show()
        }
    }
}