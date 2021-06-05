package com.example.numiz.ui.viewing.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.GlobalThemeDbModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewGlobalThemeVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    val globalTheme = MutableLiveData<List<GlobalThemeDbModel>>()

    fun getAllGlobalTheme() {
        viewModelScope.launch {
            globalTheme.value = dao.getAllGlobalTheme()
        }
    }

}