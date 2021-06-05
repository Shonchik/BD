package com.example.numiz.ui.adding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.MintDbModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.model.Country
import com.example.numiz.ui.model.Mint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMintVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    fun addMint(item: Mint) {
        viewModelScope.launch {
            dao.insertMint((Mapper::uiToDbMintModel)(item))
        }
    }

    fun updateMint(item: MintDbModel) {
        viewModelScope.launch {
            dao.updateMint(item)
        }
    }

    val money = MutableLiveData<List<Mint>>()

    fun getAllMint() {
        viewModelScope.launch {
            money.value = dao.getAllMint().map(Mapper::dbToUiMintModel)
        }
    }

    val countrys = MutableLiveData<List<Country>>()

    fun getAllCountry() {
        viewModelScope.launch {
            countrys.value = dao.getAllCountry().map(Mapper::dbToUiCountryModel)
        }
    }



}