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
import com.example.numiz.databinding.FragmentSVThemeBinding
import com.example.numiz.ui.model.sv.SVCoinModel
import com.example.numiz.ui.model.sv.SVThemeModel
import com.example.numiz.ui.singleviewing.viemodel.SVVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SVThemeFragment : Fragment() {

    private lateinit var binding: FragmentSVThemeBinding
    private val viewModel by viewModels<SVVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSVThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: SVThemeModel? = arguments?.getParcelable<SVThemeModel>("data")
        if (data != null) {
            binding.name.text = data.name
            binding.amount.text = data.globalTheme
            binding.descrTitle.text = data.description
            binding.del.setOnClickListener { basicAlert(data.id) }
        }

        binding.upd.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("data", data)
            val navController = findNavController()
            navController.navigate(R.id.UThemeFragment, bundle)
        }


    }

    fun basicAlert(item: Int){

        val builder = AlertDialog.Builder(requireContext())

        with(builder)
        {
            setTitle("Alert!!!")
            setMessage("Are you sure?")
            setPositiveButton("YES") { dialogInterface, i ->
                viewModel.delTheme(item)
                //viewModel.delThemeD(item)
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