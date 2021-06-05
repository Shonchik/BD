package com.example.numiz.ui.viewing.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.supmodel.ThemeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewThemeVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    val theme = MutableLiveData<List<ThemeModel>>()

    fun getAllTheme() {
        viewModelScope.launch {
            theme.value = dao.getThemeModel()
        }
    }

}