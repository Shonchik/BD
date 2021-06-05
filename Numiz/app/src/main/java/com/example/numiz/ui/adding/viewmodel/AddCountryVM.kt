package com.example.numiz.ui.adding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numiz.db.MyDatabase
import com.example.numiz.db.model.CountryDbModel
import com.example.numiz.db.model.MintDbModel
import com.example.numiz.mapper.Mapper
import com.example.numiz.ui.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCountryVM @Inject constructor(
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()

    fun addCountry(item: Country) {
        viewModelScope.launch {
            dao.insertCountry((Mapper::uiToDbCountryModel)(item))
        }
    }

    fun updateCountry(item: CountryDbModel) {
        viewModelScope.launch {
            dao.updateCountry(item)
        }
    }

    val money = MutableLiveData<List<Country>>()

    fun getAllCountry() {
        viewModelScope.launch {
            money.value = dao.getAllCountry().map(Mapper::dbToUiCountryModel)
        }
    }

}