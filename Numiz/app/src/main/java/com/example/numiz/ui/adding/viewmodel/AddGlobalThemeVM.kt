package com.example.numiz.ui.adding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.CountryDbModel
import com.example.numiz.db.model.GlobalThemeDbModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.model.Country
import com.example.numiz.ui.model.GlobalTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddGlobalThemeVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    fun addGlobalTheme(item: GlobalTheme) {
        viewModelScope.launch {
            dao.insertGlobalTheme((Mapper::uiToDbGlobalThemeModel)(item))
        }
    }

    fun updateGlobalTheme(item: GlobalThemeDbModel) {
        viewModelScope.launch {
            dao.updateGlobalTheme(item)
        }
    }

    val money = MutableLiveData<List<GlobalTheme>>()

    fun getAllGlobalTheme() {
        viewModelScope.launch {
            money.value = dao.getAllGlobalTheme().map(Mapper::dbToUiGlobalThemeModel)
        }
    }

    val mon = MutableLiveData<List<GlobalThemeDbModel>>()

    fun getAllGlobal() {
        viewModelScope.launch {
            mon.value = dao.getAllGlobalTheme()
        }
    }

}