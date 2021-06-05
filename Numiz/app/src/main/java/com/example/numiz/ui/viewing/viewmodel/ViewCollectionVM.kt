package com.example.numiz.ui.viewing.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.supmodel.CollectionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewCollectionVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    val collection = MutableLiveData<List<CollectionModel>>()

    fun getAllTheme() {
        viewModelScope.launch {
            collection.value = dao.getCollectionModel()
        }
    }

}