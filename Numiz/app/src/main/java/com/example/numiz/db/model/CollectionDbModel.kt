package com.example.numiz.db.model

import androidx.room.*
import kotlinx.serialization.json.Json
import java.lang.reflect.Type
import java.util.*

@Entity(
    tableName = "collection",
    indices = [(Index(value = ["name"], unique = true))],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ThemeDbModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("themeId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GlobalThemeDbModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("globalThemeId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CollectionDescrDbModel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("descriptionId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class CollectionDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val themeId: Int,
    @ColumnInfo val globalThemeId: Int,
    @ColumnInfo val descriptionId: Int,
    @ColumnInfo val coins: String,
)

class Converters {
    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> {
        return flatStringList.split(",,,")
    }
    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>): String {
        return listOfString.joinToString(",,,")
    }
}