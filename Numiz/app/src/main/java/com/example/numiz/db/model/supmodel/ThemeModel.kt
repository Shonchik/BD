package com.example.numiz.db.model.supmodel

import androidx.room.Embedded
import androidx.room.Relation
import com.example.numiz.db.model.GlobalThemeDbModel
import com.example.numiz.db.model.ThemeDbModel
import com.example.numiz.db.model.ThemeDescrDbModel

data class ThemeModel(
    @Embedded val theme: ThemeDbModel,
    @Relation(parentColumn = "globalThemeId", entityColumn = "id") val globalTheme: GlobalThemeDbModel,
    @Relation(parentColumn = "descriptionId", entityColumn = "id") val description: ThemeDescrDbModel,
)