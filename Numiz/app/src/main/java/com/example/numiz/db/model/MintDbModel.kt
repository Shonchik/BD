package com.example.numiz.db.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "mint",
    indices = [(Index(value = ["name", "countryId"], unique = true))],
    foreignKeys = arrayOf(
        ForeignKey(entity = CountryDbModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("countryId"),
            onUpdate = CASCADE,
            onDelete = CASCADE))
)
data class MintDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val countryId: Int,
)


