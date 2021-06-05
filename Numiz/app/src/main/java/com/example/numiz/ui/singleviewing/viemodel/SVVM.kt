package com.example.numiz.ui.singleviewing.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SVVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    fun delMint(item: Int) {
        viewModelScope.launch {
            dao.delMint(item)
        }
    }

    fun delCountry(item: Int) {
        viewModelScope.launch {
            dao.delCountry(item)
        }
    }

    fun delCoin(item: Int) {
        viewModelScope.launch {
            //dao.delCoinDescr(item)
            dao.delCoin(item)
        }
    }

    fun delGlobalTheme(item: Int) {
        viewModelScope.launch {
            dao.delGlobalTheme(item)
        }
    }

    fun delTheme(item: Int) {
        viewModelScope.launch {
            dao.delTheme(item)
        }
    }

    fun delThemeD(item: Int) {
        viewModelScope.launch {
            dao.delThemeDescr(item)
        }
    }

    fun delCollection(item: Int) {
        viewModelScope.launch {
            dao.delCollection(item)
        }
    }

}