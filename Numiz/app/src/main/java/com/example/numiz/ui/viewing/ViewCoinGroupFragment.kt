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
import com.example.numiz.databinding.FragmentViewCoinBinding
import com.example.numiz.databinding.FragmentViewCoinGroupBinding
import com.example.numiz.ui.viewing.adapter.MyListAdapter
import com.example.numiz.ui.viewing.adapter.SpacingItemDecoration
import com.example.numiz.ui.viewing.adapter.ViewCoinAdapter
import com.example.numiz.ui.viewing.viewmodel.ViewCoinVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewCoinGroupFragment : Fragment() {

    private lateinit var binding: FragmentViewCoinGroupBinding
    private val viewModel by viewModels<ViewCoinVM>()
    private lateinit var adapter: MyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewCoinGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.addItemDecoration(SpacingItemDecoration(dpToPx(8)))


        adapter = MyListAdapter()
        viewModel.coinGr.observe(viewLifecycleOwner) {
            adapter.submitList(it.map { "${it.year} (${it.count})"})
        }

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllCoinGr()

    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = this.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

}