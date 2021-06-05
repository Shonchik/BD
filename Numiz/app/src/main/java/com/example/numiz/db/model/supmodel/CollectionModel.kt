package com.example.numiz.db.model.supmodel

import androidx.room.Embedded
import androidx.room.Relation
import com.example.numiz.db.model.*

data class CollectionModel(
    @Embedded val collection: CollectionDbModel,
    @Relation(parentColumn = "themeId", entityColumn = "id") val theme: ThemeDbModel,
    @Relation(parentColumn = "globalThemeId", entityColumn = "id") val globalTheme: GlobalThemeDbModel,
    @Relation(parentColumn = "descriptionId", entityColumn = "id") val description: CollectionDescrDbModel,
)