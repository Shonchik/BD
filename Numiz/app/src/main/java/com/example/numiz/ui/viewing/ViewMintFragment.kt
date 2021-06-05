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
import com.example.numiz.databinding.FragmentViewMintBinding
import com.example.numiz.db.model.supmodel.MintModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.viewing.adapter.SpacingItemDecoration
import com.example.numiz.ui.viewing.adapter.ViewMintAdapter
import com.example.numiz.ui.viewing.viewmodel.ViewMintVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewMintFragment : Fragment() {

    private lateinit var binding: FragmentViewMintBinding
    private val viewModel by viewModels<ViewMintVM>()
    private lateinit var adapter: ViewMintAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewMintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.addItemDecoration(SpacingItemDecoration(dpToPx(8)))


        adapter = ViewMintAdapter(::go)
        viewModel.mint.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllMint()

        binding.floatingActionButton.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.addMintFragment)
        }

    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = this.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun go(item: MintModel) {
        val bundle = Bundle()
        bundle.putParcelable("data", (Mapper::dbToUiSVMintModel)(item))
        val navController = findNavController()
        navController.navigate(R.id.SVMintFragment, bundle)
    }


}