package com.example.numiz.ui.viewing.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.supmodel.CoinModel
import com.example.numiz.ui.model.sv.SVCoinCountModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewCoinVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    val coin = MutableLiveData<List<CoinModel>>()

    val coinGr = MutableLiveData<List<SVCoinCountModel>>()

    fun getAllMint() {
        viewModelScope.launch {
            coin.value = dao.getCoinModel()
        }
    }

    fun getAllCoinSort() {
        viewModelScope.launch {
            coin.value = dao.getAllCoinSort()
        }
    }

    fun getAllCoinGr() {
        viewModelScope.launch {
            coinGr.value = dao.getCoinGroup()
        }
    }

}