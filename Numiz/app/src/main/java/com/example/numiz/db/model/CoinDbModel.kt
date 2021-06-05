package com.example.numiz.db.model

import androidx.annotation.CheckResult
import androidx.annotation.NonNull
import androidx.room.*

@Entity(
    tableName = "coin",
    indices = [(Index(value = ["name"], unique = true))],
    foreignKeys = arrayOf(
        ForeignKey(entity = MintDbModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("mintId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(entity = CoinDescrDbModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("descriptionId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class CoinDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo @NonNull val name: String,
    @ColumnInfo val amount: Int,
    @ColumnInfo  val year: String,
    @ColumnInfo val mintId: Int,
    @ColumnInfo val descriptionId: Int = id,
)