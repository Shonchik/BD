package com.example.numiz.ui.viewing

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numiz.R
import com.example.numiz.databinding.FragmentViewCollectionBinding
import com.example.numiz.databinding.FragmentViewThemeBinding
import com.example.numiz.db.model.supmodel.CollectionModel
import com.example.numiz.db.model.supmodel.ThemeModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.viewing.adapter.SpacingItemDecoration
import com.example.numiz.ui.viewing.adapter.ViewCollectionAdapter
import com.example.numiz.ui.viewing.adapter.ViewThemeAdapter
import com.example.numiz.ui.viewing.viewmodel.ViewCollectionVM
import com.example.numiz.ui.viewing.viewmodel.ViewThemeVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewCollectionFragment : Fragment() {

    private lateinit var binding: FragmentViewCollectionBinding
    private val viewModel by viewModels<ViewCollectionVM>()
    private lateinit var adapter: ViewCollectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.addItemDecoration(SpacingItemDecoration(dpToPx(8)))


        adapter = ViewCollectionAdapter(::go)
        viewModel.collection.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllTheme()

        binding.floatingActionButton.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.addCollectionFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.collection_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        when (item.itemId) {
            R.id.globalThemeMenu -> {
                navController.navigate(R.id.viewGlobalThemeFragment)
                return true
            }
            R.id.themeMenu -> {
                navController.navigate(R.id.viewThemeFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = this.getResources().getDisplayMetrics()
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun go(item: CollectionModel) {
        val bundle = Bundle()
        bundle.putParcelable("data", (Mapper::dbToUiSVCollectionModel)(item))
        val navController = findNavController()
        navController.navigate(R.id.SVCollectionFragment, bundle)
    }

}