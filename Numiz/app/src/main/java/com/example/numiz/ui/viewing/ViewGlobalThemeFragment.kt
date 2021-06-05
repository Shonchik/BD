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
import com.example.numiz.databinding.FragmentViewCountryBinding
import com.example.numiz.databinding.FragmentViewGlobalThemeBinding
import com.example.numiz.db.model.CountryDbModel
import com.example.numiz.db.model.GlobalThemeDbModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.viewing.adapter.SpacingItemDecoration
import com.example.numiz.ui.viewing.adapter.ViewCountryAdapter
import com.example.numiz.ui.viewing.adapter.ViewGlobalThemeAdapter
import com.example.numiz.ui.viewing.viewmodel.ViewCountryVM
import com.example.numiz.ui.viewing.viewmodel.ViewGlobalThemeVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewGlobalThemeFragment : Fragment() {

    private lateinit var binding: FragmentViewGlobalThemeBinding
    private val viewModel by viewModels<ViewGlobalThemeVM>()
    private lateinit var adapter: ViewGlobalThemeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewGlobalThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.addItemDecoration(SpacingItemDecoration(dpToPx(8)))


        adapter = ViewGlobalThemeAdapter(::go)
        viewModel.globalTheme.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllGlobalTheme()

        binding.floatingActionButton.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.addGlobalThemeFragment)
        }

    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = this.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun go(item: GlobalThemeDbModel) {
        val bundle = Bundle()
        bundle.putParcelable("data", (Mapper::dbToUiSVGlobalThemeModel)(item))
        val navController = findNavController()
        navController.navigate(R.id.SVGlobalThemeFragment, bundle)
    }

}