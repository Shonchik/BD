package com.example.numiz.ui.viewing

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numiz.R
import com.example.numiz.databinding.FragmentViewCoinBinding
import com.example.numiz.databinding.FragmentViewMintBinding
import com.example.numiz.db.model.supmodel.CoinModel
import com.example.numiz.db.model.supmodel.MintModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.viewing.adapter.SpacingItemDecoration
import com.example.numiz.ui.viewing.adapter.ViewCoinAdapter
import com.example.numiz.ui.viewing.adapter.ViewMintAdapter
import com.example.numiz.ui.viewing.viewmodel.ViewCoinVM
import com.example.numiz.ui.viewing.viewmodel.ViewMintVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewCoinFragment : Fragment() {

    private lateinit var binding: FragmentViewCoinBinding
    private val viewModel by viewModels<ViewCoinVM>()
    private lateinit var adapter: ViewCoinAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewCoinBinding.inflate(inflater, container, false)
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
        viewModel.getAllMint()

        binding.floatingActionButton.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.addCoinFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.coin_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        when (item.itemId) {
            R.id.mintMenu -> {
                navController.navigate(R.id.viewMintFragment)
                return true
            }
            R.id.countryMenu -> {
                navController.navigate(R.id.viewCountryFragment)
                return true
            }
            R.id.coinSortyMenu -> {
                navController.navigate(R.id.VCoinWSFragment)
                return true
            }
            R.id.coinGroupMenu -> {
                navController.navigate(R.id.viewCoinGroupFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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