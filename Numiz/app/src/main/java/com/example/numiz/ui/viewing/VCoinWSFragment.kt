package com.example.numiz.ui.viewing

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numiz.R
import com.example.numiz.databinding.FragmentVCoinWSBinding
import com.example.numiz.databinding.FragmentViewCoinBinding
import com.example.numiz.db.model.supmodel.CoinModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.viewing.adapter.SpacingItemDecoration
import com.example.numiz.ui.viewing.adapter.ViewCoinAdapter
import com.example.numiz.ui.viewing.viewmodel.ViewCoinVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VCoinWSFragment : Fragment() {

    private lateinit var binding: FragmentVCoinWSBinding
    private val viewModel by viewModels<ViewCoinVM>()
    private lateinit var adapter: ViewCoinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVCoinWSBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.addItemDecoration(SpacingItemDecoration(dpToPx(8)))


        adapter = ViewCoinAdapter(::go)
        viewModel.coin.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllCoinSort()

        binding.floatingActionButton.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.addCoinFragment)
        }



    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = this.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun go(item: CoinModel) {
        val bundle = Bundle()
        bundle.putParcelable("data", (Mapper::dbToUiSVCoinModel)(item))
        val navController = findNavController()
        navController.navigate(R.id.SVCoinFragment, bundle)
    }


}