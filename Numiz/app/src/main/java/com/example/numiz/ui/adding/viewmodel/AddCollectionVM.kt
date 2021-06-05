package com.example.numiz.ui.adding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.CoinDbModel
import com.example.numiz.db.model.CoinDescrDbModel
import com.example.numiz.db.model.CollectionDbModel
import com.example.numiz.db.model.CollectionDescrDbModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.model.*
import com.example.numiz.ui.model.Collection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCollectionVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    fun addCollection(item: Collection) {
        viewModelScope.launch {
            dao.insertCollection((Mapper::uiToDbCollectionModel)(item))
        }
    }

    val collection = MutableLiveData<List<Collection>>()

    fun getAllCollection() {
        viewModelScope.launch {
            collection.value = dao.getAllCollection().map(Mapper::dbToUiCollectionModel)
        }
    }

    val coin = MutableLiveData<List<Coin>>()

    fun getAllCoin() {
        viewModelScope.launch {
            coin.value = dao.getAllCoin().map(Mapper::dbToUiCoinModel)
        }
    }

    fun updateCollection(item: CollectionDbModel) {
        viewModelScope.launch {
            dao.updateCollection(item)
        }
    }

    fun updateCollectionDescr(item: CollectionDescrDbModel) {
        viewModelScope.launch {
            dao.updateCollectionDescription(item)
        }
    }

    val globalThemes = MutableLiveData<List<GlobalTheme>>()

    fun getAllGlobalTheme() {
        viewModelScope.launch {
            globalThemes.value = dao.getAllGlobalTheme().map(Mapper::dbToUiGlobalThemeModel)
        }
    }

    fun addCollectionDescr(item: CollectionDescr) {
        viewModelScope.launch {
            dao.insertCollectionDescr((Mapper::uiToDbCollectionDescrModel)(item))
        }
    }

    val themes = MutableLiveData<List<Theme>>()

    fun getAllTheme() {
        viewModelScope.launch {
            themes.value = dao.getAllTheme().map(Mapper::dbToUiThemeModel)
        }
    }


}