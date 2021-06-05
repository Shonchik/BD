package com.example.numiz.db.model.supmodel

import androidx.room.Embedded
import androidx.room.Relation
import com.example.numiz.db.model.CoinDbModel
import com.example.numiz.db.model.CoinDescrDbModel
import com.example.numiz.db.model.MintDbModel

data class CoinModel(
    @Embedded val coin: CoinDbModel,
    @Relation(parentColumn = "mintId", entityColumn = "id") val mintModel: MintDbModel,
    @Relation(parentColumn = "descriptionId", entityColumn = "id") val description: CoinDescrDbModel,
)