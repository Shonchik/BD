package com.example.numiz.ui.adding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.GlobalThemeDbModel
import com.example.numiz.db.model.ThemeDbModel
import com.example.numiz.db.model.ThemeDescrDbModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddThemeVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    fun addTheme(item: Theme) {
        viewModelScope.launch {
            dao.insertTheme((Mapper::uiToDbThemeModel)(item))
        }
    }

    fun updateTheme(item: ThemeDbModel) {
        viewModelScope.launch {
            dao.updateTheme(item)
        }
    }

    fun updateThemDescre(item: ThemeDescrDbModel) {
        viewModelScope.launch {
            dao.updateThemeDescr(item)
        }
    }

    val theme = MutableLiveData<List<Theme>>()

    fun getAllCoin() {
        viewModelScope.launch {
            theme.value = dao.getAllTheme().map(Mapper::dbToUiThemeModel)
        }
    }

    val globalThemes = MutableLiveData<List<GlobalTheme>>()

    fun getAllGlobalTheme() {
        viewModelScope.launch {
            globalThemes.value = dao.getAllGlobalTheme().map(Mapper::dbToUiGlobalThemeModel)
        }
    }

    fun addThemeDescr(item: ThemeDescr) {
        viewModelScope.launch {
            dao.insertThemeDescr((Mapper::uiToDbThemeDescrModel)(item))
        }
    }

}