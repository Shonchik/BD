package com.example.numiz.db.model.supmodel

import androidx.room.Embedded
import androidx.room.Relation
import com.example.numiz.db.model.CountryDbModel
import com.example.numiz.db.model.MintDbModel

data class MintModel(
    @Embedded val mint: MintDbModel,
    @Relation(parentColumn = "countryId", entityColumn = "id") val country: CountryDbModel,
)