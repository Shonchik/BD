package com.example.numiz.db.model

import androidx.room.*

@Entity(
    tableName = "theme",
    indices = [(Index(value = ["name","descriptionId"], unique = true))],
    foreignKeys = arrayOf(
        ForeignKey(entity = GlobalThemeDbModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("globalThemeId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(entity = ThemeDescrDbModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("descriptionId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class ThemeDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val globalThemeId: Int,
    @ColumnInfo val descriptionId: Int,
)