package com.example.numiz.ui.adding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.CoinDbModel
import com.example.numiz.db.model.CoinDescrDbModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.model.Coin
import com.example.numiz.ui.model.CoinDescr
import com.example.numiz.ui.model.Mint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCoinVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    fun addCoin(item: Coin) {
        viewModelScope.launch {
            dao.insertCoin((Mapper::uiToDbCoinModel)(item))
        }
    }

    fun updateCoin(item: CoinDbModel) {
        viewModelScope.launch {
            dao.updateCoin(item)
        }
    }

    fun updateCoinDescr(item: CoinDescrDbModel) {
        viewModelScope.launch {
            dao.updateCoinDescr(item)
        }
    }

    val money = MutableLiveData<List<Coin>>()

    fun getAllCoin() {
        viewModelScope.launch {
            money.value = dao.getAllCoin().map(Mapper::dbToUiCoinModel)
        }
    }

    val mints = MutableLiveData<List<Mint>>()

    fun getAllMint() {
        viewModelScope.launch {
            mints.value = dao.getAllMint().map(Mapper::dbToUiMintModel)
        }
    }

    fun addCoinDescr(item: CoinDescr) {
        viewModelScope.launch {
            dao.insertCoinDescr((Mapper::uiToDbCoinDescrModel)(item))
        }
    }

}