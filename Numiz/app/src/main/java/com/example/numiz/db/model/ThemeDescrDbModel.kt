package com.example.numiz.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme_description")
data class ThemeDescrDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val description: String
)