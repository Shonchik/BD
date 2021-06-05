package com.example.numiz.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "global_theme", indices = [(Index(value = ["name"], unique = true))])
data class GlobalThemeDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String
)