package com.example.numiz.ui.viewing

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numiz.R
import com.example.numiz.databinding.FragmentViewGlobalThemeBinding
import com.example.numiz.databinding.FragmentViewThemeBinding
import com.example.numiz.db.model.GlobalThemeDbModel
import com.example.numiz.db.model.supmodel.ThemeModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.viewing.adapter.SpacingItemDecoration
import com.example.numiz.ui.viewing.adapter.ViewGlobalThemeAdapter
import com.example.numiz.ui.viewing.adapter.ViewThemeAdapter
import com.example.numiz.ui.viewing.viewmodel.ViewGlobalThemeVM
import com.example.numiz.ui.viewing.viewmodel.ViewThemeVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewThemeFragment : Fragment() {

    private lateinit var binding: FragmentViewThemeBinding
    private val viewModel by viewModels<ViewThemeVM>()
    private lateinit var adapter: ViewThemeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.addItemDecoration(SpacingItemDecoration(dpToPx(8)))


        adapter = ViewThemeAdapter(::go)
        viewModel.theme.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllTheme()

        binding.floatingActionButton.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.addThemeFragment)
        }

    }



    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = this.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun go(item: ThemeModel) {
        val bundle = Bundle()
        bundle.putParcelable("data", (Mapper::dbToUiSVThemeModel)(item))
        val navController = findNavController()
        navController.navigate(R.id.SVThemeFragment, bundle)
    }

}