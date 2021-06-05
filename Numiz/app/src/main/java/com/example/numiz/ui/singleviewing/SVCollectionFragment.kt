package com.example.numiz.ui.singleviewing

import android.app.AlertDialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numiz.R
import com.example.numiz.databinding.FragmentSVCoinBinding
import com.example.numiz.databinding.FragmentSVCollectionBinding
import com.example.numiz.ui.model.sv.SVCoinModel
import com.example.numiz.ui.model.sv.SVCollectionModel
import com.example.numiz.ui.singleviewing.viemodel.SVVM
import com.example.numiz.ui.viewing.adapter.MyListAdapter
import com.example.numiz.ui.viewing.adapter.SpacingItemDecoration
import com.example.numiz.ui.viewing.adapter.ViewCollectionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SVCollectionFragment : Fragment() {

    private lateinit var binding: FragmentSVCollectionBinding
    private val viewModel by viewModels<SVVM>()
    private lateinit var adapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSVCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: SVCollectionModel? = arguments?.getParcelable<SVCollectionModel>("data")
        if (data != null) {
            binding.name.text = data.name
            binding.amount.text = data.theme
            binding.year.text = data.globalTheme
            binding.descrTitle.text = data.description
            binding.del.setOnClickListener { basicAlert(data.id) }

            binding.list.addItemDecoration(SpacingItemDecoration(dpToPx(8)))


            adapter = MyListAdapter()
            adapter.submitList(data.coins.split(",,,"))


            binding.list.adapter = adapter
            binding.list.layoutManager = LinearLayoutManager(requireContext())
        }

        binding.upd.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("data", data)
            val navController = findNavController()
            navController.navigate(R.id.UCollectionFragment, bundle)
        }


    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = this.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun basicAlert(item: Int){

        val builder = AlertDialog.Builder(requireContext())

        with(builder)
        {
            setTitle("Alert!!!")
            setMessage("Are you sure?")
            setPositiveButton("YES") { dialogInterface, i ->
                viewModel.delCollection(item)
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