package com.example.numiz.ui.viewing.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.supmodel.MintModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewMintVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    val mint = MutableLiveData<List<MintModel>>()

    fun getAllMint() {
        viewModelScope.launch {
            mint.value = dao.getMintModel()
        }
    }

}